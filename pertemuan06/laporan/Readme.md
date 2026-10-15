# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
|---|---|
| **Nama** | Farid Zhahir Muttaqin |
| **NPM** | 4525210105 |
| **Kelas** | A |
| **Mata Kuliah** | Praktikum Pemrograman Berorientasi Objek (PBO) |
| **Pertemuan** | 06 — Abstraksi, Interface, Enum, dan Trait |
| **Tanggal** | Tidak dicantumkan |

---

## Java — `Movable.java`

**Penjelasan Kode:**

> **Before:** kontrak kemampuan bergerak belum menetapkan perilaku yang
> dipakai bersama oleh kendaraan.
>
> **After:** interface `Movable` menyediakan kontrak gerak dan method default
> untuk menampilkan kecepatan maksimum.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![Movable.java sebelum perubahan](screenshots/Java-Movable-sebelum.png)

- **After**:

  ![Movable.java sesudah perubahan](screenshots/Java-Movable-sesudah.png)

---

## Java — `Fuelable.java`

**Penjelasan Kode:**

> **Before:** kontrak pengisian bahan bakar belum dilengkapi untuk
> membatasi operasi hanya pada objek yang dapat diisi bahan bakar.
>
> **After:** interface `Fuelable` menetapkan perilaku pengisian. Mobil
> menerapkannya; Sepeda tidak, sehingga Sepeda tidak dapat diberikan kepada
> method yang mensyaratkan `Fuelable`.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![Fuelable.java sebelum perubahan](screenshots/Java-Fuelable-sebelum.png)

- **After**:

  ![Fuelable.java sesudah perubahan](screenshots/Java-Fuelable-sesudah.png)

---

## Java — `Kendaraan.java`

**Penjelasan Kode:**

> **Before:** kelas abstrak belum menyediakan state dan perilaku dasar
> kendaraan dengan lengkap.
>
> **After:** kelas abstrak menyimpan data umum kendaraan serta menyediakan
> dasar implementasi bagi Mobil dan Sepeda.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![Kendaraan.java sebelum perubahan](screenshots/Java-Kendaraan-sebelum.png)

- **After**:

  ![Kendaraan.java sesudah perubahan](screenshots/Java-Kendaraan-sesudah.png)

---

## Java — `Mobil.java`

**Penjelasan Kode:**

> **Before:** mobil belum menjalankan perilaku gerak dan pengisian bahan bakar
> secara lengkap; kecepatan dan hasil pengisian masih bernilai default.
>
> **After:** Mobil mewarisi data umum kendaraan, menerapkan `Movable` dan
> `Fuelable`, menjalankan perilaku gerak, serta memvalidasi jumlah bahan bakar
> dan kapasitas tangki.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![Mobil.java sebelum perubahan](screenshots/Java-Mobil-sebelum.png)

- **After**:

  ![Mobil.java sesudah perubahan](screenshots/Java-Mobil-sesudah.png)

---

## Java — `Sepeda.java`

**Penjelasan Kode:**

> Sepeda ditambahkan pada implementasi akhir sebagai kendaraan yang dapat
> bergerak, tetapi tidak dapat diisi bahan bakar. Karena itu Sepeda
> menerapkan `Movable`, bukan `Fuelable`.

**Bukti Eksekusi (Screenshot):**

- **After**:

  ![Sepeda.java sesudah perubahan](screenshots/Java-Sepeda-sesudah.png)

---

## Java — `TipeBahanBakar.java`

**Penjelasan Kode:**

> **Before:** enum belum menyediakan label yang dapat ditampilkan,
> perhitungan biaya pengisian, atau informasi keramahan lingkungan.
>
> **After:** setiap jenis bahan bakar menyimpan label, harga, dan informasi
> lingkungan, serta menyediakan perhitungan biaya berdasarkan jumlah yang
> diisi.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![TipeBahanBakar.java sebelum perubahan](screenshots/Java-TipeBahanBakar-sebelum.png)

- **After**:

  ![TipeBahanBakar.java sesudah perubahan](screenshots/Java-TipeBahanBakar-sesudah.png)

---

## Java — `Main.java`

**Penjelasan Kode:**

> **Before:** program belum mendemonstrasikan seluruh kemampuan kendaraan
> dan varian enum; gerak belum dikerjakan, kecepatan nol, dan label serta biaya
> bahan bakar belum tersedia.
>
> **After:** program menampilkan Mobil dan Sepeda yang bergerak, pengisian
> penuh mobil, serta perilaku semua jenis bahan bakar.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![Main.java sebelum perubahan](screenshots/Java-Main-sebelum.png)

