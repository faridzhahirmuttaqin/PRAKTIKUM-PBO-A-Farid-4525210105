package id.ac.univpancasila.perpustakaan;

public final class App {
    private App() {}

    public static void main(String[] args) {
        LibraryService perpustakaan = new LibraryService();
        perpustakaan.tambahBuku("978-1", "Pemrograman Java", "Farid", 2);
        perpustakaan.tambahBuku("978-2", "Struktur Data", "Ayu", 1);
        perpustakaan.tambahBuku("978-3", "Basis Data", "Farid", 0);

        System.out.println("=== Demo Collections dan Generics (Java) ===");
        System.out.println("Buku tersedia: " + perpustakaan.daftarBukuTersedia().stream()
                .map(Buku::judul).toList());
        System.out.println("Daftar judul: " + perpustakaan.daftarJudul());
        System.out.println("Jumlah judul per penulis: " + perpustakaan.jumlahBukuPerPenulis());

        perpustakaan.daftarkanAnggota("1001");
        perpustakaan.pinjam("1001", "978-1");
        System.out.println("Sisa eksemplar Pemrograman Java setelah dipinjam: "
                + perpustakaan.sisaEksemplar("978-1"));

        Repository<Buku> repository = new Repository<>(Buku::isbn);
        repository.simpan(new Buku("978-4", "Pemrograman Generik", "Dosen", 1, 1));
        System.out.println("Pencarian Repository< Buku >: "
                + repository.cariById("978-4").orElseThrow().judul());
    }
}
