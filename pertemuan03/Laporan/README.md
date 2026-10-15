# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
|---|---|
| **Nama** | Farid Zhahir Muttaqin |
| **NPM** | 4525210105 |
| **Kelas** | A |
| **Mata Kuliah** | Praktikum Pemrograman Berorientasi Objek (PBO) |
| **Pertemuan** | 03 — Constructor Berdelegasi, Anggota Statis, dan Konstanta |
| **Tanggal** | Tidak dicantumkan |

---

## Java — `RekeningBank.java`

**Penjelasan Kode:**

> **Before:** kelas rekening masih memiliki TODO. Constructor ringkas belum
> mendelegasikan proses pembuatan objek, jumlah rekening dan transaksi belum
> dihitung dengan benar, dan bunga masih bernilai nol.
>
> **After:** konstanta menyimpan bunga tahunan, biaya administrasi, dan batas
> penarikan. Anggota statis menghitung jumlah rekening, constructor ringkas
> mendelegasikan ke constructor lengkap, dan validasi melindungi identitas,
> saldo, setoran, serta penarikan. Perhitungan bunga dan biaya administrasi
> juga dilengkapi.

**Bukti Eksekusi (Screenshot):**

- **Before** _(source awal)_:

  ![RekeningBank.java sebelum perubahan — bagian 1](screenshots/Java-RekeningBank-sebelum-1.png)

  ![RekeningBank.java sebelum perubahan — bagian 2](screenshots/Java-RekeningBank-sebelum-2.png)

  ![RekeningBank.java sebelum perubahan — bagian 3](screenshots/Java-RekeningBank-sebelum-3.png)

- **After** _(source setelah perubahan)_:

  ![RekeningBank.java sesudah perubahan — bagian 1](screenshots/Java-RekeningBank-sesudah-1.png)

  ![RekeningBank.java sesudah perubahan — bagian 2](screenshots/Java-RekeningBank-sesudah-2.png)

  ![RekeningBank.java sesudah perubahan — bagian 3](screenshots/Java-RekeningBank-sesudah-3.png)

---

## PHP — `RekeningBank.php`

**Penjelasan Kode:**

> **Before:** named constructor `rekeningPelajar()` belum dikerjakan dan
> pengujian awal berhenti dengan pesan TODO.
>
> **After:** named constructor rekening pelajar, konstanta, dan aturan
> transaksi dilengkapi agar implementasi PHP menerapkan perilaku rekening
> yang sama: rekening terhitung, transaksi divalidasi, dan bunga dapat
> dihitung.

**Bukti Eksekusi (Screenshot):**

- **Before** _(source awal)_:

  ![RekeningBank.php sebelum perubahan — bagian 1](screenshots/PHP-RekeningBank-sebelum-1.png)

  ![RekeningBank.php sebelum perubahan — bagian 2](screenshots/PHP-RekeningBank-sebelum-2.png)

- **After** _(source setelah perubahan)_:

  ![RekeningBank.php sesudah perubahan — bagian 1](screenshots/PHP-RekeningBank-sesudah-1.png)

  ![RekeningBank.php sesudah perubahan — bagian 2](screenshots/PHP-RekeningBank-sesudah-2.png)

  ![RekeningBank.php sesudah perubahan — bagian 3](screenshots/PHP-RekeningBank-sesudah-3.png)

**Output Pengujian RekeningBank PHP:**

- **Before**:

  ![Output PHP RekeningBank sebelum perubahan](screenshots/Output-PHP-sebelum.png)

- **After**:

  ![Output PHP RekeningBank setelah perubahan](screenshots/Output-PHP-sesudah.png)

---

## Java — `Main.java`

**Penjelasan Kode:**

> `Main.java` yang digunakan adalah program utama aktual dari
> `pertemuan03/java`. Selain rekening, program tersebut menampilkan daftar
> gaji pegawai. Pada uji Before, kelas `RekeningBank` memakai source awal,
> sedangkan kelas pegawai digunakan sebagai dependensi program utama.

**Bukti Eksekusi (Screenshot):**

- **Program utama**:

  ![Main.java pertemuan 03 — bagian 1](screenshots/Java-Main-program-1.png)

  ![Main.java pertemuan 03 — bagian 2](screenshots/Java-Main-program-2.png)

**Output Program:**

- **Before:** jumlah rekening `-1`, setoran tidak memperbarui saldo, dan
  bunga masih `Rp0,00`.

  ![Output Java sebelum perubahan](screenshots/Output-Java-sebelum.png)

- **After:** jumlah rekening tercatat `3`, setoran menaikkan saldo Ani menjadi
  `Rp1.500.000,00`, penarikan tidak valid ditolak, dan bunga tahunan
  menghasilkan `Rp37.500,00`. Daftar gaji menampilkan total
  `Rp27.700.000,00`.

  ![Output Java setelah perubahan](screenshots/Output-Java-sesudah.png)

---

## PHP — `main.php`

**Penjelasan Kode:**

> `pertemuan03/php/main.php` yang tersedia menguji daftar gaji pegawai; file
> ini belum menjalankan operasi rekening. Screenshot program utama tetap
> disertakan agar ruang lingkup program aktual jelas.

**Bukti Eksekusi (Screenshot):**

- **Program utama PHP**:

  ![main.php pertemuan 03](screenshots/PHP-Main-program.png)

**Output Program:**

- **After:** program utama PHP menampilkan dua pegawai dengan total gaji
  `Rp12.800.000,00`.

  ![Output main.php PHP pertemuan 03](screenshots/Output-PHP-program.png)

- Pengujian rekening PHP memakai driver rekening terpisah. Pada source awal
  named constructor masih menghasilkan TODO; setelah perubahan, pengujian
  berhasil.

  ![Output pengujian RekeningBank PHP sebelum perubahan](screenshots/Output-PHP-sebelum.png)

  ![Output pengujian RekeningBank PHP setelah perubahan](screenshots/Output-PHP-sesudah.png)

---

## Hasil Pengujian

- Constructor ringkas mendelegasikan inisialisasi ke constructor lengkap.
- Jumlah rekening bertambah sesuai jumlah objek yang dibuat.
- Setoran memperbarui saldo; transaksi yang tidak memenuhi aturan ditolak.
- Bunga tahunan dihitung sesuai saldo dan tarif.
- Output Java dan uji driver rekening PHP membuktikan perilaku rekening
  sebelum dan sesudah implementasi.

## Catatan dan Kesimpulan

Konstanta dan anggota statis digunakan untuk nilai serta data yang berlaku
untuk seluruh objek rekening. Delegasi constructor menjaga inisialisasi
konsisten, sedangkan validasi memastikan operasi saldo mengikuti aturan.
Program utama PHP yang tersedia menguji daftar gaji dan terpisah dari driver
rekening PHP yang digunakan untuk menguji named constructor.
