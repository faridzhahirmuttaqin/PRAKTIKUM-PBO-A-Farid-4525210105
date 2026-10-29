<?php
declare(strict_types=1);

require __DIR__ . '/vendor/autoload.php';

use Perpustakaan\Model\Anggota;
use Perpustakaan\Model\Buku;
use Perpustakaan\Model\Jurusan;
use Perpustakaan\Model\Mahasiswa;
use Perpustakaan\Service\PerpustakaanService;
use Perpustakaan\Util\Format;

echo "=== Komposisi: Buku memiliki Bab ===", PHP_EOL;
$buku = new Buku('PBO-001', 'Pemrograman Berorientasi Objek', 'A. W. Pribadi', 3);
$buku->tambahBab(1, 'Paradigma Objek');
$buku->tambahBab(2, 'Enkapsulasi');
echo '  ', $buku, PHP_EOL;
foreach ($buku->getBab() as $bab) {
    echo '    ', $bab, PHP_EOL;
}

echo PHP_EOL, "=== Agregasi: Jurusan menaungi Mahasiswa ===", PHP_EOL;
$mahasiswa = new Mahasiswa('2024001', 'Ani Lestari');
$jurusan = new Jurusan('TI', 'Teknik Informatika');
$jurusan->daftarkan($mahasiswa);
echo '  ', $jurusan, PHP_EOL;

echo PHP_EOL, "=== Peminjaman dengan jatuh tempo ===", PHP_EOL;
$anggota = new Anggota('A-001', 'Ani Lestari');
$service = new PerpustakaanService();
$service->tambahKoleksi($buku);
$peminjaman = $service->pinjam($anggota, 'PBO-001', 7);
echo '  ID peminjaman: ', $peminjaman->getId(), PHP_EOL;
echo '  Jatuh tempo: ', $peminjaman->getTanggalJatuhTempo()->format('d-m-Y'), PHP_EOL;
echo '  Denda per hari: ', Format::rupiah(5000), PHP_EOL;