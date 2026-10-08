# Keputusan Pertemuan 06

## Hasil uji kontrak Fuelable

Pemanggilan `isiPenuh(sepeda)` sengaja dibiarkan sebagai komentar di contoh utama agar program dapat dikompilasi dan dijalankan. Saat baris itu diaktifkan, Java menolak program saat kompilasi:

```text
pertemuan06\java\Main.java:34: error: incompatible types: Sepeda cannot be converted to Fuelable
        isiPenuh(sepeda);
                 ^
1 error
```

PHP menolak pemanggilan yang sama saat runtime dengan `TypeError`:

```text
TypeError: isiPenuh(): Argument #1 ($kendaraan) must be of type Fuelable, Sepeda given
```

Penolakan Java saat kompilasi menguntungkan karena kesalahan kontrak diketahui sebelum program dijalankan; tidak perlu menunggu jalur kode tersebut dipanggil untuk menemukan bahwa sepeda bukan kendaraan yang dapat diisi bahan bakar. Di PHP, deklarasi tipe juga melindungi kontrak, tetapi pelanggarannya baru diketahui saat pemanggilan.

## Pewarisan Java

Java hanya mengizinkan satu superclass agar sebuah kelas tidak mewarisi implementasi dan state dari beberapa kelas yang berpotensi bertentangan atau ambigu (masalah diamond). Beberapa interface tetap boleh diimplementasikan karena interface menyatakan kontrak kemampuan yang dapat dipenuhi bersama, seperti `Movable` dan `Fuelable`, tanpa mewarisi banyak implementasi kelas.
