# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
|---|---|
| **Nama** | Farid Zhahir Muttaqin |
| **NPM** | 4525210105 |
| **Kelas** | A |
| **Mata Kuliah** | Praktikum Pemrograman Berorientasi Objek (PBO) |
| **Pertemuan** | 07 — Komposisi dan Agregasi |
| **Tanggal** | 15 Oktober 2026 |

---

## Java — `Buku.java`

**Penjelasan Kode:**

> **Before:** validasi stok, pembuatan Bab, dan pengurangan stok belum
> diimplementasikan. `getBab()` mengembalikan daftar internal langsung,
> sehingga pemanggil dapat mengosongkan daftar dan merusak relasi komposisi.
>
> **After:** constructor menolak jumlah eksemplar negatif, `tambahBab()`
> membuat objek Bab di dalam Buku, dan `kurangiStok()` menolak stok yang sudah
> nol. `getBab()` menggunakan `List.copyOf()` agar daftar internal tidak dapat
> diubah dari luar. Constructor Bab yang privat memastikan Bab hanya dibuat
> oleh Buku.

**Bukti Eksekusi (Screenshot):**

- **Before** _(source awal)_:

  ![Buku.java sebelum perubahan — bagian 1](screenshots/sebelum-java-buku-01.png)

  ![Buku.java sebelum perubahan — bagian 2](screenshots/sebelum-java-buku-02.png)

- **After** _(source setelah perubahan)_:

  ![Buku.java sesudah perubahan — bagian 1](screenshots/sesudah-java-buku-01.png)

  ![Buku.java sesudah perubahan — bagian 2](screenshots/sesudah-java-buku-02.png)

---

## Java — `Jurusan.java`

**Penjelasan Kode:**

> **Before:** method `daftarkan()` dan `keluarkan()` belum memiliki
> implementasi. `getMahasiswa()` membuka daftar internal, sehingga pemanggil
> dapat mengubah isi Jurusan secara langsung.
>
> **After:** `daftarkan()` menolak `null` dan tidak menambahkan mahasiswa yang
> sama dua kali. `keluarkan()` hanya melepas referensi mahasiswa dari Jurusan;
> objeknya tetap hidup selama masih digunakan dari luar. `getMahasiswa()`
> mengembalikan salinan tak-berubah. Ini menunjukkan agregasi: Mahasiswa
> dibuat di luar Jurusan dan tidak bergantung pada siklus hidupnya.

**Bukti Eksekusi (Screenshot):**

- **Before** _(source awal)_:

  ![Jurusan.java sebelum perubahan — bagian 1](screenshots/sebelum-java-jurusan-01.png)

  ![Jurusan.java sebelum perubahan — bagian 2](screenshots/sebelum-java-jurusan-02.png)

- **After** _(source setelah perubahan)_:

  ![Jurusan.java sesudah perubahan — bagian 1](screenshots/sesudah-java-jurusan-01.png)

  ![Jurusan.java sesudah perubahan — bagian 2](screenshots/sesudah-java-jurusan-02.png)

---

## Java — `PerpustakaanService.java`

**Penjelasan Kode:**

> **Before:** layanan masih berupa kerangka. Koleksi belum disimpan,
> `cariKoleksi()` selalu mengembalikan `null`, sedangkan pinjam dan
> pengembalian belum melakukan apa pun.
>
> **After:** layanan menyimpan Buku berdasarkan nomor induk dan menolak nomor
> duplikat. Peminjaman memeriksa koleksi, stok, dan batas tiga koleksi aktif
> untuk setiap nomor anggota sebelum mengurangi stok. Pengembalian harus cocok
> dengan pinjaman aktif anggota dan akan memulihkan stok. Karena source sesi
> ini belum memiliki kelas `Anggota` dan `Peminjaman`, nomor anggota digunakan
> sebagai identitas peminjam.

**Bukti Eksekusi (Screenshot):**

- **Before** _(source awal)_:

  ![PerpustakaanService.java sebelum perubahan](screenshots/sebelum-java-service-01.png)

- **After** _(source setelah perubahan)_:

  ![PerpustakaanService.java sesudah perubahan — bagian 1](screenshots/sesudah-java-service-01.png)

  ![PerpustakaanService.java sesudah perubahan — bagian 2](screenshots/sesudah-java-service-02.png)

---

## Java — `Main.java`

**Penjelasan Kode:**

> **Before:** program demonstrasi sudah memanggil operasi komposisi dan
> agregasi, tetapi implementasi model yang belum selesai membuat buku
> menampilkan 0 Bab dan jurusan menampilkan 0 mahasiswa. Percobaan mengubah
> daftar Bab dari luar juga berhasil.
>
> **After:** kode demonstrasi tetap sama; hasilnya berubah setelah class model
> dilengkapi. Program menampilkan tiga Bab dan dua mahasiswa, menolak
> perubahan daftar Bab dari luar, dan memperlihatkan bahwa Budi tetap ada
> setelah dikeluarkan dari Jurusan.

