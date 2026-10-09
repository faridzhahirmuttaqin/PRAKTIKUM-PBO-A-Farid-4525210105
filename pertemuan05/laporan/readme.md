# Laporan Praktikum PBO — Pertemuan 05

## Polimorfisme

### Identitas

- Nama: Farid Zhahir Muttaqin
- NIM: 4525210105
- Mata Kuliah: Praktikum Pemrograman Berorientasi Objek
- Pertemuan: 05

### Tujuan

Pertemuan ini menerapkan polimorfisme pada kumpulan objek bangun datar.
Kelas induk `BangunDatar` menetapkan kontrak luas dan keliling, lalu setiap
bentuk menyediakan perhitungannya sendiri. Program utama memproses seluruh
objek melalui tipe induk tanpa memilih rumus berdasarkan jenis bentuk.

### Kondisi sebelum perubahan

Source original menyediakan kelas `BangunDatar`, `Lingkaran`, dan `Persegi`.
Implementasi luas dan keliling lingkaran serta persegi masih mengembalikan
`0`, dan program utama baru menggunakan dua bentuk. Karena itu hasil
perhitungan luas dan keliling keduanya masih nol.

#### Source Java sebelum perubahan

![Java BangunDatar sebelum perubahan](screenshots/Java-BangunDatar-sebelum.png)

![Java Lingkaran sebelum perubahan](screenshots/Java-Lingkaran-sebelum.png)

![Java Persegi sebelum perubahan](screenshots/Java-Persegi-sebelum.png)

![Main Java sebelum perubahan](screenshots/Java-Main-sebelum.png)

#### Source PHP sebelum perubahan

Kelas bangun datar PHP dan implementasi bentuknya berada dalam satu file.

![PHP BangunDatar sebelum perubahan — bagian 1](screenshots/PHP-BangunDatar-sebelum-1.png)

![PHP BangunDatar sebelum perubahan — bagian 2](screenshots/PHP-BangunDatar-sebelum-2.png)

![Main PHP sebelum perubahan](screenshots/PHP-Main-sebelum.png)

#### Hasil running sebelum perubahan

Source Java dan PHP original berhasil dijalankan. Pada keduanya, luas dan
keliling lingkaran serta persegi masih `0`, sehingga total luasnya juga `0`.

![Output Java sebelum perubahan](screenshots/Output-Java-sebelum.png)

![Output PHP sebelum perubahan](screenshots/Output-PHP-sebelum.png)

### Perubahan yang dilakukan

Program polimorfik dilengkapi dengan:

1. Implementasi luas dan keliling lingkaran serta persegi, termasuk validasi
   ukuran yang harus positif dan finite.
2. Penambahan `Segitiga` dengan rumus Heron dan pemeriksaan ketaksamaan
   segitiga.
3. Penambahan `Trapesium` beserta perhitungan luas dan kelilingnya.
4. Penambahan semua bentuk pada daftar program Java dan PHP.
5. Penghitungan total luas dengan memanggil `luas()` dari setiap objek melalui
   tipe induk `BangunDatar`.

#### Source Java setelah perubahan

![Java BangunDatar setelah perubahan](screenshots/Java-BangunDatar-sesudah.png)

![Java Lingkaran setelah perubahan](screenshots/Java-Lingkaran-sesudah.png)

![Java Persegi setelah perubahan](screenshots/Java-Persegi-sesudah.png)

![Java Segitiga setelah perubahan](screenshots/Java-Segitiga-sesudah.png)

![Java Trapesium setelah perubahan](screenshots/Java-Trapesium-sesudah.png)

![Main Java setelah perubahan](screenshots/Java-Main-sesudah.png)

### Latihan polimorfisme dan refaktor

Latihan `AntiPattern` menggunakan beberapa pemeriksaan jenis objek dalam satu
method. Pada `AntiPatternRefaktor`, tiap record menerapkan kontrak `Bangun`,
kemudian total luas dihitung dengan memanggil perilaku objek secara
polimorfik.

![Java AntiPattern](screenshots/Java-AntiPattern.png)

![Java AntiPatternRefaktor](screenshots/Java-AntiPattern-Refaktor.png)

Kedua versi demonstrasi menghitung total `184,94` untuk data lingkaran,
persegi, dan segitiga yang dipakai latihan.

![Output Java AntiPattern](screenshots/Output-Java-AntiPattern.png)

![Output Java AntiPatternRefaktor](screenshots/Output-Java-AntiPattern-Refaktor.png)

### Source PHP setelah perubahan

![PHP BangunDatar setelah perubahan — bagian 1](screenshots/PHP-BangunDatar-sesudah-1.png)

![PHP BangunDatar setelah perubahan — bagian 2](screenshots/PHP-BangunDatar-sesudah-2.png)

![PHP BangunDatar setelah perubahan — bagian 3](screenshots/PHP-BangunDatar-sesudah-3.png)

![Main PHP setelah perubahan](screenshots/PHP-Main-sesudah.png)

### Latihan notifikasi PHP

`Notifikasi` menjadi kelas induk abstrak dengan tiga implementasi konkret:
`Email`, `SMS`, dan `WhatsApp`. Fungsi `kirimSemua()` memanggil perilaku
masing-masing objek tanpa `instanceof`, `match`, atau `switch`.

Source original menaruh pemanggilan uji dalam komentar, sehingga tidak
menghasilkan output sebelum perubahan.

![PHP Notifikasi sebelum perubahan](screenshots/PHP-Notifikasi-sebelum.png)

![PHP Notifikasi setelah perubahan](screenshots/PHP-Notifikasi-sesudah.png)

![Output PHP notifikasi setelah perubahan](screenshots/Output-PHP-Notifikasi.png)

### Hasil running setelah perubahan

Versi Java dan PHP menampilkan empat bentuk: lingkaran, persegi, segitiga,
dan trapesium. Total luas keduanya `202,94`. Output PHP menampilkan format
desimal bertitik sesuai format bawaan program.

![Output Java setelah perubahan](screenshots/Output-Java-sesudah.png)

![Output PHP setelah perubahan](screenshots/Output-PHP-sesudah.png)

### Kesimpulan

Polimorfisme memungkinkan daftar bangun datar diproses melalui kelas induk,
sementara setiap objek menjalankan implementasi luas dan keliling miliknya.
Penambahan segitiga dan trapesium tidak memerlukan cabang rumus baru pada
perulangan utama.
