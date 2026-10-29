package id.ac.univpancasila.perpustakaan.model;

import java.util.ArrayList;
import java.util.List;

public class Buku {
    private final String nomorInduk;
    private final String judul;
    private final String penulis;
    private int jumlahEksemplar;
    private final List<Bab> bab = new ArrayList<>();

    public Buku(String nomorInduk, String judul, String penulis, int jumlahEksemplar) {
        if (jumlahEksemplar < 0) {
            throw new IllegalArgumentException("Jumlah eksemplar tidak boleh negatif");
        }
        this.nomorInduk = nomorInduk;
        this.judul = judul;
        this.penulis = penulis;
        this.jumlahEksemplar = jumlahEksemplar;
    }

    public void tambahBab(int nomor, String judulBab) {
        bab.add(new Bab(nomor, judulBab));
    }

    public List<Bab> getBab() {
        return List.copyOf(bab);
    }

    public boolean tersedia() {
        return jumlahEksemplar > 0;
    }

    public void kurangiStok() {
        if (jumlahEksemplar > 0) {
            jumlahEksemplar--;
        } else {
            throw new IllegalStateException("Tidak ada eksemplar yang tersedia");
        }
    }

    public void tambahStok() {
        jumlahEksemplar++;
    }

    public String getNomorInduk() {
        return nomorInduk;
    }

    public String getJudul() {
        return judul;
    }

    public int getJumlahEksemplar() {
        return jumlahEksemplar;
    }

    @Override
    public String toString() {
        return String.format("%-32s %-16s [%d eks, %d bab]",
                judul, penulis, jumlahEksemplar, bab.size());
    }

    public static final class Bab {
        private final int nomor;
        private final String judul;

        private Bab(int nomor, String judul) {
            this.nomor = nomor;
            this.judul = judul;
        }

        public int getNomor() {
            return nomor;
        }

        public String getJudul() {
            return judul;
        }

        @Override
        public String toString() {
            return "Bab " + nomor + ": " + judul;
        }
    }
}