package id.ac.univpancasila.perpustakaan;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    private Repository<Buku> repository;

    @BeforeEach
    void setUp() {
        repository = new Repository<>(Buku::isbn);
    }

    @Test
    void simpanDanCariBuku() {
        repository.simpan(new Buku("978-1", "PBO", "Farid", 1, 1));
        assertEquals("PBO", repository.cariById("978-1").orElseThrow().judul());
    }

    @Test
    void pencarianTidakAdaMenghasilkanOptionalKosong() {
        assertTrue(repository.cariById("tidak-ada").isEmpty());
    }

    @Test
    void hapusMengembalikanStatusDanMenghilangkanItem() {
        repository.simpan(new Buku("978-1", "PBO", "Farid", 1, 1));
        assertTrue(repository.hapus("978-1"));
        assertFalse(repository.hapus("978-1"));
        assertTrue(repository.cariById("978-1").isEmpty());
    }

    @Test
    void repositoryKosongMemilikiJumlahNol() {
        assertEquals(0, repository.jumlah());
        assertTrue(repository.cariSemua().isEmpty());
    }

    @Test
    void daftarSemuaTakDapatDiubahDariLuar() {
        repository.simpan(new Buku("978-1", "PBO", "Farid", 1, 1));
        assertThrows(UnsupportedOperationException.class,
                () -> repository.cariSemua().add(new Buku("978-2", "Java", "Ayu", 1, 1)));
    }

    @Test
    void itemDenganIdSamaMenimpaItemLama() {
        repository.simpan(new Buku("978-1", "PBO", "Farid", 1, 1));
        repository.simpan(new Buku("978-1", "PBO Lanjut", "Ayu", 1, 1));
        assertEquals(1, repository.jumlah());
        assertEquals("PBO Lanjut", repository.cariById("978-1").orElseThrow().judul());
    }

    @Test
    void peminjamanIsbnTidakTerdaftarMelemparExceptionYangTepat() {
        LibraryService service = new LibraryService();
        service.daftarkanAnggota("1001");
        assertThrows(BukuTidakDitemukanException.class, () -> service.pinjam("1001", "ISBN-TIDAK-ADA"));
    }

    @Test
    void anggotaTidakTerdaftarDitolak() {
        LibraryService service = new LibraryService();
        service.tambahBuku("978-1", "PBO", "Farid", 1);
        assertThrows(AnggotaTidakDitemukanException.class, () -> service.pinjam("9999", "978-1"));
    }

    @Test
    void pencarianJudulTidakMengembalikanNull() {
        assertTrue(new LibraryService().cariByIsbn("tidak-ada").isEmpty());
    }
}
