<?php
declare(strict_types=1);

require __DIR__ . '/tests/bootstrap.php';

use Perpustakaan\Model\Buku;
use Perpustakaan\Service\LibraryService;

$perpustakaan = new LibraryService();
$perpustakaan->tambahBuku('978-1', 'Pemrograman Java', 'Farid', 2);
$perpustakaan->tambahBuku('978-2', 'Struktur Data', 'Ayu', 1);
$perpustakaan->tambahBuku('978-3', 'Basis Data', 'Farid', 0);

echo "=== Demo Collections dan Generics (PHP) ===\n";
echo 'Buku tersedia: ' . implode(', ', array_map(
    static fn (Buku $buku): string => $buku->judul,
    $perpustakaan->daftarBukuTersedia(),
)) . "\n";
echo 'Daftar judul: ' . $perpustakaan->daftarJudul() . "\n";
echo 'Jumlah judul per penulis: ' . json_encode(
    $perpustakaan->jumlahBukuPerPenulis(),
    JSON_THROW_ON_ERROR | JSON_UNESCAPED_UNICODE,
) . "\n";

$perpustakaan->daftarkanAnggota('1001');
$perpustakaan->pinjam('1001', '978-1');
echo 'Sisa eksemplar Pemrograman Java setelah dipinjam: '
    . $perpustakaan->sisaEksemplar('978-1') . "\n";
