<?php
declare(strict_types=1);

namespace Perpustakaan\Exception;

final class BukuSedangDipinjamException extends \RuntimeException
{
    public function __construct(string $isbn, string $judul)
    {
        parent::__construct("Buku \"{$judul}\" (ISBN {$isbn}) sedang dipinjam atau tidak tersedia");
    }
}
