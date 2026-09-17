# Percobaan Static

## 1. Percobaan static menghitung jumlah rekening

```java
System.out.println("Jumlah rekening di awal: " + RekeningBank.getJumlahRekening());

RekeningBank a = new RekeningBank("111", "Ani", 1_000_000);
RekeningBank b = new RekeningBank("222", "Budi");
RekeningBank c = new RekeningBank("333", "Citra", 250_000);

System.out.println("Jumlah rekening sekarang: " + RekeningBank.getJumlahRekening());
```

### Hasil

```text
Jumlah rekening di awal: 0
Jumlah rekening sekarang: 3
```

### Keterangan

Variabel `jumlahRekening` dibuat `static`, sehingga semua objek rekening ikut menghitung jumlah rekening yang dibuat.

---

## 2. Percobaan static pada method bunga

```java
System.out.printf("Bunga setahun dari saldo Ani: Rp%,.2f%n",
        RekeningBank.bungaSetahun(a.getSaldo()));
```

### Hasil

```text
Bunga setahun dari saldo Ani: Rp37.500,00
```

### Keterangan

Method `bungaSetahun()` bersifat `static`, jadi bisa dipanggil tanpa membuat objek baru.

---

## 3. Percobaan static saat transaksi ditolak

```java
try {
    a.tarik(9_999_999);
    System.out.println("MASALAH: penarikan melebihi batas seharusnya ditolak!");
} catch (RuntimeException e) {
    System.out.println("Ditolak: " + e.getMessage());
}
```

### Hasil

```text
Ditolak: Jumlah penarikan melebihi saldo
```

### Keterangan

Ini adalah pesan kesalahan ketika transaksi penarikan melebihi saldo atau melebihi batas yang ditentukan static.