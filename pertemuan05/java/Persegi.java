public class Persegi extends BangunDatar {

    private final double sisi;

    public Persegi(double sisi) {
        super("Persegi");
        if (!Double.isFinite(sisi) || sisi <= 0) {
            throw new IllegalArgumentException("Sisi harus berupa angka positif dan finite.");
        }
        this.sisi = sisi;
    }

    @Override public double luas()     { return sisi * sisi; }
    @Override public double keliling() { return 4 * sisi; }
}
