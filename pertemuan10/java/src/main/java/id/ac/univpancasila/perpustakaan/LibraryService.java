package id.ac.univpancasila.perpustakaan;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class LibraryService {
    public static final int BATAS_PINJAM = 3;

    private final Map<String, Buku> buku = new LinkedHashMap<>();
    private final Set<String> anggota = new LinkedHashSet<>();
    private final Map<String, Set<String>> pinjaman = new LinkedHashMap<>();

    public void tambahBuku(String isbn, String judul, int eksemplar) {
        validasiTeks(isbn, "ISBN");
        validasiTeks(judul, "Judul");
        if (eksemplar < 0) {
            throw new IllegalArgumentException("Jumlah eksemplar tidak boleh negatif");
        }
        if (buku.containsKey(isbn)) {
            throw new IllegalArgumentException("ISBN sudah terdaftar: " + isbn);
        }
        buku.put(isbn, new Buku(judul, eksemplar));
    }

    public void daftarkanAnggota(String idAnggota) {
        validasiIdAnggota(idAnggota);
        anggota.add(idAnggota);
        pinjaman.computeIfAbsent(idAnggota, ignored -> new LinkedHashSet<>());
    }

    public void pinjam(String idAnggota, String isbn) {
        validasiIdAnggota(idAnggota);
        validasiTeks(isbn, "ISBN");

        if (!anggota.contains(idAnggota)) {
            throw new AnggotaTidakDitemukanException(idAnggota);
        }

        Buku dataBuku = buku.get(isbn);
        if (dataBuku == null) {
            throw new BukuTidakDitemukanException(isbn);
        }

        Set<String> pinjamanAnggota = pinjaman.get(idAnggota);
        if (pinjamanAnggota.contains(isbn) || dataBuku.tersedia == 0) {
            throw new BukuSedangDipinjamException(isbn, dataBuku.judul);
        }
        if (pinjamanAnggota.size() >= BATAS_PINJAM) {
            throw new BatasPinjamTerlampauiException(idAnggota, BATAS_PINJAM);
        }

        dataBuku.tersedia--;
        pinjamanAnggota.add(isbn);
    }

    public int sisaEksemplar(String isbn) {
        Buku dataBuku = buku.get(isbn);
        if (dataBuku == null) {
            throw new BukuTidakDitemukanException(isbn);
        }
        return dataBuku.tersedia;
    }

    public void exportDaftarBuku(Path tujuan, boolean simulasikanKegagalan) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(
                tujuan,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE)) {
            writer.write("ISBN | Judul | Tersedia");
            writer.newLine();
            int jumlah = 0;
            for (Map.Entry<String, Buku> entri : buku.entrySet()) {
                writer.write(entri.getKey() + " | " + entri.getValue().judul + " | " + entri.getValue().tersedia);
                writer.newLine();
                if (simulasikanKegagalan && ++jumlah == 1) {
                    throw new IOException("Kegagalan ekspor simulasi setelah baris pertama");
                }
            }
        }
    }

    public List<String> daftarIsbn() {
        return new ArrayList<>(buku.keySet());
    }

    public String judulDari(String isbn) {
        Buku dataBuku = buku.get(isbn);
        return dataBuku == null ? null : dataBuku.judul;
    }

    private static void validasiTeks(String nilai, String nama) {
        if (nilai == null || nilai.isBlank()) {
            throw new IllegalArgumentException(nama + " tidak boleh kosong");
        }
        if (nilai.matches("-\\d+")) {
            throw new IllegalArgumentException(nama + " tidak boleh bernilai negatif");
        }
    }

    private static void validasiIdAnggota(String idAnggota) {
        if (idAnggota == null || !idAnggota.matches("[1-9]\\d*")) {
            throw new IllegalArgumentException("ID anggota harus berupa angka positif");
        }
    }

    private static final class Buku {
        private final String judul;
        private int tersedia;

        private Buku(String judul, int tersedia) {
            this.judul = judul;
            this.tersedia = tersedia;
        }
    }
}
