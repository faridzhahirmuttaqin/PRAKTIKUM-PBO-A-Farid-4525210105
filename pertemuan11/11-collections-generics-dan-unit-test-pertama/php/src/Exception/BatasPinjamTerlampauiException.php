<?php
declare(strict_types=1);

namespace Perpustakaan\Exception;

final class BatasPinjamTerlampauiException extends \RuntimeException
{
    public function __construct(string $idAnggota, int $batas)
    {
        parent::__construct("Anggota {$idAnggota} telah mencapai batas {$batas} buku");
    }
}
