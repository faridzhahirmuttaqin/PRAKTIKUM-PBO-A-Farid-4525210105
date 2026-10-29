<?php
declare(strict_types=1);

namespace Perpustakaan\Service;

use Carbon\CarbonImmutable;
use Perpustakaan\Model\Anggota;
use Perpustakaan\Model\Buku;
use Perpustakaan\Model\Peminjaman;

final class PerpustakaanService
{
    private const BATAS_PINJAMAN_AKTIF = 3;

    /** @var array<string, Buku> */
    private array $koleksi = [];

    /** @var array<string, Peminjaman> */
    private array $peminjaman = [];

    private int $nomorPeminjaman = 0;

    public function tambahKoleksi(Buku $buku): void
    {
        $nomorInduk = $buku->getNomorInduk();
        if (trim($nomorInduk) === '') {
            throw new \InvalidArgumentException('Nomor induk tidak boleh kosong');
        }
        if (isset($this->koleksi[$nomorInduk])) {
            throw new \InvalidArgumentException('Nomor induk sudah terdaftar: ' . $nomorInduk);
        }
        $this->koleksi[$nomorInduk] = $buku;
    }

    public function cariKoleksi(string $nomorInduk): ?Buku
    {
        return $this->koleksi[$nomorInduk] ?? null;
    }

    public function pinjam(Anggota $anggota, string $nomorInduk, int $lamaHari = 7): Peminjaman
    {
        if ($lamaHari < 1) {
            throw new \InvalidArgumentException('Lama peminjaman minimal satu hari');
        }
        $buku = $this->cariKoleksi($nomorInduk);
        if ($buku === null) {
            throw new \InvalidArgumentException('Koleksi tidak ditemukan: ' . $nomorInduk);
        }

        $pinjamanAktifAnggota = array_filter(
            $this->peminjaman,
            static fn (Peminjaman $pinjaman): bool =>
                $pinjaman->getAnggota()->getNomorAnggota() === $anggota->getNomorAnggota()
                && !$pinjaman->sudahDikembalikan(),
        );
        if (count($pinjamanAktifAnggota) >= self::BATAS_PINJAMAN_AKTIF) {
            throw new \LogicException('Anggota tidak boleh meminjam lebih dari tiga koleksi');
        }

        $buku->kurangiStok();
        $tanggalPinjam = CarbonImmutable::now();
        $id = sprintf('PJM-%04d', ++$this->nomorPeminjaman);
        $pinjaman = new Peminjaman(
            $id,
            $anggota,
            $buku,
            $tanggalPinjam,
            $tanggalPinjam->addDays($lamaHari),
        );
        $this->peminjaman[$id] = $pinjaman;

        return $pinjaman;
    }

    public function kembalikan(string $idPeminjaman): void
    {
        $pinjaman = $this->peminjaman[$idPeminjaman] ?? null;
        if ($pinjaman === null) {
            throw new \InvalidArgumentException('Peminjaman tidak ditemukan: ' . $idPeminjaman);
        }
        if ($pinjaman->sudahDikembalikan()) {
            throw new \LogicException('Peminjaman sudah dikembalikan');
        }

        $pinjaman->kembalikan(CarbonImmutable::now());
        $pinjaman->getBuku()->tambahStok();
    }
}