- **After**:

  ![Main.java sesudah perubahan](screenshots/Java-Main-sesudah.png)

**Output Program:**

- **Before**: kecepatan `0 km/jam`, label bahan bakar `?`, dan biaya
  pengisian `Rp0`.

  ![Output Java sebelum perubahan](screenshots/Output-Java-sebelum.png)

- **After**: program menampilkan perilaku gerak, biaya penuh mobil
  `Rp540.000`, serta biaya untuk bensin, solar, dan listrik.

  ![Output Java sesudah perubahan](screenshots/Output-Java-sesudah.png)

---

## PHP — `abstraksi.php`

**Penjelasan Kode:**

> **Before:** deklarasi interface, enum, trait, dan kelas berada dalam satu
> file. Perilaku gerak belum dikerjakan, nilai kecepatan masih nol, enum
> belum menyediakan label/perhitungan biaya, dan trait belum digunakan
> sebagai demonstrasi.
>
> **After:** file melengkapi interface gerak dan pengisian, abstract class
> kendaraan, enum bahan bakar, serta trait `Loggable`. Mobil dan Sepeda
> menunjukkan kemampuan yang berbeda; trait digunakan oleh Mobil dan
> Pesanan meskipun keduanya tidak sekerabat.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![abstraksi.php sebelum perubahan — bagian 1](screenshots/PHP-Abstraksi-sebelum-1.png)

  ![abstraksi.php sebelum perubahan — bagian 2](screenshots/PHP-Abstraksi-sebelum-2.png)

  ![abstraksi.php sebelum perubahan — bagian 3](screenshots/PHP-Abstraksi-sebelum-3.png)

- **After**:

  ![abstraksi.php sesudah perubahan — bagian 1](screenshots/PHP-Abstraksi-sesudah-1.png)

  ![abstraksi.php sesudah perubahan — bagian 2](screenshots/PHP-Abstraksi-sesudah-2.png)

  ![abstraksi.php sesudah perubahan — bagian 3](screenshots/PHP-Abstraksi-sesudah-3.png)

  ![abstraksi.php sesudah perubahan — bagian 4](screenshots/PHP-Abstraksi-sesudah-4.png)

---

## PHP — `main.php`

**Penjelasan Kode:**

> **Before:** program utama memperlihatkan perilaku yang belum lengkap:
> kendaraan belum bergerak sebagaimana mestinya, nilai kecepatan nol, dan
> enum belum menampilkan informasi biaya.
>
> **After:** program menampilkan gerak Mobil dan Sepeda, mengisi bahan bakar
> Mobil, menguji semua nilai enum, serta menggunakan trait log pada Mobil dan
> Pesanan. Timestamp log berubah sesuai waktu program dijalankan.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![main.php sebelum perubahan](screenshots/PHP-Main-sebelum.png)

- **After**:

  ![main.php sesudah perubahan](screenshots/PHP-Main-sesudah.png)

**Output Program:**

- **Before**:

  ![Output PHP sebelum perubahan](screenshots/Output-PHP-sebelum.png)

- **After**: pengisian penuh Mobil menghasilkan biaya `Rp540.000`; enum
  menampilkan biaya bensin, solar, dan listrik; log menunjukkan trait dipakai
  oleh Mobil dan Pesanan.

  ![Output PHP sesudah perubahan](screenshots/Output-PHP-sesudah.png)

---

## Hasil Pengujian

- Mobil dapat bergerak dan mengisi bahan bakar; Sepeda dapat bergerak tanpa
  menerapkan kemampuan pengisian.
- Pengisian bahan bakar divalidasi berdasarkan jumlah dan kapasitas tangki.
- Enum menampilkan label, biaya, dan status keramahan lingkungan untuk setiap
  jenis bahan bakar.
- Trait PHP `Loggable` digunakan oleh kelas berbeda yang tidak berkerabat.
- Kontrak pengisian ditegakkan oleh tipe: Java menolak `isiPenuh(sepeda)` saat
  kompilasi, sedangkan PHP menghasilkan `TypeError` jika dipanggil.

## Catatan dan Kesimpulan

Interface memisahkan kemampuan bergerak dan mengisi bahan bakar, sehingga
kelas hanya menerapkan kontrak yang sesuai. Abstract class menyimpan perilaku
umum kendaraan, enum mengikat data bahan bakar dengan perilakunya, dan trait
PHP memungkinkan perilaku log dipakai oleh kelas yang tidak memiliki hubungan
pewarisan. Rincian pengujian kontrak dicatat pada
[`keputusan.md`](keputusan.md).
