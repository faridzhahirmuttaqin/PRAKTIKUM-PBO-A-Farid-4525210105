package id.ac.univpancasila.perpustakaan;

import id.ac.univpancasila.perpustakaan.model.Buku;
import id.ac.univpancasila.perpustakaan.model.Jurusan;
import org.apache.commons.lang3.StringUtils;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Komposisi: Buku memiliki Bab ===");
        Buku buku = new Buku("PBO-001", "Pemrograman Berorientasi Objek", "A. W. Pribadi", 3);
        buku.tambahBab(1, "Paradigma Objek");
        buku.tambahBab(2, "Enkapsulasi");
        buku.tambahBab(3, "Pewarisan dan Polimorfisme");
        System.out.println("  " + buku);
        System.out.println("  Ringkasan judul: " + StringUtils.abbreviate(buku.getJudul(), 24));
        buku.getBab().forEach(bab -> System.out.println("    " + bab));

        System.out.println();
        System.out.println("=== Agregasi: Jurusan menaungi Mahasiswa ===");
        Jurusan.Mahasiswa ani = new Jurusan.Mahasiswa("2024001", "Ani Lestari");
        Jurusan.Mahasiswa budi = new Jurusan.Mahasiswa("2024002", "Budi Santoso");

        Jurusan informatika = new Jurusan("TI", "Teknik Informatika");
        informatika.daftarkan(ani);
        informatika.daftarkan(budi);
        System.out.println("  " + informatika);

        informatika.keluarkan(budi);
        System.out.println("  Setelah Budi pindah: " + informatika);
        System.out.println("  Budi tetap ada sebagai objek: " + budi);
    }
}