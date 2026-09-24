public class Main {
    public static void main(String[] args) {

        // TODO Langkah 4: tambahkan Dosen dan PegawaiHarian ke daftar ini
        //                 setelah Anda membuat kelasnya.
        Pegawai[] daftar = {
            new PegawaiTetap("198701012010", "Ani Lestari",  6_000_000, 15),
            new PegawaiKontrak("K-2024-007",  "Budi Santoso", 5_000_000, 12),
            new Dosen("D-2024-003", "Iman Paryudi", 7_000_000, 10, 1_500_000),
            new PegawaiHarian("H-2024-011", "Dedi Irawan", 250_000, 20)
        };

        System.out.println("=== Daftar Gaji ===");
        for (Pegawai p : daftar) {
            System.out.println("  " + p);
        }

        double total = 0;
        for (Pegawai p : daftar) total += p.hitungGaji();
        System.out.printf("%n  Total beban gaji: Rp%,.2f%n", total);

        System.out.println();
        System.out.println("Periksa: Ani (pokok 6.000.000, masa kerja 15 tahun)");
        System.out.println("  tunjangan 15 x 2% = 30%, jadi gaji seharusnya Rp7.800.000,00");

        // Percobaan Langkah 1: hapus komentar baris berikut, kompilasi, catat pesannya.
        // Pegawai langsung = new Pegawai("X", "Y", 1000) { public String jenis() { return "?"; } };
        System.out.println("Jumlah rekening di awal: " + RekeningBank.getJumlahRekening());

        RekeningBank a = new RekeningBank("111", "Ani", 1_000_000);
        RekeningBank b = new RekeningBank("222", "Budi");        // constructor ringkas
        RekeningBank c = new RekeningBank("333", "Citra", 250_000);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        System.out.println("Jumlah rekening sekarang: " + RekeningBank.getJumlahRekening()
                           + "   (seharusnya 3, bukan 4)");

        System.out.println();
        System.out.println("=== Operasi ===");
        a.setor(500_000);
        System.out.println("Setelah setor 500.000  -> " + a);

        try {
            a.tarik(9_999_999);
            System.out.println("  MASALAH: penarikan melebihi batas seharusnya ditolak!");
        } catch (RuntimeException e) {
            System.out.println("  Ditolak: " + e.getMessage());
        }

        b.potongBiayaAdmin();
        System.out.println("Budi setelah potong admin: " + b + "   (saldo tidak boleh negatif)");

        System.out.printf("Bunga setahun dari saldo Ani: Rp%,.2f%n",
                RekeningBank.bungaSetahun(a.getSaldo()));
    }
}
