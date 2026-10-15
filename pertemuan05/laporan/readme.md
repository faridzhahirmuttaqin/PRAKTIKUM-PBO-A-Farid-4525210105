# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
|---|---|
| **Nama** | Farid Zhahir Muttaqin |
| **NPM** | 4525210105 |
| **Kelas** | A |
| **Mata Kuliah** | Praktikum Pemrograman Berorientasi Objek (PBO) |
| **Pertemuan** | 05 — Polimorfisme |
| **Tanggal** | Tidak dicantumkan |

---

## Java — `BangunDatar.java`

**Penjelasan Kode:**

> **Before:** kelas induk belum menetapkan kontrak abstrak yang digunakan
> bentuk-bentuk geometri untuk luas dan keliling.
>
> **After:** `BangunDatar` menjadi tipe induk abstrak yang menetapkan operasi
> luas dan keliling. Program dapat memproses bentuk-bentuk berbeda melalui
> tipe induk yang sama.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![BangunDatar.java sebelum perubahan](screenshots/Java-BangunDatar-sebelum.png)

- **After**:

  ![BangunDatar.java sesudah perubahan](screenshots/Java-BangunDatar-sesudah.png)

---

## Java — `Lingkaran.java`

**Penjelasan Kode:**

> **Before:** method luas dan keliling masih menghasilkan `0`.
>
> **After:** kelas menghitung luas dengan rumus πr² dan keliling dengan 2πr.
> Jari-jari harus positif dan finite.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![Lingkaran.java sebelum perubahan](screenshots/Java-Lingkaran-sebelum.png)

- **After**:

  ![Lingkaran.java sesudah perubahan](screenshots/Java-Lingkaran-sesudah.png)

---

## Java — `Persegi.java`

**Penjelasan Kode:**

> **Before:** luas dan keliling persegi belum dihitung dan masih menghasilkan
> nilai `0`.
>
> **After:** luas dihitung dari sisi kuadrat dan keliling dari empat kali sisi.
> Nilai sisi divalidasi agar positif dan finite.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![Persegi.java sebelum perubahan](screenshots/Java-Persegi-sebelum.png)

- **After**:

  ![Persegi.java sesudah perubahan](screenshots/Java-Persegi-sesudah.png)

---

## Java — `Segitiga.java`

**Penjelasan Kode:**

> Kelas ini ditambahkan pada hasil akhir. Luas dihitung menggunakan rumus
> Heron, dan panjang sisi diperiksa menggunakan aturan ketaksamaan segitiga.

**Bukti Eksekusi (Screenshot):**

- **After**:

  ![Segitiga.java sesudah perubahan](screenshots/Java-Segitiga-sesudah.png)

---

## Java — `Trapesium.java`

**Penjelasan Kode:**

> Kelas trapesium ditambahkan pada implementasi akhir dengan perhitungan luas
> dan keliling berdasarkan ukuran sisi-sisinya.

**Bukti Eksekusi (Screenshot):**

- **After**:

  ![Trapesium.java sesudah perubahan](screenshots/Java-Trapesium-sesudah.png)

---

## Java — `Main.java`

**Penjelasan Kode:**

> **Before:** program utama hanya menguji lingkaran dan persegi yang
> implementasi luas serta kelilingnya masih nol.
>
> **After:** daftar bentuk mencakup lingkaran, persegi, segitiga, dan
> trapesium. Perulangan memanggil perilaku luas melalui tipe induk
> `BangunDatar`, tanpa memilih rumus menggunakan percabangan tipe.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![Main.java sebelum perubahan](screenshots/Java-Main-sebelum.png)

- **After**:

  ![Main.java sesudah perubahan](screenshots/Java-Main-sesudah.png)

**Output Program:**

- **Before:** perhitungan luas serta keliling yang belum diimplementasikan
  menghasilkan nol.

  ![Output Java sebelum perubahan](screenshots/Output-Java-sebelum.png)

- **After:** keempat bentuk ditampilkan dan total luas program adalah
  `202,94`.

  ![Output Java setelah perubahan](screenshots/Output-Java-sesudah.png)

---

## Java — `AntiPattern.java` dan `AntiPatternRefaktor.java`

**Penjelasan Kode:**

> `AntiPattern.java` menunjukkan pendekatan yang memeriksa jenis objek untuk
> memilih perhitungan. Pada `AntiPatternRefaktor.java`, objek menerapkan
> kontrak `Bangun` dan luas dihitung dengan memanggil perilaku polimorfik
> masing-masing objek. Kedua contoh latihan memakai data yang sama dan
> menghasilkan total `184,94`.

