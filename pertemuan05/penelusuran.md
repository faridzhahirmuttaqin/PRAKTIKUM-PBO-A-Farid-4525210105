# Catatan Penelusuran Pertemuan 05

## Polimorfisme Bangun Datar

`BangunDatar` menetapkan kontrak `luas()` dan `keliling()`. Setiap kelas turunan menyimpan data bentuknya sendiri dan menyediakan rumusnya. Karena itu, perulangan di `Main` cukup menggunakan tipe induk `BangunDatar`; perulangan tidak perlu mengetahui bentuk konkret objek.

`toString()` dapat berada di kelas induk tetapi tetap menampilkan perhitungan milik kelas turunan karena pemanggilan method instance bersifat dinamis. Ketika `toString()` memanggil `luas()` atau `keliling()`, Java memilih implementasi override sesuai kelas objek saat runtime. Contohnya, objek `Segitiga` menjalankan `Segitiga.luas()`.

Perhitungan luas lingkaran sebaiknya berada di kelas `Lingkaran`, bukan pada method terpusat yang memeriksa tipe objek. Dengan begitu, data dan perilaku yang menggunakannya berada bersama.

## Perbandingan Menambah Bentuk

Pada `AntiPattern`, penambahan bentuk menyentuh setidaknya tiga lokasi: deklarasi tipe data, cabang baru di `hitungLuas`, dan pendaftaran objek di daftar. Jika cabang perhitungan terlupa, program melempar `IllegalArgumentException` karena bentuk tidak dikenal. Banyaknya baris persis bergantung pada format penulisan, tetapi ada beberapa lokasi yang harus dijaga tetap sinkron.

Pada versi polimorfik, tambahkan kelas yang mengimplementasikan kontrak luas lalu daftarkan objeknya di array. Perulangan untuk menjumlahkan luas tidak perlu diubah. Penambahan tipe baru tidak mengharuskan method pusat mengenali tipe itu.

## Rumus dan Validasi

- Lingkaran: luas $\pi r^2$, keliling $2\pi r$; jari-jari harus positif dan finite.
- Persegi: luas $s^2$, keliling $4s$; sisi harus positif dan finite.
- Segitiga: luas memakai rumus Heron, dengan $s=(a+b+c)/2$; semua sisi harus positif dan memenuhi ketaksamaan segitiga.
- Trapesium: luas $(a+b)t/2$, keliling $a+b+c+d$. Konstruktor menerima dua sisi sejajar, tinggi, dan dua sisi miring, dengan urutan argumen tersebut; semua ukuran harus positif dan finite.

Objek demo trapesium memakai sisi sejajar 4 dan 8, tinggi 3, dan dua sisi miring $\sqrt{13}$, sehingga luasnya 18 dan kelilingnya sekitar 19,21.

## Latihan Notifikasi

`Notifikasi` menyimpan tujuan, mendefinisikan kontrak `kirim(pesan)`, dan menyediakan nama saluran. `Email`, `SMS`, dan `WhatsApp` memberi format pengiriman masing-masing. `kirimSemua()` hanya mengulang daftar dan memanggil `kirim()` pada tiap objek; fungsi itu tidak memakai `instanceof`, `match`, atau `switch`.

Hasil total luas untuk daftar bangun adalah 202,94. Versi `AntiPattern` dan `AntiPatternRefaktor` masing-masing menghitung total 184,94.
