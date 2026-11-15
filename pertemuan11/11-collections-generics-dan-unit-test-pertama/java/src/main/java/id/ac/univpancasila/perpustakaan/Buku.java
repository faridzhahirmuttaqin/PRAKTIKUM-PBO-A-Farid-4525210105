package id.ac.univpancasila.perpustakaan;

import java.util.Objects;

public record Buku(String isbn, String judul, String penulis, int totalEksemplar, int tersedia) {
    public Buku {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN tidak boleh kosong");
        }
        if (judul == null || judul.isBlank()) {
            throw new IllegalArgumentException("Judul tidak boleh kosong");
        }
        if (penulis == null || penulis.isBlank()) {
            throw new IllegalArgumentException("Penulis tidak boleh kosong");
        }
        if (totalEksemplar < 0 || tersedia < 0 || tersedia > totalEksemplar) {
            throw new IllegalArgumentException("Jumlah eksemplar buku tidak valid");
        }
    }

    public Buku denganStokTersedia(int stokBaru) {
        return new Buku(isbn, judul, penulis, totalEksemplar, stokBaru);
    }
}
