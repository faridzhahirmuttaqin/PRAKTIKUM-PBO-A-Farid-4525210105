# Bukti Relasi

## Komposisi: Buku dan Bab

`Buku` membuat setiap `Bab` melalui `tambahBab(nomor, judul)`. Tidak ada
method yang menerima objek `Bab` dari pemanggil. Di Java, constructor `Bab`
bersarang dan privat. PHP tidak mendukung kelas bersarang atau constructor
privat yang hanya dapat dipanggil oleh kelas tertentu; karena itu constructor
`Bab` dapat diakses dari luar, dan aturan bahwa bab hanya dibuat oleh `Buku`
ditegakkan sebagai konvensi pemakaian.

Kedua implementasi mengembalikan koleksi bab yang tidak dapat dipakai untuk
mengubah koleksi internal. Java menggunakan `List.copyOf`. PHP mengembalikan
array by-value: perubahan pada array hasil tidak mengubah properti array
`Buku` (copy-on-write). Objek `Bab` di dalam array tetap berupa referensi objek,
sehingga propertinya dibuat `readonly`.

## Agregasi: Jurusan dan Mahasiswa

`Mahasiswa` dibuat di luar `Jurusan`, lalu diberikan kepada `daftarkan`.
`keluarkan` hanya melepas referensi dari jurusan; variabel pemanggil tetap
memegang objek mahasiswa. Getter mengembalikan koleksi tak-berubah di Java
dan salinan array di PHP.

