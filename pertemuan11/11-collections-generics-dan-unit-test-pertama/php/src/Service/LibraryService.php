<?php
declare(strict_types=1);

namespace Perpustakaan\Service;

use Perpustakaan\Exception\AnggotaTidakDitemukanException;
use Perpustakaan\Exception\BatasPinjamTerlampauiException;
use Perpustakaan\Exception\BukuSedangDipinjamException;
use Perpustakaan\Exception\BukuTidakDitemukanException;
use Perpustakaan\Model\Buku;
use Perpustakaan\Model\KoleksiBuku;

final class LibraryService
{
    public const BATAS_PINJAM = 3;

    private KoleksiBuku $buku;
    /** @var array<string, true> */
    private array $anggota = [];
    /** @var array<string, array<string, true>> */
    private array $pinjaman = [];

    public function __construct()
    {
        $this->buku = new KoleksiBuku();
    }

    public function tambahBuku(string $isbn, string $judul, string $penulis, int $eksemplar): void
    {
        if (preg_match('/^-\d+$/', $isbn) === 1) {
            throw new \InvalidArgumentException('ISBN tidak boleh bernilai negatif');
        }
        if ($this->buku->cariByIsbn($isbn) !== null) {
            throw new \InvalidArgumentException("ISBN sudah terdaftar: {$isbn}");
        }
        $this->buku->simpan(new Buku($isbn, $judul, $penulis, $eksemplar, $eksemplar));
    }

    public function daftarkanAnggota(string $idAnggota): void
    {
        $this->validasiIdAnggota($idAnggota);
        $this->anggota[$idAnggota] = true;
        $this->pinjaman[$idAnggota] ??= [];
    }

    public function pinjam(string $idAnggota, string $isbn): void
    {
        $this->validasiIdAnggota($idAnggota);
        if (trim($isbn) === '') {
            throw new \InvalidArgumentException('ISBN tidak boleh kosong');
        }
        if (preg_match('/^-\d+$/', $isbn) === 1) {
            throw new \InvalidArgumentException('ISBN tidak boleh bernilai negatif');
        }
        if (!isset($this->anggota[$idAnggota])) {
            throw new AnggotaTidakDitemukanException($idAnggota);
        }

        $item = $this->buku->cariByIsbn($isbn);
        if ($item === null) {
            throw new BukuTidakDitemukanException($isbn);
        }
        if (isset($this->pinjaman[$idAnggota][$isbn]) || $item->tersedia === 0) {
            throw new BukuSedangDipinjamException($isbn, $item->judul);
        }
        if (count($this->pinjaman[$idAnggota]) >= self::BATAS_PINJAM) {
            throw new BatasPinjamTerlampauiException($idAnggota, self::BATAS_PINJAM);
        }

        $this->buku->simpan($item->denganStokTersedia($item->tersedia - 1));
        $this->pinjaman[$idAnggota][$isbn] = true;
    }

    public function sisaEksemplar(string $isbn): int
    {
        return $this->cariBuku($isbn)->tersedia;
    }

    public function judulDari(string $isbn): ?string
    {
        return $this->buku->cariByIsbn($isbn)?->judul;
    }

    /** @return Buku[] */
    public function daftarBukuTersedia(): array
    {
        return $this->buku->filter(static fn (Buku $item): bool => $item->tersedia > 0)->semua();
    }

    public function daftarJudul(): string
    {
        return implode(', ', $this->buku->map(static fn (Buku $item): string => $item->judul));
    }

    /** @return array<string, int> */
    public function jumlahBukuPerPenulis(): array
    {
        $hasil = [];
        foreach ($this->buku->kelompokkan(static fn (Buku $item): string => $item->penulis) as $penulis => $items) {
            $hasil[$penulis] = count($items);
        }
        return $hasil;
    }

    private function cariBuku(string $isbn): Buku
    {
        return $this->buku->cariByIsbn($isbn) ?? throw new BukuTidakDitemukanException($isbn);
    }

    private function validasiIdAnggota(string $idAnggota): void
    {
        if (preg_match('/^[1-9]\d*$/', $idAnggota) !== 1) {
            throw new \InvalidArgumentException('ID anggota harus berupa angka positif');
        }
    }
}
