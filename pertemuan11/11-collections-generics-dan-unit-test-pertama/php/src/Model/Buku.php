<?php
declare(strict_types=1);

namespace Perpustakaan\Model;

final class Buku
{
    public function __construct(
        public readonly string $isbn,
        public readonly string $judul,
        public readonly string $penulis,
        public readonly int $totalEksemplar,
        public readonly int $tersedia,
    ) {
        if (trim($isbn) === '' || trim($judul) === '' || trim($penulis) === '') {
            throw new \InvalidArgumentException('ISBN, judul, dan penulis wajib diisi');
        }
        if ($totalEksemplar < 0 || $tersedia < 0 || $tersedia > $totalEksemplar) {
            throw new \InvalidArgumentException('Jumlah eksemplar buku tidak valid');
        }
    }

    public function denganStokTersedia(int $stokBaru): self
    {
        return new self($this->isbn, $this->judul, $this->penulis, $this->totalEksemplar, $stokBaru);
    }
}
