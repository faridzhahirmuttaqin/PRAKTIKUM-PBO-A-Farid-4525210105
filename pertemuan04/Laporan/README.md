# Laporan Praktikum PBO — Pertemuan 04

## Pewarisan dan Hierarki Pegawai

### Identitas

- Nama: Farid Zhahir Muttaqin
- NIM: 4525210105
- Mata Kuliah: Praktikum Pemrograman Berorientasi Objek
- Pertemuan: 04

### Tujuan

Pertmemuan kali ini menerapkan pewarisan melalui hierarki `Pegawai`. Kelas induk
menyimpan data dan perilaku umum, sedangkan jenis pegawai turunannya
menerapkan perhitungan gaji masing-masing.

### Kondisi sebelum perubahan

Implementasi awal masih berisi TODO. Perhitungan gaji kelas induk dan
`PegawaiTetap` mengembalikan `0`, dan gaji pokok negatif belum ditolak.
Program utama hanya menguji pegawai tetap dan pegawai kontrak.

#### Source Java sebelum perubahan

![Java Pegawai sebelum perubahan](screenshots/Java-Pegawai-sebelum.png)

![Java PegawaiTetap sebelum perubahan](screenshots/Java-PegawaiTetap-sebelum.png)

![Main Java sebelum perubahan](screenshots/Java-Main-sebelum.png)

#### Source PHP sebelum perubahan

Source PHP awal menaruh kelas induk dan turunannya dalam satu file.

![PHP Pegawai sebelum perubahan — bagian 1](screenshots/PHP-Pegawai-sebelum-1.png)

![PHP Pegawai sebelum perubahan — bagian 2](screenshots/PHP-Pegawai-sebelum-2.png)

![Main PHP sebelum perubahan](screenshots/PHP-Main-sebelum.png)

#### Hasil running sebelum perubahan

Source Java dan PHP original dapat dijalankan. Keduanya menampilkan dua
pegawai, tetapi perhitungan gaji masih menghasilkan `Rp0,00` untuk setiap
pegawai dan total beban gaji `Rp0,00`.

![Output Java sebelum perubahan](screenshots/Output-Java-sebelum.png)

![Output PHP sebelum perubahan](screenshots/Output-PHP-sebelum.png)

### Perubahan yang dilakukan

Implementasi kelas dan program utama dilengkapi dengan:

1. Validasi agar gaji pokok tidak bernilai negatif.
2. Perhitungan dasar `Pegawai` yang mengembalikan gaji pokok.
3. Perhitungan gaji `PegawaiTetap` dengan tunjangan masa kerja 2% per tahun,
   maksimal 40%, menggunakan `super.hitungGaji()`.
4. Pewarisan gaji pokok oleh `PegawaiKontrak` tanpa override perhitungan gaji.
5. Penambahan `Dosen` sebagai turunan `PegawaiTetap` dengan tunjangan
   fungsional dan `PegawaiHarian` yang mengalikan upah harian dengan hari kerja.
6. Penambahan objek dosen dan pegawai harian pada daftar program Java dan PHP.

#### Source Java setelah perubahan

![Java Pegawai setelah perubahan](screenshots/Java-Pegawai-sesudah.png)

![Java PegawaiTetap setelah perubahan](screenshots/Java-PegawaiTetap-sesudah.png)

![Java PegawaiKontrak setelah perubahan](screenshots/Java-PegawaiKontrak-sesudah.png)

![Java Dosen setelah perubahan](screenshots/Java-Dosen-sesudah.png)

![Java PegawaiHarian setelah perubahan](screenshots/Java-PegawaiHarian-sesudah.png)

![Main Java setelah perubahan](screenshots/Java-Main-sesudah.png)

#### Source PHP setelah perubahan

Kelas-kelas hierarki PHP berada bersama-sama di `Pegawai.php`.

![PHP Pegawai setelah perubahan — bagian 1](screenshots/PHP-Pegawai-sesudah-1.png)

![PHP Pegawai setelah perubahan — bagian 2](screenshots/PHP-Pegawai-sesudah-2.png)

![PHP Pegawai setelah perubahan — bagian 3](screenshots/PHP-Pegawai-sesudah-3.png)

![Main PHP setelah perubahan](screenshots/PHP-Main-sesudah.png)

### Hasil running setelah perubahan

Program Java dan PHP menghasilkan gaji yang sama:

- Pegawai tetap Ani Lestari: `Rp7.800.000,00`
- Pegawai kontrak Budi Santoso: `Rp5.000.000,00`
- Dosen Iman Paryudi: `Rp9.900.000,00`
- Pegawai harian Dimas Prasetya: `Rp7.000.000,00`
- Total beban gaji: `Rp29.700.000,00`

![Output Java setelah perubahan](screenshots/Output-Java-sesudah.png)

![Output PHP setelah perubahan](screenshots/Output-PHP-sesudah.png)

### Kesimpulan

Pewarisan memungkinkan data dan perilaku gaji dasar didefinisikan pada kelas
induk, lalu digunakan atau dikembangkan sesuai jenis pegawai. Program Java
dan PHP yang telah dilengkapi menghasilkan perhitungan gaji yang sesuai untuk
pegawai tetap, kontrak, dosen, dan harian.
