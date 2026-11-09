# Skenario Kegagalan — Pertemuan 10

Seluruh skenario dijalankan melalui `--self-test` pada kedua implementasi. Log hasil tangkapan ditulis ke `java/logs/perpustakaan.log` dan `php/logs/perpustakaan.log`.

| No. | Masukan / tindakan | Kegagalan yang diharapkan | Jenis exception | Pesan yang ditampilkan / hasil |
|---:|---|---|---|---|
| 1 | Pinjam `ISBN-TIDAK-ADA` sebagai anggota `1001` | ISBN tidak terdaftar | `BukuTidakDitemukanException` | Buku dengan ISBN `ISBN-TIDAK-ADA` tidak ditemukan |
| 2 | Pinjam buku sebagai anggota `9999` | Anggota tidak terdaftar | `AnggotaTidakDitemukanException` | Anggota dengan ID `9999` tidak terdaftar |
| 3 | Masukkan ISBN `-5` | ISBN negatif ditolak | `IllegalArgumentException` / `InvalidArgumentException` | ISBN tidak boleh bernilai negatif |
| 4 | Masukkan ID anggota `-5` | ID negatif bukan ID anggota valid | `IllegalArgumentException` / `InvalidArgumentException` | ID anggota harus berupa angka positif |
| 5 | Masukkan ID anggota kosong/spasi | ID wajib diisi | `IllegalArgumentException` / `InvalidArgumentException` | ID anggota harus berupa angka positif |
| 6 | Masukkan ISBN kosong/spasi | ISBN wajib diisi | `IllegalArgumentException` / `InvalidArgumentException` | ISBN tidak boleh kosong |
| 7 | Masukkan huruf `abc` pada kolom ID | Masukan nonangka ditolak | `IllegalArgumentException` / `InvalidArgumentException` | ID anggota harus berupa angka positif |
| 8 | Anggota meminjam ISBN yang sama dua kali | Peminjaman duplikat ditolak | `BukuSedangDipinjamException` | Buku sedang dipinjam atau tidak tersedia |
| 9 | Anggota ketiga mencoba meminjam buku dengan satu eksemplar yang sudah dipinjam | Stok habis | `BukuSedangDipinjamException` | Buku sedang dipinjam atau tidak tersedia |
| 10 | Pinjam buku keempat ketika tiga buku masih aktif dipinjam | Batas pinjaman tercapai | `BatasPinjamTerlampauiException` | Batas maksimum tiga buku telah tercapai |
| 11 | Ekspor sengaja gagal sesudah baris pertama | Kegagalan penulisan tidak membiarkan berkas terbuka | `IOException` / `RuntimeException` | Exception tetap tertangkap; berkas dapat dipindah/dihapus sesudahnya |
| 12 | Ekspor daftar buku tanpa kegagalan | Berkas tersimpan dan bisa dibaca kembali | — | Ekspor berhasil; berkas dapat dibuka kembali |

## Hasil aktual

Perintah `--self-test` menampilkan 11 baris `LULUS` pada masing-masing bahasa: 10 skenario kegagalan ditambah ekspor normal. Skenario ekspor gagal juga memeriksa bahwa berkas dapat dipindahkan (Java/PHP) setelah exception dilempar, sebagai bukti sumber daya sudah ditutup.

## Jawaban pertanyaan

1. Masukan kosong, huruf, negatif, ISBN yang tidak ditemukan, anggota yang tidak terdaftar, stok habis, dan batas pinjam adalah permintaan/kondisi domain yang ditangani atau diberi pesan kepada pengguna. Kesalahan pemrograman (misalnya dereferensi `null` yang tidak disengaja) tidak ditelan oleh catch domain. Karena itu service memakai exception domain yang spesifik; validasi permintaan yang tidak sah memakai exception argumen.
2. `PerpustakaanException` diturunkan dari `RuntimeException` di Java. Permintaan tidak sah adalah kegagalan domain yang ditangani pada batas aplikasi, bukan exception teknis yang harus dipaksa ditangani di setiap pemanggil. PHP tidak memiliki checked exception; hierarkinya diturunkan dari `RuntimeException`.
3. Catch kosong akan menelan kegagalan: pemanggil melihat seolah-olah operasi berhasil atau tidak mendapat penjelasan dan jejak. Karena itu seluruh exception yang ditangkap pada jalur operasi dicatat dengan waktu, tipe, dan data penyebabnya.

## Cara verifikasi

Jalankan `mvn exec:java -Dexec.mainClass=id.ac.univpancasila.perpustakaan.App -Dexec.args="--self-test"` dari `java/` dan `php main.php --self-test` dari `php/`. Keluaran terminal dan log menjadi bukti eksekusi; tabel di atas mencatat hasil yang diharapkan.
