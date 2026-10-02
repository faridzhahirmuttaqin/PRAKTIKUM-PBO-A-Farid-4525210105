public class Segitiga extends BangunDatar {

    private final double sisiA;
    private final double sisiB;
    private final double sisiC;

    public Segitiga(double sisiA, double sisiB, double sisiC) {
        super("Segitiga");
        if (!Double.isFinite(sisiA) || !Double.isFinite(sisiB) || !Double.isFinite(sisiC)
                || sisiA <= 0 || sisiB <= 0 || sisiC <= 0
                || sisiA + sisiB <= sisiC || sisiA + sisiC <= sisiB || sisiB + sisiC <= sisiA) {
            throw new IllegalArgumentException("Ketiga sisi harus membentuk segitiga yang valid.");
        }
        this.sisiA = sisiA;
        this.sisiB = sisiB;
        this.sisiC = sisiC;
    }

    @Override public double luas() {
        double semiperimeter = keliling() / 2;
        return Math.sqrt(semiperimeter * (semiperimeter - sisiA)
                * (semiperimeter - sisiB) * (semiperimeter - sisiC));
    }

    @Override public double keliling() { return sisiA + sisiB + sisiC; }
}