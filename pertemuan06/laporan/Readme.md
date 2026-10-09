# Laporan Praktikum PBO — Pertemuan 06

## Abstraksi, Interface, Enum, dan Trait

### Identitas

- Nama: Farid Zhahir Muttaqin
- NIM: 4525210105
- Mata Kuliah: Praktikum Pemrograman Berorientasi Objek
- Pertemuan: 06

## Kondisi sebelum perubahan

Program starter Java dan PHP berhasil dikompilasi/dijalankan. Namun, sejumlah
perilaku penting masih berupa TODO atau nilai default: kendaraan belum bergerak,
kecepatan masih nol, dan enum belum memiliki label maupun perhitungan biaya.
Starter Java juga belum menyertakan sepeda dan contoh seluruh varian enum.

### Source Java sebelum perubahan

![Interface Fuelable Java sebelum perubahan](screenshots/Java-Fuelable-sebelum.png)

![Abstract class Kendaraan Java sebelum perubahan](screenshots/Java-Kendaraan-sebelum.png)

![Program utama Java sebelum perubahan](screenshots/Java-Main-sebelum.png)

![Kelas Mobil Java sebelum perubahan](screenshots/Java-Mobil-sebelum.png)

![Interface Movable Java sebelum perubahan](screenshots/Java-Movable-sebelum.png)

![Enum bahan bakar Java sebelum perubahan](screenshots/Java-TipeBahanBakar-sebelum.png)

### Source PHP sebelum perubahan

Deklarasi interface, enum, trait, dan kelas PHP berada dalam satu file.
Screenshot abstraksi dibagi menjadi beberapa bagian untuk memperlihatkan
keseluruhan source.

![Abstraksi PHP sebelum perubahan — bagian 1](screenshots/PHP-Abstraksi-sebelum-1.png)

![Abstraksi PHP sebelum perubahan — bagian 2](screenshots/PHP-Abstraksi-sebelum-2.png)

![Abstraksi PHP sebelum perubahan — bagian 3](screenshots/PHP-Abstraksi-sebelum-3.png)

![Program utama PHP sebelum perubahan](screenshots/PHP-Main-sebelum.png)

### Hasil running sebelum perubahan

Program dapat berjalan, tetapi hasilnya memperlihatkan method gerak yang belum
dikerjakan, kecepatan `0 km/jam`, label bahan bakar `?`, dan biaya `Rp0`.

![Output Java sebelum perubahan](screenshots/Output-Java-sebelum.png)

![Output PHP sebelum perubahan](screenshots/Output-PHP-sebelum.png)

## Perubahan yang dilakukan

1. Memisahkan kemampuan bergerak (`Movable`) dari kemampuan mengisi bahan bakar
   (`Fuelable`). Mobil menerapkan kedua kontrak, sedangkan sepeda hanya
   menerapkan `Movable`.
2. Melengkapi abstract class `Kendaraan` sebagai tempat data dan perilaku umum,
   lalu melengkapi implementasi khusus pada `Mobil` dan `Sepeda`.
3. Melengkapi enum `TipeBahanBakar` dengan label, harga, biaya pengisian, dan
   informasi keramahan lingkungan.
4. Menambahkan default method Java pada `Movable` dan trait PHP `Loggable` yang
   dipakai oleh `Mobil` serta `Pesanan`.
5. Menambahkan validasi jumlah pengisian bahan bakar dan batas kapasitas tangki.
6. Melengkapi program utama agar dapat menampilkan perilaku kendaraan,
   pengisian mobil, dan perilaku semua nilai enum.

### Source Java setelah perubahan

![Interface Fuelable Java setelah perubahan](screenshots/Java-Fuelable-sesudah.png)

![Abstract class Kendaraan Java setelah perubahan](screenshots/Java-Kendaraan-sesudah.png)

![Program utama Java setelah perubahan](screenshots/Java-Main-sesudah.png)

![Kelas Mobil Java setelah perubahan](screenshots/Java-Mobil-sesudah.png)

![Interface Movable Java setelah perubahan](screenshots/Java-Movable-sesudah.png)

![Kelas Sepeda Java setelah perubahan](screenshots/Java-Sepeda-sesudah.png)

![Enum bahan bakar Java setelah perubahan](screenshots/Java-TipeBahanBakar-sesudah.png)

### Source PHP setelah perubahan

![Abstraksi PHP setelah perubahan — bagian 1](screenshots/PHP-Abstraksi-sesudah-1.png)

![Abstraksi PHP setelah perubahan — bagian 2](screenshots/PHP-Abstraksi-sesudah-2.png)

![Abstraksi PHP setelah perubahan — bagian 3](screenshots/PHP-Abstraksi-sesudah-3.png)

![Abstraksi PHP setelah perubahan — bagian 4](screenshots/PHP-Abstraksi-sesudah-4.png)

![Program utama PHP setelah perubahan](screenshots/PHP-Main-sesudah.png)

### Hasil running setelah perubahan

Mobil dan sepeda menampilkan perilaku gerak serta kecepatan masing-masing.
Pengisian penuh mobil 45 satuan bensin menghasilkan biaya `Rp540.000`, dan
enum menampilkan perhitungan untuk bensin, solar, serta listrik. PHP juga
menunjukkan trait yang digunakan oleh dua kelas yang tidak sekerabat;
timestamp pada baris log berubah setiap kali program dijalankan.

![Output Java setelah perubahan](screenshots/Output-Java-sesudah.png)

![Output PHP setelah perubahan](screenshots/Output-PHP-sesudah.png)

## Catatan pengujian kontrak

Pemanggilan `isiPenuh(sepeda)` sengaja tidak diaktifkan pada contoh utama,
karena `Sepeda` bukan `Fuelable`. Java menolaknya saat kompilasi, sementara
PHP menghasilkan `TypeError` saat pemanggilan. Rincian uji dicatat di
[keputusan.md](keputusan.md).
