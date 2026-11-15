package id.ac.univpancasila.perpustakaan;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class LibraryService {
    public static final int BATAS_PINJAM = 3;

    private final Repository<Buku> buku = new Repository<>(Buku::isbn);
    private final Set<String> anggota = new LinkedHashSet<>();
    private final Map<String, Set<String>> pinjaman = new LinkedHashMap<>();

    public void tambahBuku(String isbn, String judul, String penulis, int eksemplar) {
        Buku data = new Buku(isbn, judul, penulis, eksemplar, eksemplar);
        if (buku.cariById(isbn).isPresent()) {
            throw new IllegalArgumentException("ISBN sudah terdaftar: " + isbn);
        }
        buku.simpan(data);
    }

    public void daftarkanAnggota(String idAnggota) {
        validasiIdAnggota(idAnggota);
        anggota.add(idAnggota);
        pinjaman.computeIfAbsent(idAnggota, ignored -> new LinkedHashSet<>());
    }

    public void pinjam(String idAnggota, String isbn) {
        validasiIdAnggota(idAnggota);
        validasiIsbn(isbn);
        if (!anggota.contains(idAnggota)) {
            throw new AnggotaTidakDitemukanException(idAnggota);
        }

        Buku dataBuku = cariBuku(isbn);
        Set<String> pinjamanAnggota = pinjaman.get(idAnggota);
        if (pinjamanAnggota.contains(isbn) || dataBuku.tersedia() == 0) {
            throw new BukuSedangDipinjamException(isbn, dataBuku.judul());
        }
        if (pinjamanAnggota.size() >= BATAS_PINJAM) {
            throw new BatasPinjamTerlampauiException(idAnggota, BATAS_PINJAM);
        }

        buku.simpan(dataBuku.denganStokTersedia(dataBuku.tersedia() - 1));
        pinjamanAnggota.add(isbn);
    }

    public int sisaEksemplar(String isbn) {
        return cariBuku(isbn).tersedia();
    }

    public Optional<Buku> cariByIsbn(String isbn) {
        return buku.cariById(isbn);
    }

    public List<Buku> daftarBukuTersedia() {
        return buku.cariSemua().stream().filter(item -> item.tersedia() > 0).toList();
    }

    public String daftarJudul() {
        return buku.cariSemua().stream().map(Buku::judul).collect(Collectors.joining(", "));
    }

    public Map<String, Long> jumlahBukuPerPenulis() {
        return buku.cariSemua().stream().collect(Collectors.groupingBy(
                Buku::penulis, LinkedHashMap::new, Collectors.counting()));
    }

    private Buku cariBuku(String isbn) {
        validasiIsbn(isbn);
        return buku.cariById(isbn).orElseThrow(() -> new BukuTidakDitemukanException(isbn));
    }

    private static void validasiIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN tidak boleh kosong");
        }
        if (isbn.matches("-\\d+")) {
            throw new IllegalArgumentException("ISBN tidak boleh bernilai negatif");
        }
    }

    private static void validasiIdAnggota(String idAnggota) {
        if (idAnggota == null || !idAnggota.matches("[1-9]\\d*")) {
            throw new IllegalArgumentException("ID anggota harus berupa angka positif");
        }
    }
}
