# Pilihan koleksi dan null-safety

- **ISBN ke buku — `Map<String, Buku>` / `Repository<Buku>`**: ISBN adalah kunci unik dan pencarian langsung lebih sesuai daripada pemindaian `List`. Implementasi Java menyimpan berdasarkan kunci dan mempertahankan urutan masukan dengan `LinkedHashMap`; PHP menggunakan array asosiatif yang kuncinya ISBN.
- **Anggota — `Set<String>` di Java dan array asosiatif bertipe di PHP**: keanggotaan unik, dan validasi tidak perlu mencari duplikat dalam daftar.
- **Peminjaman — `Map<String, Set<String>>` di Java**: setiap anggota memiliki kumpulan ISBN unik, sehingga duplikasi peminjaman dapat dicegah. PHP menggunakan peta bertingkat dengan nilai `true` untuk maksud yang sama.
- **Laporan**: filter hanya memilih buku dengan stok tersedia; map mengambil judul lalu join menggabungkannya; grouping mengelompokkan jumlah judul per penulis. Hitungan penulis adalah jumlah judul terdaftar, bukan jumlah eksemplar.
- **Null-safety Java**: pencarian buku melalui `Optional<Buku>` (`cariByIsbn`) alih-alih mengembalikan `null`. Kode peminjaman memakai `orElseThrow` saat buku wajib ada. **PHP** menyediakan `?Buku` untuk pencarian koleksi dan `?->` untuk judul opsional.
- Daftar yang dikembalikan `Repository.cariSemua()` adalah salinan tak dapat diubah (`List.copyOf`); koleksi PHP mengembalikan array hasil, bukan referensi ke penyimpanan internal.

## Bukti kegagalan uji

Saat implementasi `Repository.cariById` sengaja diubah sementara menjadi `Optional.empty()`, tes `simpanDanCariBuku` gagal dengan `NoSuchElementException: No value present` pada `RepositoryTest.java:21` ketika `orElseThrow()` dipanggil. Ini memperlihatkan bahwa item yang disimpan tidak sampai ke hasil pencarian. Implementasi sumber dikembalikan ke `Optional.ofNullable(data.get(...))`, lalu seluruh pengujian dijalankan ulang dan lulus.
