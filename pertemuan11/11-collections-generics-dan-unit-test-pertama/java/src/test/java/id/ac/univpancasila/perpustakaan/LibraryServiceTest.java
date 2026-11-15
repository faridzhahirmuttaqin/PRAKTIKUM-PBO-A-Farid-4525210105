package id.ac.univpancasila.perpustakaan;

import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {
    @Test
    void laporanTersediaJudulDanPengelompokanPenulisBenar() {
        LibraryService service = new LibraryService();
        service.tambahBuku("978-1", "PBO", "Farid", 1);
        service.tambahBuku("978-2", "Algoritma", "Farid", 0);
        service.tambahBuku("978-3", "Basis Data", "Ayu", 2);

        assertEquals(2, service.daftarBukuTersedia().size());
        assertEquals("PBO, Algoritma, Basis Data", service.daftarJudul());
        assertEquals(2L, service.jumlahBukuPerPenulis().get("Farid"));
        assertEquals(1L, service.jumlahBukuPerPenulis().get("Ayu"));
    }

    @Test
    void peminjamanMengurangiStokDanDuplikatDitolak() {
        LibraryService service = new LibraryService();
        service.tambahBuku("978-1", "PBO", "Farid", 1);
        service.daftarkanAnggota("1001");
        service.pinjam("1001", "978-1");

        assertEquals(0, service.sisaEksemplar("978-1"));
        assertThrows(BukuSedangDipinjamException.class, () -> service.pinjam("1001", "978-1"));
    }

    @Test
    void batasPinjamMaksimalTigaBuku() {
        LibraryService service = new LibraryService();
        service.daftarkanAnggota("1001");
        for (int i = 1; i <= 4; i++) {
            service.tambahBuku("978-" + i, "Judul " + i, "Penulis", 1);
        }
        service.pinjam("1001", "978-1");
        service.pinjam("1001", "978-2");
        service.pinjam("1001", "978-3");

        assertThrows(BatasPinjamTerlampauiException.class, () -> service.pinjam("1001", "978-4"));
    }
}
