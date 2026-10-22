<?php
declare(strict_types=1);

/**
 * Sesi 7 — komposisi dan agregasi (PHP).
 * Bandingkan dengan versi Java.
 */

final class Bab
{
    public function __construct(
        private readonly int $nomor,
        private readonly string $judul,
    ) {}

    public function __toString(): string
    {
        return 'Bab ' . $this->nomor . ': ' . $this->judul;
    }
}

final class Buku
{
    /** @var Bab[] */
    private array $bab = [];

    public function __construct(
        private readonly string $nomorInduk,
        private readonly string $judul,
        private readonly string $penulis,
        private int $jumlahEksemplar,
    ) {
        if ($this->jumlahEksemplar < 0) {
            throw new InvalidArgumentException('Jumlah eksemplar tidak boleh negatif');
        }
    }

    /** Bab dibuat di dalam Buku, bukan diberikan sebagai objek dari luar. */
    public function tambahBab(int $nomor, string $judulBab): void
    {
        $this->bab[] = new Bab($nomor, $judulBab);
    }

    /**
     * PHP mengembalikan array secara by-value, sehingga pemanggil hanya
     * memperoleh salinan larik-nya. Namun objek DI DALAMNYA tetap berbagi
     * referensi — itulah sebabnya Bab dibuat readonly.
     *
     * @return Bab[]
     */
    public function getBab(): array { return $this->bab; }

    public function tersedia(): bool { return $this->jumlahEksemplar > 0; }

    public function kurangiStok(): void
    {
        if ($this->jumlahEksemplar <= 0) {
            throw new LogicException('Tidak ada eksemplar yang tersedia');
        }
        $this->jumlahEksemplar--;
    }

    public function tambahStok(): void { $this->jumlahEksemplar++; }

    public function getNomorInduk(): string { return $this->nomorInduk; }
    public function getJudul(): string { return $this->judul; }

    public function __toString(): string
    {
        return sprintf('%-32s %-16s [%d eks, %d bab]',
            $this->judul, $this->penulis, $this->jumlahEksemplar, count($this->bab));
    }
}

final class Mahasiswa
{
    public function __construct(
        private readonly string $nim,
        private readonly string $nama,
    ) {}

    public function getNim(): string { return $this->nim; }
    public function __toString(): string { return $this->nim . ' ' . $this->nama; }
}

final class Jurusan
{
    /** @var Mahasiswa[] */
    private array $mahasiswa = [];

    public function __construct(
        private readonly string $kode,
        private readonly string $nama,
    ) {}

    /** Terima objek dari luar (agregasi) dan hindari pendaftaran ganda. */
    public function daftarkan(Mahasiswa $m): void
    {
        if (!in_array($m, $this->mahasiswa, true)) {
            $this->mahasiswa[] = $m;
        }
    }

    /** Lepaskan mahasiswa dari jurusan tanpa menghapus objeknya. */
    public function keluarkan(Mahasiswa $m): void
    {
        $index = array_search($m, $this->mahasiswa, true);
        if ($index !== false) {
            array_splice($this->mahasiswa, $index, 1);
        }
    }

    /** @return Mahasiswa[] */
    public function getMahasiswa(): array { return $this->mahasiswa; }

    public function __toString(): string
    {
        return sprintf('%s - %s (%d mahasiswa)', $this->kode, $this->nama, count($this->mahasiswa));
    }
}
