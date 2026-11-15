# Catatan pengujian

Kasus paling sulit adalah batas maksimal tiga peminjaman. Tes harus mempersiapkan anggota terdaftar, empat buku berbeda dengan stok tersedia, lalu meminjam tiga buku pertama sebelum memastikan peminjaman keempat melempar exception batas. Jika ISBN yang sama dipakai berulang, tes justru menguji duplikasi; jika stok buku tidak tersedia, exception stok habis akan terjadi lebih dahulu. Menyiapkan keadaan awal yang tepat membuat tes hanya membuktikan satu aturan bisnis.

## Cakupan skenario sesi 10 yang diuji

Tes Java dan PHP mencakup ISBN tidak ditemukan, anggota tidak terdaftar, ID anggota negatif, peminjaman ISBN yang sama dua kali, dan batas tiga buku. Tes koleksi juga memeriksa koleksi kosong, pencarian yang tidak ada, filter, map, penjumlahan, pengelompokan, serta daftar tak dapat diubah di Java.
