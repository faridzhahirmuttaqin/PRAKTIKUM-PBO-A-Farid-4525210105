# Laporan Praktikum PBO — Pertemuan 03

## Constructor Berdelegasi, Anggota Statis, dan Konstanta

### Identitas

- Nama: Farid
- NIM: 4525210105
- Mata Kuliah: Praktikum Pemrograman Berorientasi Objek
- Pertemuan: 03

### Tujuan

Pertemuan ini membahas constructor berdelegasi, anggota `static`, dan konstanta
melalui program rekening bank. Program juga menunjukkan validasi rekening,
transaksi setoran dan penarikan, jumlah rekening yang dibuat, serta perhitungan
bunga.

### Kondisi sebelum perubahan

Implementasi `RekeningBank` masih memiliki TODO. Constructor ringkas Java
belum mendelegasikan proses pembuatan objek; jumlah rekening, transaksi,
biaya administrasi, dan perhitungan bunga juga belum berfungsi.

#### Source Java sebelum perubahan

![Java RekeningBank sebelum perubahan — bagian 1](screenshots/Java-RekeningBank-sebelum-1.png)

![Java RekeningBank sebelum perubahan — bagian 2](screenshots/Java-RekeningBank-sebelum-2.png)

![Java RekeningBank sebelum perubahan — bagian 3](screenshots/Java-RekeningBank-sebelum-3.png)

#### Source PHP sebelum perubahan

![PHP RekeningBank sebelum perubahan — bagian 1](screenshots/PHP-RekeningBank-sebelum-1.png)

![PHP RekeningBank sebelum perubahan — bagian 2](screenshots/PHP-RekeningBank-sebelum-2.png)

#### Hasil running Java sebelum perubahan

Pengujian memakai `Main.java` aktual dari `pertemuan03/java`. Program itu
menampilkan daftar gaji pegawai dan operasi rekening. Untuk hasil sebelum,
hanya kelas `RekeningBank` yang menggunakan versi original; kelas-kelas
pegawai dipakai sebagai dependensi program utama.

Hasilnya, jumlah rekening bernilai `-1`, setoran tidak mengubah saldo,
penarikan tidak ditolak, dan bunga masih `Rp0,00`.

![Output Java sebelum perubahan](screenshots/Output-Java-sebelum.png)

#### Hasil running PHP sebelum perubahan

Driver rekening PHP dari folder original menunjukkan bahwa
`rekeningPelajar()` masih melemparkan pesan `TODO 5 belum dikerjakan`.

![Output PHP RekeningBank sebelum perubahan](screenshots/Output-PHP-sebelum.png)

### Perubahan yang dilakukan

Implementasi kelas `RekeningBank` dilengkapi dengan:

1. Konstanta untuk bunga tahunan, biaya administrasi, dan batas penarikan.
2. Anggota statis untuk menghitung jumlah rekening yang dibuat.
3. Delegasi constructor ringkas Java ke constructor lengkap.
4. Validasi nomor rekening, pemilik, saldo awal, dan nilai transaksi.
5. Operasi setoran, penarikan, dan pemotongan biaya administrasi.
6. Perhitungan bunga tahunan dan named constructor `rekeningPelajar()` PHP.

#### Source Java setelah perubahan

![Java RekeningBank setelah perubahan — bagian 1](screenshots/Java-RekeningBank-sesudah-1.png)

![Java RekeningBank setelah perubahan — bagian 2](screenshots/Java-RekeningBank-sesudah-2.png)

![Java RekeningBank setelah perubahan — bagian 3](screenshots/Java-RekeningBank-sesudah-3.png)

#### Source PHP setelah perubahan

![PHP RekeningBank setelah perubahan — bagian 1](screenshots/PHP-RekeningBank-sesudah-1.png)

![PHP RekeningBank setelah perubahan — bagian 2](screenshots/PHP-RekeningBank-sesudah-2.png)

![PHP RekeningBank setelah perubahan — bagian 3](screenshots/PHP-RekeningBank-sesudah-3.png)

### Program utama pertemuan 03

Screenshot berikut menggunakan `Main.java` aktual di folder
`pertemuan03/java`. File ini menampilkan daftar gaji pegawai **dan** operasi
rekening bank, sesuai program yang digunakan untuk hasil running Java.

![Main.java pertemuan 03 — bagian 1](screenshots/Java-Main-program-1.png)

![Main.java pertemuan 03 — bagian 2](screenshots/Java-Main-program-2.png)

`pertemuan03/php/main.php` yang tersedia saat ini berisi pengujian daftar gaji
pegawai saja; file tersebut belum menjalankan operasi rekening.

![main.php pertemuan 03](screenshots/PHP-Main-program.png)

### Hasil running setelah perubahan

#### Java

Program utama menampilkan daftar gaji dengan total `Rp27.700.000,00`.
Pada bagian rekening, jumlah tercatat `3`, setoran menaikkan saldo Ani menjadi
`Rp1.500.000,00`, penarikan tidak valid ditolak, dan bunga tahunan menghasilkan
`Rp37.500,00`.

![Output Java setelah perubahan](screenshots/Output-Java-sesudah.png)

#### PHP

Berikut output dari `pertemuan03/php/main.php` aktual. Program ini menampilkan
dua pegawai yang ada di daftar PHP dan menghasilkan total gaji
`Rp12.800.000,00`.

![Output main.php PHP pertemuan 03](screenshots/Output-PHP-program.png)

Untuk membandingkan kelas `RekeningBank.php` sebelum dan sesudah, digunakan
driver rekening khusus dari folder original. Driver tersebut terpisah dari
`main.php` PHP aktif, yang saat ini hanya menguji daftar gaji.

![Output pengujian RekeningBank PHP sebelum perubahan](screenshots/Output-PHP-sebelum.png)

![Output pengujian RekeningBank PHP setelah perubahan](screenshots/Output-PHP-sesudah.png)

### Kesimpulan

Implementasi Java dan PHP melengkapi pengelolaan rekening dengan konstanta,
anggota statis, pembuatan objek yang konsisten, dan validasi transaksi.
Pengujian Java melalui `Main.java` aktual memperlihatkan rekening terhitung
dengan benar, setoran memperbarui saldo, transaksi yang tidak sesuai ditolak,
dan bunga tahunan dihitung.
