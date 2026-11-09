# Praktikum PBO — Pertemuan 10

Implementasi exception handling dan pemrograman defensif pada sistem perpustakaan dengan Java dan PHP. Bahan asli yang belum diubah disimpan di [`ori prak pertemuan/pretemuan10`](<../ori prak pertemuan/pretemuan10>); proyek di folder ini berisi hasil pengerjaan.

## Isi

- [`java/`](java/) — proyek Maven, custom exception, service hasil refactoring, program konsol, ekspor berkas, dan log.
- [`php/`](php/) — proyek Composer dengan fitur yang setara.
- [`skenario-uji.md`](skenario-uji.md) — skenario kegagalan dan hasil pengujian.
- [`laporan/readme.md`](laporan/readme.md) — laporan bergaya Pertemuan 07, dengan source sebelum/sesudah dan ringkasan hasil.

## Menjalankan Java

Gunakan Java 25 dan Maven dari direktori `pertemuan10/java`:

```powershell
mvn clean compile
mvn exec:java -Dexec.mainClass=id.ac.univpancasila.perpustakaan.App
mvn exec:java -Dexec.mainClass=id.ac.univpancasila.perpustakaan.App -Dexec.args="--self-test"
```

Menu konsol: `1` meminjam buku, `2` mengekspor daftar buku, dan `0` keluar. Log ditambahkan ke `java/logs/perpustakaan.log`.

## Menjalankan PHP

Gunakan PHP 8.4+ dan Composer dari direktori `pertemuan10/php`:

```powershell
composer install
php main.php
php main.php --self-test
```

Log ditambahkan ke `php/logs/perpustakaan.log`. `--self-test` menjalankan skenario gagal yang terdokumentasi dan membuktikan penutupan berkas saat ekspor gagal.

## Data demo

ID anggota demo: `1001` dan `1002`. ISBN demo tersedia di katalog kode pada `App.java` dan `main.php`. Setiap proses mulai dengan data contoh baru; peminjaman tidak disimpan antarsesi.