**Bukti Eksekusi (Screenshot):**

- **Before** _(contoh anti-pattern)_:

  ![AntiPattern.java](screenshots/Java-AntiPattern.png)

- **After** _(hasil refaktor polimorfik)_:

  ![AntiPatternRefaktor.java](screenshots/Java-AntiPattern-Refaktor.png)

**Output Program:**

- **Before**:

  ![Output AntiPattern](screenshots/Output-Java-AntiPattern.png)

- **After**:

  ![Output AntiPatternRefaktor](screenshots/Output-Java-AntiPattern-Refaktor.png)

---

## PHP — `BangunDatar.php`

**Penjelasan Kode:**

> **Before:** kelas induk beserta lingkaran dan persegi berada dalam satu file.
> Perhitungan bentuk masih mengembalikan nol.
>
> **After:** kelas induk abstrak menetapkan perilaku polimorfik. Lingkaran,
> persegi, segitiga, dan trapesium menghitung luas serta keliling masing-masing
> dan memvalidasi ukuran yang diperlukan.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![BangunDatar.php sebelum perubahan — bagian 1](screenshots/PHP-BangunDatar-sebelum-1.png)

  ![BangunDatar.php sebelum perubahan — bagian 2](screenshots/PHP-BangunDatar-sebelum-2.png)

- **After**:

  ![BangunDatar.php sesudah perubahan — bagian 1](screenshots/PHP-BangunDatar-sesudah-1.png)

  ![BangunDatar.php sesudah perubahan — bagian 2](screenshots/PHP-BangunDatar-sesudah-2.png)

  ![BangunDatar.php sesudah perubahan — bagian 3](screenshots/PHP-BangunDatar-sesudah-3.png)

---

## PHP — `main.php`

**Penjelasan Kode:**

> **Before:** program utama menggunakan lingkaran dan persegi, tetapi
> perhitungan luas dan kelilingnya belum selesai.
>
> **After:** seluruh bentuk diproses melalui kontrak induk dan luasnya
> dijumlahkan tanpa pemilihan rumus berdasarkan tipe. Output PHP menampilkan
> format desimal bawaan dan total luas `202,94`.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![main.php sebelum perubahan](screenshots/PHP-Main-sebelum.png)

- **After**:

  ![main.php sesudah perubahan](screenshots/PHP-Main-sesudah.png)

**Output Program:**

- **Before**:

  ![Output PHP sebelum perubahan](screenshots/Output-PHP-sebelum.png)

- **After**:

  ![Output PHP setelah perubahan](screenshots/Output-PHP-sesudah.png)

---

## PHP — `notifikasi.php`

**Penjelasan Kode:**

> **Before:** pemanggilan uji notifikasi berada dalam komentar, sehingga
> belum ada output pengiriman.
>
> **After:** `Notifikasi` menjadi kelas induk abstrak dengan implementasi
> konkret `Email`, `SMS`, dan `WhatsApp`. Fungsi `kirimSemua()` memanggil
> perilaku setiap objek tanpa `instanceof`, `match`, atau `switch`.

**Bukti Eksekusi (Screenshot):**

- **Before**:

  ![notifikasi.php sebelum perubahan](screenshots/PHP-Notifikasi-sebelum.png)

- **After**:

  ![notifikasi.php sesudah perubahan](screenshots/PHP-Notifikasi-sesudah.png)

**Output Program:**

- **After**:

  ![Output latihan notifikasi PHP](screenshots/Output-PHP-Notifikasi.png)

---

## Hasil Pengujian

- Luas dan keliling Java serta PHP terhitung untuk bentuk yang diimplementasi.
- Nilai ukuran yang tidak valid ditolak, termasuk sisi segitiga yang tidak
  memenuhi ketaksamaan segitiga.
- Program utama memproses empat bentuk melalui abstraksi yang sama dan
  menghasilkan total luas `202,94`.
- Contoh anti-pattern dan refaktor menghasilkan total `184,94` untuk data
  latihan yang sama.
- Latihan notifikasi PHP mengirimkan pesan melalui implementasi Email, SMS,
  dan WhatsApp.

## Catatan dan Kesimpulan

Polimorfisme memungkinkan program memproses objek dari kelas turunan melalui
kontrak atau kelas induk, tanpa menyebarkan percabangan berdasarkan jenis
objek. Penambahan bentuk baru cukup menyediakan implementasi perilaku luas
dan keliling; perulangan utama tidak perlu diubah untuk memilih rumus.
