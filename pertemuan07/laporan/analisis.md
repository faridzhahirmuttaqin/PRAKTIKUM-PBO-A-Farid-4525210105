# Analisis Desain

`PerpustakaanService` mengagregasi objek `Buku`: buku dibuat di luar layanan,
lalu diberikan melalui `tambahKoleksi`. Layanan tidak membuat atau menghapus
objek buku. Buku dicari berdasarkan nomor induk; nomor yang sama tidak boleh
didaftarkan dua kali.

Pada kerangka sesi ini belum tersedia kelas `Anggota`, sehingga layanan
mencatat nomor anggota sebagai kunci peminjaman, tanpa membuat objek anggota.
Catatan peminjaman aktif menyimpan nomor induk buku dan membatasi setiap anggota
hingga tiga koleksi sekaligus. Peminjaman mengurangi stok dan pengembalian
menambahkannya kembali.
