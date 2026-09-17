# Praktikum PBO Pertemuan 03

## Judul Tugas

**Constructor Berdelegasi, Anggota Statis, dan Konstanta pada Rekening Bank**

## Identitas

- Nama: Farid
- NIM: 4525210105
- Mata Kuliah: Praktikum Pemrograman Berorientasi Objek
- Pertemuan: 03

## Deskripsi

Pada tugas ini dibuat class `RekeningBank` dalam Java dan PHP untuk menerapkan:

- Constructor berdelegasi dan constructor dengan saldo awal.
- Named constructor `rekeningPelajar()` pada PHP.
- Konstanta untuk bunga tahunan, biaya administrasi, dan batas penarikan.
- Anggota statis untuk menghitung jumlah rekening yang dibuat.
- Validasi nomor rekening, pemilik, saldo, setoran, dan penarikan.
- Operasi setor, tarik, potong biaya administrasi, dan perhitungan bunga.

## Screenshot Program

running.png

## Screenshot Hasil Running

program.png

## Cara Menjalankan

### Java

```bash
javac Main.java RekeningBank.java
java Main
```

### PHP

```bash
php main.php
```

## Hasil Running

```text
Jumlah rekening di awal: 0
Rekening[111] Ani            Rp1.000.000,00
Rekening[222] Budi           Rp0,00
Rekening[333] Citra          Rp250.000,00
Jumlah rekening sekarang: 3   (seharusnya 3)

=== Operasi ===
Setelah setor 500.000  -> Rekening[111] Ani            Rp1.500.000,00
  Ditolak: Jumlah penarikan melebihi saldo
Budi setelah potong admin: Rekening[222] Budi           Rp0,00   (saldo tidak boleh negatif)
Bunga setahun dari saldo Ani: Rp37.500,00
```