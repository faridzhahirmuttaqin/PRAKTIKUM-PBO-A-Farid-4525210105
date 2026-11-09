<?php
declare(strict_types=1);

namespace Perpustakaan\Service;

use Perpustakaan\Exception\AnggotaTidakDitemukanException;
use Perpustakaan\Exception\BatasPinjamTerlampauiException;
use Perpustakaan\Exception\BukuSedangDipinjamException;
use Perpustakaan\Exception\BukuTidakDitemukanException;

final class LibraryService
{
    public const BATAS_PINJAM = 3;

    /** @var array<string, array{judul: string, tersedia: int}> */
    private array $buku = [];
    /** @var array<string, true> */
    private array $anggota = [];
    /** @var array<string, array<string, true>> */
    private array $pinjaman = [];

    public function tambahBuku(string $isbn, string $judul, int $eksemplar): void
    {
        $this->validasiTeks($isbn, 'ISBN');
        $this->validasiTeks($judul, 'Judul');
        if ($eksemplar < 0) {
            throw new \InvalidArgumentException('Jumlah eksemplar tidak boleh negatif');
        }
        if (isset($this->buku[$isbn])) {
            throw new \InvalidArgumentException("ISBN sudah terdaftar: {$isbn}");
        }
        $this->buku[$isbn] = ['judul' => $judul, 'tersedia' => $eksemplar];
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
        $this->validasiTeks($isbn, 'ISBN');
        if (!isset($this->anggota[$idAnggota])) {
            throw new AnggotaTidakDitemukanException($idAnggota);
        }
        if (!isset($this->buku[$isbn])) {
            throw new BukuTidakDitemukanException($isbn);
        }

        $buku = $this->buku[$isbn];
        if (isset($this->pinjaman[$idAnggota][$isbn]) || $buku['tersedia'] === 0) {
            throw new BukuSedangDipinjamException($isbn, $buku['judul']);
        }
        if (count($this->pinjaman[$idAnggota]) >= self::BATAS_PINJAM) {
            throw new BatasPinjamTerlampauiException($idAnggota, self::BATAS_PINJAM);
        }

        $this->buku[$isbn]['tersedia']--;
        $this->pinjaman[$idAnggota][$isbn] = true;
    }

    public function sisaEksemplar(string $isbn): int
    {
        if (!isset($this->buku[$isbn])) {
            throw new BukuTidakDitemukanException($isbn);
        }
        return $this->buku[$isbn]['tersedia'];
    }

    public function judulDari(string $isbn): ?string
    {
        return $this->buku[$isbn]['judul'] ?? null;
    }

    public function exportDaftarBuku(string $tujuan, bool $simulasikanKegagalan = false): void
    {
        $berkas = fopen($tujuan, 'wb');
        if ($berkas === false) {
            throw new \RuntimeException("Berkas ekspor tidak dapat dibuka: {$tujuan}");
        }

        try {
            if (fwrite($berkas, "ISBN | Judul | Tersedia\n") === false) {
                throw new \RuntimeException('Gagal menulis kepala berkas ekspor');
            }
            $jumlah = 0;
            foreach ($this->buku as $isbn => $buku) {
                $baris = "{$isbn} | {$buku['judul']} | {$buku['tersedia']}\n";
                if (fwrite($berkas, $baris) === false) {
                    throw new \RuntimeException('Gagal menulis daftar buku');
                }
                if ($simulasikanKegagalan && ++$jumlah === 1) {
                    throw new \RuntimeException('Kegagalan ekspor simulasi setelah baris pertama');
                }
            }
        } finally {
            fclose($berkas);
        }
    }

    private function validasiTeks(string $nilai, string $nama): void
    {
        if (trim($nilai) === '') {
            throw new \InvalidArgumentException("{$nama} tidak boleh kosong");
        }
        if (preg_match('/^-\d+$/', $nilai) === 1) {
            throw new \InvalidArgumentException("{$nama} tidak boleh bernilai negatif");
        }
    }

    private function validasiIdAnggota(string $idAnggota): void
    {
        if (preg_match('/^[1-9]\d*$/', $idAnggota) !== 1) {
            throw new \InvalidArgumentException('ID anggota harus berupa angka positif');
        }
    }
}
