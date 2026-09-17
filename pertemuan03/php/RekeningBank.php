<?php
declare(strict_types=1);

/**
 * Sesi 3 — PHP tidak punya constructor overloading.
 * Padanannya: default parameter + named constructor (static factory).
 */
class RekeningBank
{
    private const BUNGA_TAHUNAN = 0.025;
    private const BIAYA_ADMINISTRASI = 5000;
    private const BATAS_PENARIKAN_SEKALI = 5000000;

    private static int $jumlahRekening = 0;

    private float $saldo;

    /**
     * Default parameter menggantikan constructor overloading.
    * Validasi dilakukan di satu tempat agar semua cara pembuatan rekening konsisten.
     */
    public function __construct(
        private readonly string $nomor,
        private readonly string $pemilik,
        float $saldoAwal = 0,
    ) {
        if (trim($this->nomor) === '') {
            throw new InvalidArgumentException('Nomor rekening tidak boleh kosong');
        }
        if (trim($this->pemilik) === '') {
            throw new InvalidArgumentException('Pemilik rekening tidak boleh kosong');
        }
        if ($saldoAwal < 0) {
            throw new InvalidArgumentException('Saldo awal tidak boleh negatif');
        }

        $this->saldo = $saldoAwal;
        self::$jumlahRekening++;
    }

    /** Named constructor untuk rekening pelajar dengan saldo awal nol. */
    public static function rekeningPelajar(string $nomor, string $pemilik): static
    {
        return new static($nomor, $pemilik);
    }

    public function setor(float $jumlah): void
    {
        if ($jumlah <= 0) {
            throw new InvalidArgumentException('Jumlah setoran harus > 0');
        }
        $this->saldo += $jumlah;
    }

    public function tarik(float $jumlah): void
    {
        if ($jumlah <= 0) {
            throw new InvalidArgumentException('Jumlah penarikan harus > 0');
        }
        if ($jumlah > $this->saldo) {
            throw new InvalidArgumentException('Jumlah penarikan melebihi saldo');
        }
        if ($jumlah > self::BATAS_PENARIKAN_SEKALI) {
            throw new InvalidArgumentException('Jumlah penarikan melebihi batas transaksi');
        }

        $this->saldo -= $jumlah;
    }

    /** Kurangi saldo sebesar biaya administrasi, tetapi jangan sampai negatif. */
    public function potongBiayaAdmin(): void
    {
        $this->saldo = max(0, $this->saldo - self::BIAYA_ADMINISTRASI);
    }

    /** Kembalikan jumlah rekening yang pernah dibuat. */
    public static function getJumlahRekening(): int
    {
        return self::$jumlahRekening;
    }

    /** Hitung bunga setahun dari pokok tanpa membaca keadaan objek. */
    public static function bungaSetahun(float $pokok): float
    {
        return $pokok * self::BUNGA_TAHUNAN;
    }

    public function getSaldo(): float { return $this->saldo; }
    public function getNomor(): string { return $this->nomor; }

    public function __toString(): string
    {
        return sprintf('Rekening[%s] %-14s Rp%s',
            $this->nomor, $this->pemilik, number_format($this->saldo, 2, ',', '.'));
    }
}
