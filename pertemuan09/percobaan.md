# Percobaan Namespace dan Package

## PHP: namespace tidak cocok dengan lokasi

`src/Model/Buku.php` sementara diubah dari `Perpustakaan\Model` menjadi `Perpustakaan\Domain`, tanpa memindahkan berkas. Saat `php main.php` dijalankan, PHP melaporkan:

```text
PHP Fatal error: Uncaught Error: Class "Perpustakaan\Model\Buku" not found
```

Composer mencari `Perpustakaan\Model\Buku` pada `src/Model/Buku.php`. Deklarasi namespace yang berbeda membuat kelas yang dicari tidak tersedia. Perbaikan yang diuji: kembalikan namespace menjadi `Perpustakaan\Model`, lalu bangun ulang peta dengan `composer dump-autoload`. Alternatifnya, pindahkan kelas ke `src/Domain/Buku.php` dan ubah pemanggil menjadi `Perpustakaan\Domain\Buku`.

## Java: package tidak cocok dengan lokasi

`src/main/java/id/ac/univpancasila/perpustakaan/model/Buku.java` sementara diberi deklarasi `package id.ac.univpancasila.perpustakaan.domain;`. `mvn clean compile` gagal. Pesan utama compiler:

```text
duplicate class: id.ac.univpancasila.perpustakaan.domain.Buku
bad source file: .../model/Buku.java
file does not contain class id.ac.univpancasila.perpustakaan.model.Buku
```

Import dari `App` dan `PerpustakaanService` masih merujuk ke package `model`, sehingga kelas tidak ditemukan. Perbaikan yang diuji: kembalikan deklarasi menjadi `package id.ac.univpancasila.perpustakaan.model;`. Setelah itu `mvn clean compile` kembali menghasilkan `BUILD SUCCESS`.

## Artefak Build

`target/` dan `vendor/` berisi hasil build atau dependency yang dapat dibuat ulang, sehingga tidak perlu disimpan di repositori. `composer.lock` perlu di-commit agar semua mesin memasang versi dependency yang sama.