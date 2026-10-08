/**
 * Satu kelas boleh mewarisi SATU class, tetapi mengimplementasikan BANYAK interface.
 * Tuliskan di keputusan.md: mengapa Java membuat aturan seperti itu?
 */
public class Mobil extends Kendaraan implements Movable, Fuelable {

    private final double kapasitasTangki;
    private double isiTangki = 0;

    public Mobil(String merek, int tahun, double kapasitasTangki) {
        super(merek, tahun);
        this.kapasitasTangki = kapasitasTangki;
    }

    @Override public int jumlahRoda() { return 4; }

    @Override public void bergerak() {
        System.out.println(merek + " melaju di jalan raya");
    }

    @Override public double kecepatanMaksimum() { return 180; }

    @Override public void isiBahanBakar(double jumlah) {
        if (!Double.isFinite(jumlah) || jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah bahan bakar harus lebih dari 0 dan finite.");
        }
        if (jumlah > kapasitasTangki - isiTangki) {
            throw new IllegalArgumentException("Pengisian melebihi kapasitas tangki.");
        }
        isiTangki += jumlah;
    }

    @Override public double kapasitasTangki() { return kapasitasTangki; }

    @Override public TipeBahanBakar tipeBahanBakar() { return TipeBahanBakar.BENSIN; }

    public double getIsiTangki() { return isiTangki; }
}
