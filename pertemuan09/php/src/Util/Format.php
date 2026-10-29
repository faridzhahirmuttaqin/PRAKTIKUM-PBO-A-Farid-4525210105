<?php
declare(strict_types=1);

namespace Perpustakaan\Util;

final class Format
{
    public static function rupiah(int $jumlah): string
    {
        return 'Rp ' . number_format($jumlah, 0, ',', '.');
    }
}