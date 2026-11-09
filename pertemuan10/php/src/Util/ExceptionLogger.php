<?php
declare(strict_types=1);

namespace Perpustakaan\Util;

use Perpustakaan\Exception\AnggotaTidakDitemukanException;
use Perpustakaan\Exception\BatasPinjamTerlampauiException;
use Perpustakaan\Exception\BukuSedangDipinjamException;
use Perpustakaan\Exception\BukuTidakDitemukanException;

final class ExceptionLogger
{
    public static function catat(\Throwable $exception, string $tujuan): void
    {
        $data = match (true) {
            $exception instanceof BukuTidakDitemukanException => 'isbn=' . $exception->getIsbn(),
            $exception instanceof AnggotaTidakDitemukanException => 'idAnggota=' . $exception->getIdAnggota(),
            $exception instanceof BukuSedangDipinjamException =>
                'isbn=' . $exception->getIsbn() . ', judul=' . $exception->getJudul(),
            $exception instanceof BatasPinjamTerlampauiException =>
                'idAnggota=' . $exception->getIdAnggota() . ', batas=' . $exception->getBatas(),
            default => 'pesan=' . $exception->getMessage(),
        };
        $direktori = dirname($tujuan);
        if (!is_dir($direktori) && !mkdir($direktori, 0777, true) && !is_dir($direktori)) {
            throw new \RuntimeException("Direktori log tidak dapat dibuat: {$direktori}");
        }
        $baris = sprintf(
            "%s | %s | %s%s",
            (new \DateTimeImmutable())->format(DATE_ATOM),
            $exception::class,
            $data,
            PHP_EOL,
        );
        if (file_put_contents($tujuan, $baris, FILE_APPEND | LOCK_EX) === false) {
            throw new \RuntimeException("Log tidak dapat ditulis: {$tujuan}");
        }
    }
}
