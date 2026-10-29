<?php
declare(strict_types=1);

namespace Perpustakaan\Model;

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
            throw new \InvalidArgumentException('Jumlah eksemplar tidak boleh negatif');
        }
    }

    public function tambahBab(int $nomor, string $judulBab): void
    {
        $this->bab[] = new Bab($nomor, $judulBab);
    }

    /** @return Bab[] */
    public function getBab(): array
    {
        return $this->bab;
    }

    public function tersedia(): bool
    {
        return $this->jumlahEksemplar > 0;
    }

    public function kurangiStok(): void
    {
        if (!$this->tersedia()) {
            throw new \LogicException('Tidak ada eksemplar yang tersedia');
        }
        $this->jumlahEksemplar--;
    }

    public function tambahStok(): void
    {
        $this->jumlahEksemplar++;
    }

    public function getNomorInduk(): string
    {
        return $this->nomorInduk;
    }

    public function getJudul(): string
    {
        return $this->judul;
    }

    public function getJumlahEksemplar(): int
    {
        return $this->jumlahEksemplar;
    }

    public function __toString(): string
    {
        return sprintf('%-32s %-16s [%d eks, %d bab]',
            $this->judul, $this->penulis, $this->jumlahEksemplar, count($this->bab));
    }
}