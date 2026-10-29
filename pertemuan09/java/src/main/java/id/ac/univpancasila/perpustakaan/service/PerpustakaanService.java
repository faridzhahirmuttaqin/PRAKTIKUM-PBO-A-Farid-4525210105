package id.ac.univpancasila.perpustakaan.service;

import id.ac.univpancasila.perpustakaan.model.Buku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PerpustakaanService {
    private static final int BATAS_PINJAMAN_AKTIF = 3;

    private final Map<String, Buku> koleksi = new LinkedHashMap<>();
    private final Map<String, List<String>> pinjamanAktif = new HashMap<>();

    public void tambahKoleksi(Buku buku) {
        Objects.requireNonNull(buku, "Buku tidak boleh null");
        String nomorInduk = buku.getNomorInduk();
        validasiNomor(nomorInduk, "Nomor induk");
        if (koleksi.putIfAbsent(nomorInduk, buku) != null) {
            throw new IllegalArgumentException("Nomor induk sudah terdaftar: " + nomorInduk);
        }
    }

    public Buku cariKoleksi(String nomorInduk) {
        return koleksi.get(nomorInduk);
    }

    public void pinjam(String nomorAnggota, String nomorInduk) {
        validasiNomor(nomorAnggota, "Nomor anggota");
        validasiNomor(nomorInduk, "Nomor induk");

        Buku buku = koleksi.get(nomorInduk);
        if (buku == null) {
            throw new IllegalArgumentException("Koleksi tidak ditemukan: " + nomorInduk);
        }

        List<String> pinjamanAnggota = pinjamanAktif.get(nomorAnggota);
        if (pinjamanAnggota != null && pinjamanAnggota.size() >= BATAS_PINJAMAN_AKTIF) {
            throw new IllegalStateException("Anggota tidak boleh meminjam lebih dari tiga koleksi");
        }

        buku.kurangiStok();
        pinjamanAktif.computeIfAbsent(nomorAnggota, k -> new ArrayList<>()).add(nomorInduk);
    }

    public void kembalikan(String nomorAnggota, String nomorInduk) {
        validasiNomor(nomorAnggota, "Nomor anggota");
        validasiNomor(nomorInduk, "Nomor induk");

        List<String> pinjamanAnggota = pinjamanAktif.get(nomorAnggota);
        if (pinjamanAnggota == null || !pinjamanAnggota.contains(nomorInduk)) {
            throw new IllegalArgumentException("Koleksi tersebut tidak sedang dipinjam anggota");
        }

        Buku buku = koleksi.get(nomorInduk);
        if (buku == null) {
            throw new IllegalStateException("Koleksi pinjaman tidak lagi terdaftar: " + nomorInduk);
        }

        pinjamanAnggota.remove(nomorInduk);
        buku.tambahStok();
        if (pinjamanAnggota.isEmpty()) {
            pinjamanAktif.remove(nomorAnggota);
        }
    }

    private static void validasiNomor(String nomor, String namaBidang) {
        if (nomor == null || nomor.isBlank()) {
            throw new IllegalArgumentException(namaBidang + " tidak boleh kosong");
        }
    }
}