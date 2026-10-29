package id.ac.univpancasila.perpustakaan.model;

import java.util.ArrayList;
import java.util.List;

public class Jurusan {
    private final String kode;
    private final String nama;
    private final List<Mahasiswa> mahasiswa = new ArrayList<>();

    public Jurusan(String kode, String nama) {
        this.kode = kode;
        this.nama = nama;
    }

    public void daftarkan(Mahasiswa mahasiswaBaru) {
        if (mahasiswaBaru == null) {
            throw new IllegalArgumentException("Mahasiswa tidak boleh null");
        }
        if (!mahasiswa.contains(mahasiswaBaru)) {
            mahasiswa.add(mahasiswaBaru);
        }
    }

    public void keluarkan(Mahasiswa mahasiswa) {
        this.mahasiswa.remove(mahasiswa);
    }

    public List<Mahasiswa> getMahasiswa() {
        return List.copyOf(mahasiswa);
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%d mahasiswa)", kode, nama, mahasiswa.size());
    }

    public static final class Mahasiswa {
        private final String nim;
        private final String nama;

        public Mahasiswa(String nim, String nama) {
            this.nim = nim;
            this.nama = nama;
        }

        public String getNim() {
            return nim;
        }

        public String getNama() {
            return nama;
        }

        @Override
        public String toString() {
            return nim + " " + nama;
        }
    }
}