**Bukti Eksekusi (Screenshot):**

- **Before** _(source awal)_:

  ![Main.java sebelum perubahan](screenshots/sebelum-java-main-01.png)

- **After** _(source setelah perubahan)_:

  ![Main.java sesudah perubahan](screenshots/sesudah-java-main-01.png)

**Output Program:**

- **Before**:

  ![Output Java sebelum perubahan](screenshots/output-java-sebelum.png)

- **After**:

  ![Output Java sesudah perubahan](screenshots/output-java-sesudah.png)

---

## PHP — `relasi.php`

**Penjelasan Kode:**

> **Before:** class `Bab` belum memiliki constructor dan hanya menampilkan
> teks TODO. `Buku` belum membuat Bab atau memvalidasi stok, sementara method
> pengurangan stok, pendaftaran mahasiswa, dan pengeluaran mahasiswa juga
> belum diimplementasikan.
>
> **After:** `Bab` memiliki constructor dengan properti `readonly`, dan Bab
> dibuat oleh `Buku` melalui `tambahBab()`. Stok negatif ditolak dan
> pengurangan stok saat kosong menghasilkan exception. `Jurusan` mencegah
> pendaftaran objek yang sama lebih dari sekali dan dapat melepas mahasiswa
> tanpa menghapus objeknya. PHP mengembalikan array getter secara by-value;
> properti Bab yang `readonly` melindungi data objek di dalam array.

**Bukti Eksekusi (Screenshot):**

- **Before** _(source awal)_:

  ![relasi.php sebelum perubahan — bagian 1](screenshots/sebelum-php-relasi-01.png)

  ![relasi.php sebelum perubahan — bagian 2](screenshots/sebelum-php-relasi-02.png)

  ![relasi.php sebelum perubahan — bagian 3](screenshots/sebelum-php-relasi-03.png)

- **After** _(source setelah perubahan)_:

  ![relasi.php sesudah perubahan — bagian 1](screenshots/sesudah-php-relasi-01.png)

  ![relasi.php sesudah perubahan — bagian 2](screenshots/sesudah-php-relasi-02.png)

  ![relasi.php sesudah perubahan — bagian 3](screenshots/sesudah-php-relasi-03.png)

---

## PHP — `main.php`

**Penjelasan Kode:**

> **Before:** program utama telah menyiapkan demonstrasi komposisi dan
> agregasi, tetapi implementasi di `relasi.php` yang belum lengkap membuat
> Buku menampilkan 0 Bab dan Jurusan menampilkan 0 mahasiswa.
>
> **After:** kode demonstrasi tetap sama. Setelah `relasi.php` dilengkapi,
> Buku menampilkan tiga Bab, Jurusan menampilkan dua mahasiswa, dan mahasiswa
> yang dikeluarkan tetap dapat digunakan oleh pemanggil.

**Bukti Eksekusi (Screenshot):**

- **Before** _(source awal)_:

  ![main.php sebelum perubahan](screenshots/sebelum-php-main-01.png)

- **After** _(source setelah perubahan)_:

  ![main.php sesudah perubahan](screenshots/sesudah-php-main-01.png)

**Output Program:**

- **Before**:

  ![Output PHP sebelum perubahan](screenshots/output-php-sebelum.png)

- **After**:

  ![Output PHP sesudah perubahan](screenshots/output-php-sesudah.png)

---

## Hasil Pengujian

- Source Java berhasil dikompilasi menggunakan `javac -Xlint:all`.
- Program `Main` Java dan `main.php` berhasil dijalankan dari source sebelum
  dan sesudah perubahan.
- `relasi.php` dan `main.php` lolos pemeriksaan sintaks `php -l`.
- Uji perilaku layanan Java memastikan batas tiga pinjaman, pemulihan stok
  setelah pengembalian, penolakan nomor koleksi duplikat, serta penolakan
  pengembalian yang tidak cocok.
- Uji komposisi dan agregasi memastikan daftar Bab Java tak dapat diubah dari
  luar, pendaftaran mahasiswa ganda dihindari, dan objek mahasiswa tetap ada
  setelah dilepas dari Jurusan.

## Catatan dan Kesimpulan

`Buku` dan `Bab` menerapkan komposisi: Bab dibuat di dalam Buku dan tidak
memiliki constructor publik di Java. `Jurusan` dan `Mahasiswa` menerapkan
agregasi: objek mahasiswa berasal dari luar dan tetap hidup di luar Jurusan.
Catatan mengenai cara PHP menjaga komposisi dan salinan array dijelaskan pada
[`bukti-relasi.md`](bukti-relasi.md), sedangkan keputusan desain layanan ada
di [`analisis.md`](analisis.md).

Layanan pada pertemuan ini mengelola objek `Buku` dan nomor anggota. Tanggal
jatuh tempo, denda, status rusak/hilang, serta jenis koleksi lain belum
dimodelkan karena class `Koleksi`, `Anggota`, dan `Peminjaman` belum tersedia
pada source sesi ini.
