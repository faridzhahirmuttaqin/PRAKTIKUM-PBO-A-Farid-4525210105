# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
|---|---|
| **Nama** | Farid Zhahir Muttaqin |
| **NPM** | 4525210105 |
| **Kelas** | A |
| **Mata Kuliah** | Praktikum Pemrograman Berorientasi Objek (PBO) |
| **Pertemuan** | 04 — Pewarisan dan Hierarki Pegawai |
| **Tanggal** | Tidak dicantumkan |

---

## Java — `Pegawai.java`

**Penjelasan Kode:**

> **Before:** kelas induk belum menerapkan perhitungan gaji yang benar, dan
> gaji pokok negatif belum divalidasi.
>
> **After:** kelas induk menyimpan data umum pegawai, menolak gaji pokok
> negatif, dan menyediakan perhitungan dasar berupa gaji pokok. Kelas turunan
> dapat memakai perilaku dasar ini atau melakukan override sesuai aturan
> jenis pegawainya.

**Bukti Eksekusi (Screenshot):**

- **Before** _(source awal)_:

  ![Pegawai.java sebelum perubahan](screenshots/Java-Pegawai-sebelum.png)

- **After** _(source setelah perubahan)_:

  ![Pegawai.java sesudah perubahan](screenshots/Java-Pegawai-sesudah.png)

---

## Java — `PegawaiTetap.java`

**Penjelasan Kode:**

> **Before:** perhitungan gaji pegawai tetap masih mengembalikan `0`.
>
> **After:** gaji dihitung dari gaji dasar ditambah tunjangan masa kerja
> sebesar 2% per tahun, dengan batas maksimum 40%. Implementasi menggunakan
> `super.hitungGaji()` untuk memakai perhitungan kelas induk.

**Bukti Eksekusi (Screenshot):**

- **Before** _(source awal)_:

  ![PegawaiTetap.java sebelum perubahan](screenshots/Java-PegawaiTetap-sebelum.png)

- **After** _(source setelah perubahan)_:

  ![PegawaiTetap.java sesudah perubahan](screenshots/Java-PegawaiTetap-sesudah.png)

---

## Java — `PegawaiKontrak.java`

**Penjelasan Kode:**

> `PegawaiKontrak` merupakan turunan `Pegawai` yang menggunakan perhitungan
> gaji pokok dari kelas induk tanpa override perhitungan gaji.

**Bukti Eksekusi (Screenshot):**

- **After** _(implementasi pada hasil akhir)_:

  ![PegawaiKontrak.java sesudah perubahan](screenshots/Java-PegawaiKontrak-sesudah.png)

---

## Java — `Dosen.java`

**Penjelasan Kode:**

> `Dosen` memperluas `PegawaiTetap` dengan tunjangan fungsional dosen. Source
> dan objek dosen ditambahkan pada hasil akhir; screenshot Before untuk kelas
> baru ini tidak tersedia.

**Bukti Eksekusi (Screenshot):**

- **After**:

  ![Dosen.java sesudah perubahan](screenshots/Java-Dosen-sesudah.png)

---

## Java — `PegawaiHarian.java`

**Penjelasan Kode:**

> `PegawaiHarian` menerapkan perhitungan upah dengan mengalikan upah harian
> dan jumlah hari kerja. Kelas ini ditambahkan pada implementasi akhir;
> screenshot Before untuk kelas baru ini tidak tersedia.

**Bukti Eksekusi (Screenshot):**

- **After**:

  ![PegawaiHarian.java sesudah perubahan](screenshots/Java-PegawaiHarian-sesudah.png)

---

## Java — `Main.java`

**Penjelasan Kode:**

> **Before:** program utama hanya menguji pegawai tetap dan pegawai kontrak.
> **After:** daftar program juga mencakup dosen dan pegawai harian, sehingga
> hierarki dan perhitungan gaji untuk empat jenis pegawai dapat ditampilkan.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![Main.java sebelum perubahan](screenshots/Java-Main-sebelum.png)

- **After**:

  ![Main.java sesudah perubahan](screenshots/Java-Main-sesudah.png)

**Output Program:**

- **Before:** gaji kedua pegawai dan total beban gaji masih `Rp0,00`.

  ![Output Java sebelum perubahan](screenshots/Output-Java-sebelum.png)

- **After:** output menampilkan pegawai tetap Ani Lestari (`Rp7.800.000,00`),
  pegawai kontrak Budi Santoso (`Rp5.000.000,00`), dosen Iman Paryudi
  (`Rp9.900.000,00`), pegawai harian Dimas Prasetya (`Rp7.000.000,00`), dan
  total beban gaji `Rp29.700.000,00`.

  ![Output Java setelah perubahan](screenshots/Output-Java-sesudah.png)

---

## PHP — `Pegawai.php`

**Penjelasan Kode:**

> **Before:** kelas induk dan turunannya berada dalam satu file. Perhitungan
> gaji kelas induk dan pegawai tetap belum selesai, sehingga gaji yang tampil
> masih nol.
>
> **After:** hierarki pegawai dalam satu file PHP dilengkapi. Kelas induk
> memvalidasi gaji pokok dan menyediakan perilaku dasar; pegawai tetap
> menghitung tunjangan masa kerja, dan kelas turunan dosen serta pegawai
> harian menyediakan perhitungan sesuai jenisnya.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![Pegawai.php sebelum perubahan — bagian 1](screenshots/PHP-Pegawai-sebelum-1.png)

  ![Pegawai.php sebelum perubahan — bagian 2](screenshots/PHP-Pegawai-sebelum-2.png)

- **After**:

  ![Pegawai.php sesudah perubahan — bagian 1](screenshots/PHP-Pegawai-sesudah-1.png)

  ![Pegawai.php sesudah perubahan — bagian 2](screenshots/PHP-Pegawai-sesudah-2.png)

  ![Pegawai.php sesudah perubahan — bagian 3](screenshots/PHP-Pegawai-sesudah-3.png)

---

## PHP — `main.php`

**Penjelasan Kode:**

> **Before:** program utama menampilkan pegawai tetap dan kontrak, tetapi
> perhitungan gaji belum dikerjakan. **After:** program juga menguji dosen dan
> pegawai harian, lalu menjumlahkan seluruh beban gaji.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![main.php sebelum perubahan](screenshots/PHP-Main-sebelum.png)

- **After**:

  ![main.php sesudah perubahan](screenshots/PHP-Main-sesudah.png)

**Output Program:**

- **Before:** dua jenis pegawai ditampilkan dengan gaji nol.

  ![Output PHP sebelum perubahan](screenshots/Output-PHP-sebelum.png)

- **After:** PHP menghasilkan nilai gaji yang sama dengan program Java dan
  total `Rp29.700.000,00`.

  ![Output PHP setelah perubahan](screenshots/Output-PHP-sesudah.png)

---

## Hasil Pengujian

- Gaji pokok negatif ditolak.
- Pegawai tetap menerima tunjangan masa kerja dengan batas maksimum.
- Pegawai kontrak menggunakan perhitungan gaji dasar dari kelas induk.
- Dosen dan pegawai harian menerapkan perhitungan khusus masing-masing.
- Program Java dan PHP menampilkan total beban gaji `Rp29.700.000,00`.

## Catatan dan Kesimpulan

Pewarisan menempatkan data dan perilaku bersama pada kelas `Pegawai`.
`PegawaiTetap`, `PegawaiKontrak`, `Dosen`, dan `PegawaiHarian` memakai atau
mengembangkan perilaku tersebut sesuai jenisnya. Implementasi Java dan PHP
menghasilkan perhitungan gaji yang konsisten.
