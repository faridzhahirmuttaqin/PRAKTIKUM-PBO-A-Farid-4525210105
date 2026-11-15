<?php
declare(strict_types=1);

namespace Perpustakaan\Exception;

final class BukuTidakDitemukanException extends \RuntimeException
{
    public function __construct(string $isbn)
    {
        parent::__construct("Buku dengan ISBN {$isbn} tidak ditemukan");
    }
}
