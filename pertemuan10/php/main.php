<?php
declare(strict_types=1);

require __DIR__ . '/vendor/autoload.php';

use Perpustakaan\Exception\AnggotaTidakDitemukanException;
use Perpustakaan\Exception\BatasPinjamTerlampauiException;
use Perpustakaan\Exception\BukuSedangDipinjamException;
use Perpustakaan\Exception\BukuTidakDitemukanException;
use Perpustakaan\Exception\PerpustakaanException;
use Perpustakaan\Service\LibraryService;
use Perpustakaan\Util\ExceptionLogger;

const LOG_FILE = __DIR__ . '/logs/perpustakaan.log';

function layananContoh(): LibraryService
{
    $service = new LibraryService();
    $service->tambahBuku('978-602-001-001-1', 'Pemrograman Berorientasi Objek', 2);
    $service->tambahBuku('978-602-001-002-8', 'Dasar Algoritma', 1);
    $service->tambahBuku('978-602-001-003-5', 'Struktur Data', 1);
    $service->tambahBuku('978-602-001-004-2', 'Basis Data', 1);
    $service->daftarkanAnggota('1001');
    $service->daftarkanAnggota('1002');
    return $service;
}

function jalankanKonsol(LibraryService $service): void
{
    echo "Sistem Perpustakaan — ketik 0 untuk keluar.", PHP_EOL;
    while (true) {
        echo PHP_EOL, "1. Pinjam buku", PHP_EOL, "2. Ekspor daftar buku", PHP_EOL, "Pilihan: ";
        $pilihan = trim((string) fgets(STDIN));
        if ($pilihan === '0') {
            return;
        }
        if ($pilihan === '1') {
            echo 'ID anggota (angka positif): ';
            $idAnggota = trim((string) fgets(STDIN));
            if (preg_match('/^[1-9]\d*$/', $idAnggota) !== 1) {
                echo 'Masukan tidak valid: ID anggota harus berupa angka positif.', PHP_EOL;
                continue;
            }
            echo 'ISBN (kosong atau 0 untuk batal): ';
            $isbn = trim((string) fgets(STDIN));
            if ($isbn === '' || $isbn === '0') {
                echo 'Masukan ISBN kosong atau dibatalkan.', PHP_EOL;
                continue;
            }
            if (preg_match('/^-\d+$/', $isbn) === 1) {
                echo 'Masukan tidak valid: ISBN tidak boleh bernilai negatif.', PHP_EOL;
                continue;
            }

            try {
                $service->pinjam($idAnggota, $isbn);
                echo 'Peminjaman berhasil: ', $service->judulDari($isbn), PHP_EOL;
            } catch (PerpustakaanException $exception) {
                ExceptionLogger::catat($exception, LOG_FILE);
                echo 'Peminjaman gagal: ', $exception->getMessage(), PHP_EOL;
            }
            continue;
        }
        if ($pilihan === '2') {
            try {
                $service->exportDaftarBuku(__DIR__ . '/data/daftar-buku.txt');
                echo 'Daftar buku berhasil diekspor.', PHP_EOL;
            } catch (\RuntimeException $exception) {
                ExceptionLogger::catat($exception, LOG_FILE);
                echo 'Ekspor gagal: ', $exception->getMessage(), PHP_EOL;
            }
            continue;
        }
        echo 'Pilihan tidak valid. Masukkan 1, 2, atau 0.', PHP_EOL;
    }
}

/**
 * @param class-string<\Throwable> $jenisDiharapkan
 */
function ujiException(string $nama, string $jenisDiharapkan, callable $tindakan, int &$lulus): void
{
    try {
        $tindakan();
        throw new AssertionError("Tidak ada exception pada skenario: {$nama}");
    } catch (Throwable $exception) {
        ExceptionLogger::catat($exception, LOG_FILE);
        if (!$exception instanceof $jenisDiharapkan) {
            throw new RuntimeException(
                "Skenario {$nama} mengharapkan {$jenisDiharapkan}, tetapi mendapat " . $exception::class,
                0,
                $exception,
            );
        }
        $lulus++;
        echo 'LULUS — ', $nama, ' -> ', $exception::class, PHP_EOL;
    }
}

