public class Main {
    public static void main(String[] args) {

        // TODO Langkah 4: tambahkan Dosen dan PegawaiHarian ke daftar ini
        //                 setelah Anda membuat kelasnya.
        Pegawai[] daftar = {
            new PegawaiTetap("198701012010", "Ani Lestari",  6_000_000, 15),
            new PegawaiKontrak("K-2024-007",  "Budi Santoso", 5_000_000, 12),
            new Dosen("202003041234", "Iman Paryudi", 7_500_000, 8, 1_200_000),
            new PegawaiHarian("202407151111", "Dimas Prasetya", 350_000, 20)
        };

        System.out.println("=== Daftar Gaji ===");
        for (Pegawai p : daftar) {
            System.out.println("  " + p);
        }

        double total = 0;
        for (Pegawai p : daftar) total += p.hitungGaji();
        System.out.printf("%n  Total beban gaji: Rp%,.2f%n", total);


        // Percobaan Langkah 1: hapus komentar baris berikut, kompilasi, catat pesannya.
        // Pegawai langsung = new Pegawai("X", "Y", 1000) { public String jenis() { return "?"; } };
    }
}
