public class Trapesium extends BangunDatar {

    private final double sisiSejajarAtas;
    private final double sisiSejajarBawah;
    private final double tinggi;
    private final double sisiMiringKiri;
    private final double sisiMiringKanan;

    public Trapesium(double sisiSejajarAtas, double sisiSejajarBawah, double tinggi,
                     double sisiMiringKiri, double sisiMiringKanan) {
        super("Trapesium");
        if (!Double.isFinite(sisiSejajarAtas) || !Double.isFinite(sisiSejajarBawah)
                || !Double.isFinite(tinggi) || !Double.isFinite(sisiMiringKiri)
                || !Double.isFinite(sisiMiringKanan) || sisiSejajarAtas <= 0
                || sisiSejajarBawah <= 0 || tinggi <= 0 || sisiMiringKiri <= 0
                || sisiMiringKanan <= 0) {
            throw new IllegalArgumentException("Semua ukuran trapesium harus positif dan finite.");
        }
        this.sisiSejajarAtas = sisiSejajarAtas;
        this.sisiSejajarBawah = sisiSejajarBawah;
        this.tinggi = tinggi;
        this.sisiMiringKiri = sisiMiringKiri;
        this.sisiMiringKanan = sisiMiringKanan;
    }

    @Override public double luas() {
        return (sisiSejajarAtas + sisiSejajarBawah) * tinggi / 2;
    }

    @Override public double keliling() {
        return sisiSejajarAtas + sisiSejajarBawah + sisiMiringKiri + sisiMiringKanan;
    }
}