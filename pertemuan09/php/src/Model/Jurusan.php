<?php
declare(strict_types=1);

namespace Perpustakaan\Model;

final class Jurusan
{
    /** @var Mahasiswa[] */
    private array $mahasiswa = [];

    public function __construct(
        private readonly string $kode,
        private readonly string $nama,
    ) {}

    public function daftarkan(Mahasiswa $mahasiswa): void
    {
        if (!in_array($mahasiswa, $this->mahasiswa, true)) {
            $this->mahasiswa[] = $mahasiswa;
        }
    }

    public function keluarkan(Mahasiswa $mahasiswa): void
    {
        $index = array_search($mahasiswa, $this->mahasiswa, true);
        if ($index !== false) {
            array_splice($this->mahasiswa, $index, 1);
        }
    }

    /** @return Mahasiswa[] */
    public function getMahasiswa(): array
    {
        return $this->mahasiswa;
    }

    public function __toString(): string
    {
        return sprintf('%s - %s (%d mahasiswa)', $this->kode, $this->nama, count($this->mahasiswa));
    }
}