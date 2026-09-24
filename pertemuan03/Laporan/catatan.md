# Catatan Sesi 4

## Langkah 1
`Pegawai` dibuat `abstract`, sehingga objek pegawai umum tidak dibuat secara langsung. Turunan wajib mengimplementasikan `jenis()`.

## Langkah 2
`Pegawai.hitungGaji()` mengembalikan gaji pokok. `PegawaiTetap` memanggil `super.hitungGaji()` lalu menambahkan tunjangan masa kerja. `PegawaiKontrak` tidak perlu override karena hanya menerima gaji pokok.

## Langkah 3
Pemanggilan `super(...)` wajib menjadi pernyataan pertama pada konstruktor turunan Java. Jika dihapus dari `PegawaiTetap`, kompilasi gagal karena konstruktor `Pegawai` tidak memiliki konstruktor tanpa argumen.

Pada PHP, `parent::__construct(...)` tetap dipanggil secara eksplisit agar validasi konstruktor kelas induk dijalankan.

## Langkah 4
`Dosen` mewarisi perhitungan gaji `PegawaiTetap` dan menambahkan tunjangan fungsional. `PegawaiHarian` menghitung gaji per hari dikali jumlah hari kerja.
