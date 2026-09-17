                                                                                                                                                                           /**
 * Sesi 3 — constructor berdelegasi, anggota statis, dan konstanta.
 *
 * Invariant:
 *   1. saldo tidak pernah negatif
 *   2. nomor rekening tidak berubah setelah objek dibuat
 *   3. setoran dan penarikan selalu bernilai positif
 */
public class RekeningBank {

    // Konstanta untuk nilai tetap yang dipakai di beberapa method.
    private static final double BUNGA_TAHUNAN = 0.025;
    private static final double BIAYA_ADMINISTRASI = 5000;
    private static final double BATAS_PENARIKAN_SEKALI = 5000000;

    // Penghitung jumlah rekening yang pernah dibuat.
    private static int jumlahRekening = 0;

    private final String nomor;
    private final String pemilik;
    private double saldo;

    /**
     * Constructor ringkas.
     * Delegasikan ke constructor lengkap agar validasi ada di satu tempat.
     */
    public RekeningBank(String nomor, String pemilik) {
        this(nomor, pemilik, 0);
    }

    /** Constructor lengkap — SATU-SATUNYA tempat validasi berada. */
    public RekeningBank(String nomor, String pemilik, double saldoAwal) {
        if (nomor == null || nomor.isBlank()) {
            throw new IllegalArgumentException("Nomor rekening tidak boleh kosong");
        }
        if (pemilik == null || pemilik.isBlank()) {
            throw new IllegalArgumentException("Pemilik rekening tidak boleh kosong");
        }
        if (saldoAwal < 0) {
            throw new IllegalArgumentException("Saldo awal tidak boleh negatif");
        }

        this.nomor = nomor;
        this.pemilik = pemilik;
        this.saldo = saldoAwal;

        jumlahRekening++;
    }

    public void setor(double jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah setoran harus > 0");
        }
        saldo += jumlah;
    }

    public void tarik(double jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah penarikan harus > 0");
        }
        if (jumlah > saldo) {
            throw new IllegalArgumentException("Jumlah penarikan melebihi saldo");
        }
        if (jumlah > BATAS_PENARIKAN_SEKALI) {
            throw new IllegalArgumentException("Jumlah penarikan melebihi batas transaksi");
        }

        saldo -= jumlah;
    }

    /** Kurangi saldo sebesar biaya administrasi, tetapi jangan sampai negatif. */
    public void potongBiayaAdmin() {
        if (saldo < BIAYA_ADMINISTRASI) {
            saldo = 0;
        } else {
            saldo -= BIAYA_ADMINISTRASI;
        }
    }

    /** Method statis — kembalikan jumlah rekening yang pernah dibuat. */
    public static int getJumlahRekening() {
        return jumlahRekening;
    }

    /**
     * Method statis utilitas — hitung bunga setahun dari pokok.
     * Tidak membaca keadaan objek mana pun.
     */
    public static double bungaSetahun(double pokok) {
        return pokok * BUNGA_TAHUNAN;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNomor() {
        return nomor;
    }

    @Override
    public String toString() {
        return String.format("Rekening[%s] %-14s Rp%,.2f", nomor, pemilik, saldo);
    }
}