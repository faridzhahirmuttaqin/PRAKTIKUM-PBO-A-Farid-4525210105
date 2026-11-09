package id.ac.univpancasila.perpustakaan;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.PerpustakaanException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public final class App {
    private static final Path LOG = Path.of("logs", "perpustakaan.log");

    private App() {}

    public static void main(String[] args) throws IOException {
        LibraryService service = layananContoh();
        if (args.length > 0 && args[0].equals("--self-test")) {
            jalankanSkenario(service);
            return;
        }
        jalankanKonsol(service);
    }

    private static LibraryService layananContoh() {
        LibraryService service = new LibraryService();
        service.tambahBuku("978-602-001-001-1", "Pemrograman Berorientasi Objek", 2);
        service.tambahBuku("978-602-001-002-8", "Dasar Algoritma", 1);
        service.tambahBuku("978-602-001-003-5", "Struktur Data", 1);
        service.tambahBuku("978-602-001-004-2", "Basis Data", 1);
        service.tambahBuku("978-602-001-005-9", "Pemrograman Lanjut", 1);
        service.daftarkanAnggota("1001");
        service.daftarkanAnggota("1002");
        return service;
    }

    private static void jalankanKonsol(LibraryService service) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Sistem Perpustakaan — ketik 0 untuk keluar.");
            while (true) {
                System.out.println();
                System.out.println("1. Pinjam buku");
                System.out.println("2. Ekspor daftar buku");
                System.out.print("Pilihan: ");
                if (!scanner.hasNextLine()) {
                    return;
                }
                String pilihan = scanner.nextLine().trim();
                if (pilihan.equals("0")) {
                    return;
                }
                if (pilihan.equals("1")) {
                    prosesPeminjaman(scanner, service);
                } else if (pilihan.equals("2")) {
                    try {
                        service.exportDaftarBuku(Path.of("data", "daftar-buku.txt"), false);
                        System.out.println("Daftar buku berhasil diekspor.");
                    } catch (IOException e) {
                        ExceptionLogger.catat(e, LOG);
                        System.out.println("Ekspor gagal: " + e.getMessage());
                    }
                } else {
                    System.out.println("Pilihan tidak valid. Masukkan 1, 2, atau 0.");
                }
            }
        }
    }

    private static void prosesPeminjaman(Scanner scanner, LibraryService service) throws IOException {
        System.out.print("ID anggota (angka positif): ");
        if (!scanner.hasNextLine()) {
            return;
        }
        String idAnggota = scanner.nextLine().trim();
        if (!idAnggota.matches("[1-9]\\d*")) {
            System.out.println("Masukan tidak valid: ID anggota harus berupa angka positif.");
            return;
        }

        System.out.print("ISBN (kosong atau 0 untuk batal): ");
        if (!scanner.hasNextLine()) {
            return;
        }
        String isbn = scanner.nextLine().trim();
        if (isbn.isEmpty() || isbn.equals("0")) {
            System.out.println("Masukan ISBN kosong atau dibatalkan.");
            return;
        }
        if (isbn.matches("-\\d+")) {
            System.out.println("Masukan tidak valid: ISBN tidak boleh bernilai negatif.");
            return;
        }

        try {
            service.pinjam(idAnggota, isbn);
            System.out.println("Peminjaman berhasil: " + service.judulDari(isbn));
        } catch (PerpustakaanException e) {
            ExceptionLogger.catat(e, LOG);
            System.out.println("Peminjaman gagal: " + e.getMessage());
        }
    }

    private static void jalankanSkenario(LibraryService service) throws IOException {
        int[] lulus = {0};
        ujiException("ISBN tidak ditemukan", BukuTidakDitemukanException.class,
                () -> service.pinjam("1001", "ISBN-TIDAK-ADA"), lulus);
        ujiException("anggota tidak terdaftar", AnggotaTidakDitemukanException.class,
                () -> service.pinjam("9999", "978-602-001-001-1"), lulus);
        ujiException("ISBN negatif", IllegalArgumentException.class,
                () -> service.pinjam("1001", "-5"), lulus);
        ujiException("ID anggota negatif", IllegalArgumentException.class,
                () -> service.pinjam("-5", "978-602-001-001-1"), lulus);
        ujiException("ID anggota kosong", IllegalArgumentException.class,
                () -> service.pinjam(" ", "978-602-001-001-1"), lulus);
        ujiException("ISBN kosong", IllegalArgumentException.class,
                () -> service.pinjam("1001", " "), lulus);
        ujiException("ID berisi huruf", IllegalArgumentException.class,
                () -> service.pinjam("abc", "978-602-001-001-1"), lulus);
        ujiException("buku sama dipinjam dua kali", BukuSedangDipinjamException.class, () -> {
            service.pinjam("1001", "978-602-001-001-1");
            service.pinjam("1001", "978-602-001-001-1");
        }, lulus);
        ujiException("seluruh eksemplar sedang dipinjam", BukuSedangDipinjamException.class, () -> {
            LibraryService stokPenuh = new LibraryService();
            stokPenuh.tambahBuku("B-1", "Buku Satu", 1);
            stokPenuh.daftarkanAnggota("1001");
            stokPenuh.daftarkanAnggota("1002");
            stokPenuh.pinjam("1001", "B-1");
            stokPenuh.pinjam("1002", "B-1");
            stokPenuh.daftarkanAnggota("1003");
            stokPenuh.pinjam("1003", "B-1");
        }, lulus);
        ujiException("batas pinjam terlampaui", BatasPinjamTerlampauiException.class, () -> {
            service.pinjam("1002", "978-602-001-002-8");
            service.pinjam("1002", "978-602-001-003-5");
            service.pinjam("1002", "978-602-001-004-2");
            service.pinjam("1002", "978-602-001-005-9");
        }, lulus);

        Path sementara = Files.createTempFile("perpustakaan-ekspor-", ".txt");
        Path dipindah = sementara.resolveSibling(sementara.getFileName() + ".closed");
        try {
            ujiException("berkas tetap tertutup saat ekspor gagal", IOException.class,
                    () -> service.exportDaftarBuku(sementara, true), lulus);
            Files.move(sementara, dipindah);
            Files.delete(dipindah);
            service.exportDaftarBuku(sementara, false);
            Files.delete(sementara);
            lulus[0]++;
            System.out.println("LULUS — ekspor normal menghasilkan berkas dan berkas dapat dibuka kembali");
        } finally {
            Files.deleteIfExists(sementara);
            Files.deleteIfExists(dipindah);
        }
        System.out.println("Selesai: " + lulus[0] + " skenario lulus.");
    }

    private static void ujiException(
            String nama,
            Class<? extends Exception> jenisDiharapkan,
            Aksi tindakan,
            int[] lulus) throws IOException {
        try {
            tindakan.jalankan();
            throw new AssertionError("Tidak ada exception pada skenario: " + nama);
        } catch (Exception e) {
            if (!jenisDiharapkan.isInstance(e)) {
                throw new AssertionError(
                        "Skenario " + nama + " mengharapkan " + jenisDiharapkan.getSimpleName()
                                + ", tetapi mendapat " + e.getClass().getSimpleName(),
                        e);
            }
            ExceptionLogger.catat(e, LOG);
            lulus[0]++;
            System.out.println("LULUS — " + nama + " -> " + e.getClass().getSimpleName());
        }
    }

    @FunctionalInterface
    private interface Aksi {
        void jalankan() throws Exception;
    }
}
