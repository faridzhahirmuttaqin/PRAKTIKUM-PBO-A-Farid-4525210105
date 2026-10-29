<?php
declare(strict_types=1);

namespace Perpustakaan\Model;

final class Anggota
{
    public function __construct(
        private readonly string $nomorAnggota,
        private readonly string $nama,
    ) {
        if (trim($this->nomorAnggota) === '' || trim($this->nama) === '') {
            throw new \InvalidArgumentException('Nomor anggota dan nama wajib diisi');
        }
    }

    public function getNomorAnggota(): string
    {
        return $this->nomorAnggota;
    }

    public function getNama(): string
    {
        return $this->nama;
    }
}