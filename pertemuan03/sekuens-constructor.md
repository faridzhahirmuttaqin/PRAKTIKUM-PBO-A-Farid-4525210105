# Sekuens Constructor

## Proses Pembuatan Objek

```text
Mulai
  |
  v
Buat objek RekeningBank("111", "Ani", 1000000)
  |
  v
Constructor lengkap dipanggil
  |
  v
Cek validasi nomor rekening
  |
  v
Cek validasi pemilik rekening
  |
  v
Cek saldo awal tidak negatif
  |
  v
Set nilai nomor, pemilik, dan saldo
  |
  v
Tambah jumlahRekening
  |
  v
Objek selesai dibuat
```

## Contoh di program

```java
RekeningBank a = new RekeningBank("111", "Ani", 1_000_000);
RekeningBank b = new RekeningBank("222", "Budi");
```

### Urutan yang terjadi

1. `new RekeningBank("111", "Ani", 1_000_000)` memanggil constructor lengkap.
2. Constructor ringkas `RekeningBank(String nomor, String pemilik)` memanggil constructor lengkap.
3. Constructor lengkap melakukan validasi input.
4. Jika valid, atribut `nomor`, `pemilik`, dan `saldo` diisi.
5. `jumlahRekening` bertambah 1.
6. Objek `a` siap digunakan.

Untuk objek `b`, constructor ringkas dipanggil lalu delegasi ke constructor lengkap dengan saldo awal 0.