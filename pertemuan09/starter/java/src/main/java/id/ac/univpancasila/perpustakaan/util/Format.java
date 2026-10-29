package id.ac.univpancasila.perpustakaan.util;

import java.text.NumberFormat;
import java.util.Locale;

public final class Format {
    private Format() {
    }

    public static String rupiah(long jumlah) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"));
        return format.format(jumlah);
    }
}