function jalankanSkenario(): void
{
    $lulus = 0;
    ujiException('ISBN tidak ditemukan', BukuTidakDitemukanException::class,
        static fn () => layananContoh()->pinjam('1001', 'ISBN-TIDAK-ADA'), $lulus);
    ujiException('anggota tidak terdaftar', AnggotaTidakDitemukanException::class,
        static fn () => layananContoh()->pinjam('9999', '978-602-001-001-1'), $lulus);
    ujiException('ISBN negatif', InvalidArgumentException::class,
        static fn () => layananContoh()->pinjam('1001', '-5'), $lulus);
    ujiException('ID anggota negatif', InvalidArgumentException::class,
        static fn () => layananContoh()->pinjam('-5', '978-602-001-001-1'), $lulus);
    ujiException('ID anggota kosong', InvalidArgumentException::class,
        static fn () => layananContoh()->pinjam(' ', '978-602-001-001-1'), $lulus);
    ujiException('ISBN kosong', InvalidArgumentException::class,
        static fn () => layananContoh()->pinjam('1001', ' '), $lulus);
    ujiException('ID berisi huruf', InvalidArgumentException::class,
        static fn () => layananContoh()->pinjam('abc', '978-602-001-001-1'), $lulus);
    ujiException('buku sama dipinjam dua kali', BukuSedangDipinjamException::class, static function (): void {
        $service = layananContoh();
        $service->pinjam('1001', '978-602-001-001-1');
        $service->pinjam('1001', '978-602-001-001-1');
    }, $lulus);
    ujiException('seluruh eksemplar sedang dipinjam', BukuSedangDipinjamException::class, static function (): void {
        $service = new LibraryService();
        $service->tambahBuku('B-1', 'Buku Satu', 1);
        $service->daftarkanAnggota('1001');
        $service->daftarkanAnggota('1002');
        $service->daftarkanAnggota('1003');
        $service->pinjam('1001', 'B-1');
        $service->pinjam('1002', 'B-1');
        $service->pinjam('1003', 'B-1');
    }, $lulus);
    ujiException('batas pinjam terlampaui', BatasPinjamTerlampauiException::class, static function (): void {
        $service = new LibraryService();
        foreach (['B-1', 'B-2', 'B-3', 'B-4'] as $nomor => $isbn) {
            $service->tambahBuku($isbn, 'Buku ' . $nomor, 1);
        }
        $service->daftarkanAnggota('1002');
        $service->pinjam('1002', 'B-1');
        $service->pinjam('1002', 'B-2');
        $service->pinjam('1002', 'B-3');
        $service->pinjam('1002', 'B-4');
    }, $lulus);

    $sementara = tempnam(sys_get_temp_dir(), 'perpustakaan-ekspor-');
    if ($sementara === false) {
        throw new RuntimeException('Berkas sementara untuk pengujian tidak dapat dibuat');
    }
    $dipindah = $sementara . '.closed';
    try {
        ujiException('berkas tetap tertutup saat ekspor gagal', RuntimeException::class,
            static fn () => layananContoh()->exportDaftarBuku($sementara, true), $lulus);
        if (!rename($sementara, $dipindah) || !unlink($dipindah)) {
            throw new RuntimeException('Berkas ekspor gagal dipindahkan/dihapus setelah finally');
        }
        layananContoh()->exportDaftarBuku($sementara);
        if (file_get_contents($sementara) === false) {
            throw new RuntimeException('Berkas ekspor normal tidak dapat dibaca kembali');
        }
        unlink($sementara);
        $lulus++;
        echo 'LULUS — ekspor normal menghasilkan berkas dan berkas dapat dibuka kembali', PHP_EOL;
    } finally {
        if (is_file($sementara)) {
            unlink($sementara);
        }
        if (is_file($dipindah)) {
            unlink($dipindah);
        }
    }
    echo "Selesai: {$lulus} skenario lulus.", PHP_EOL;
}

if (($argv[1] ?? '') === '--self-test') {
    jalankanSkenario();
} else {
    jalankanKonsol(layananContoh());
}
