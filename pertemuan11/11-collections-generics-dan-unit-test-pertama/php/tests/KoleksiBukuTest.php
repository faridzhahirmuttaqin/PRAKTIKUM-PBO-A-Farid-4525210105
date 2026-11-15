<?php
declare(strict_types=1);

namespace Perpustakaan\Tests;

use Perpustakaan\Exception\AnggotaTidakDitemukanException;
use Perpustakaan\Exception\BatasPinjamTerlampauiException;
use Perpustakaan\Exception\BukuSedangDipinjamException;
use Perpustakaan\Exception\BukuTidakDitemukanException;
use Perpustakaan\Model\Buku;
use Perpustakaan\Model\KoleksiBuku;
use Perpustakaan\Service\LibraryService;
use PHPUnit\Framework\TestCase;

final class KoleksiBukuTest extends TestCase
{
    private KoleksiBuku $koleksi;

    protected function setUp(): void
    {
        $this->koleksi = new KoleksiBuku();
    }

    public function testItemTersimpanDapatDitemukan(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO', 'Farid', 1, 1));
        self::assertSame('PBO', $this->koleksi->cariByIsbn('978-1')?->judul);
    }

    public function testIsbnTidakAdaMenghasilkanNull(): void
    {
        self::assertNull($this->koleksi->cariByIsbn('tidak-ada'));
    }

    public function testKoleksiBaruKosong(): void
    {
        self::assertCount(0, $this->koleksi);
    }

    public function testFilterHanyaMengembalikanItemYangMemenuhiPredikat(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO', 'Farid', 1, 1));
        $this->koleksi->simpan(new Buku('978-2', 'Algoritma', 'Ayu', 1, 0));
        self::assertSame(['978-1'], array_map(
            static fn (Buku $item): string => $item->isbn,
            $this->koleksi->filter(static fn (Buku $item): bool => $item->tersedia > 0)->semua(),
        ));
    }

    public function testJumlahkanMenghitungTotalStok(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO', 'Farid', 2, 2));
        $this->koleksi->simpan(new Buku('978-2', 'Algoritma', 'Ayu', 3, 1));
        self::assertSame(3.0, $this->koleksi->jumlahkan(static fn (Buku $item): int => $item->tersedia));
    }

    public function testMapDanKelompokkan(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO', 'Farid', 1, 1));
        $this->koleksi->simpan(new Buku('978-2', 'Java', 'Farid', 1, 1));
        self::assertSame(['PBO', 'Java'], $this->koleksi->map(static fn (Buku $item): string => $item->judul));
        self::assertCount(2, $this->koleksi->kelompokkan(static fn (Buku $item): string => $item->penulis)['Farid']);
    }

    public function testMeminjamBukuYangTidakAdaMelemparExceptionTepat(): void
    {
        $service = new LibraryService();
        $service->daftarkanAnggota('1001');
        $this->expectException(BukuTidakDitemukanException::class);
        $service->pinjam('1001', 'ISBN-TIDAK-ADA');
    }

    public function testAnggotaTidakTerdaftarDitolak(): void
    {
        $service = new LibraryService();
        $service->tambahBuku('978-1', 'PBO', 'Farid', 1);
        $this->expectException(AnggotaTidakDitemukanException::class);
        $service->pinjam('9999', '978-1');
    }

    public function testPeminjamanDuplikatDitolak(): void
    {
        $service = new LibraryService();
        $service->tambahBuku('978-1', 'PBO', 'Farid', 2);
        $service->daftarkanAnggota('1001');
        $service->pinjam('1001', '978-1');
        $this->expectException(BukuSedangDipinjamException::class);
        $service->pinjam('1001', '978-1');
    }

    public function testBatasTigaPeminjamanDitegakkan(): void
    {
        $service = new LibraryService();
        $service->daftarkanAnggota('1001');
        for ($i = 1; $i <= 4; $i++) {
            $service->tambahBuku("978-{$i}", "Judul {$i}", 'Penulis', 1);
        }
        $service->pinjam('1001', '978-1');
        $service->pinjam('1001', '978-2');
        $service->pinjam('1001', '978-3');
        $this->expectException(BatasPinjamTerlampauiException::class);
        $service->pinjam('1001', '978-4');
    }

    public function testIdAnggotaNegatifDitolak(): void
    {
        $service = new LibraryService();
        $this->expectException(\InvalidArgumentException::class);
        $service->daftarkanAnggota('-5');
    }

    public function testLaporanBukuTersediaJudulDanPenulis(): void
    {
        $service = new LibraryService();
        $service->tambahBuku('978-1', 'PBO', 'Farid', 1);
        $service->tambahBuku('978-2', 'Algoritma', 'Farid', 0);
        $service->tambahBuku('978-3', 'Basis Data', 'Ayu', 2);
        self::assertCount(2, $service->daftarBukuTersedia());
        self::assertSame('PBO, Algoritma, Basis Data', $service->daftarJudul());
        self::assertSame(['Farid' => 2, 'Ayu' => 1], $service->jumlahBukuPerPenulis());
    }
}
