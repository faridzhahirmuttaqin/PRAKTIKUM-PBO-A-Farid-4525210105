/** Versi polimorfik dari AntiPattern: tiap bentuk memiliki perhitungan luasnya sendiri. */
public class AntiPatternRefaktor {

    interface Bangun {
        double luas();
    }

    record LingkaranData(double r) implements Bangun {
        @Override public double luas() { return Math.PI * r * r; }
    }

    record PersegiData(double sisi) implements Bangun {
        @Override public double luas() { return sisi * sisi; }
    }

    record SegitigaData(double alas, double tinggi) implements Bangun {
        @Override public double luas() { return 0.5 * alas * tinggi; }
    }

    public static void main(String[] args) {
        Bangun[] daftar = {
            new LingkaranData(7),
            new PersegiData(5),
            new SegitigaData(4, 3)
        };

        double total = 0;
        for (Bangun bangun : daftar) total += bangun.luas();
        System.out.printf("Total luas (versi polimorfik): %.2f%n", total);
    }
}