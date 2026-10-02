<?php
declare(strict_types=1);

/**
 * Langkah 6 — latihan mandiri.
 *
 * Buat hierarki Notifikasi dengan tiga turunan: Email, SMS, WhatsApp.
 * Lalu lengkapi kirimSemua() TANPA satu pun pemeriksaan tipe.
 */

abstract class Notifikasi
{
    public function __construct(protected readonly string $tujuan)
    {
        if (trim($tujuan) === '') {
            throw new InvalidArgumentException('Tujuan notifikasi tidak boleh kosong.');
        }
    }

    abstract public function kirim(string $pesan): void;

    public function saluran(): string
    {
        return static::class;
    }
}


class Email extends Notifikasi
{
    public function kirim(string $pesan): void
    {
        printf('[%s] Email ke %s: %s%s', $this->saluran(), $this->tujuan, $pesan, PHP_EOL);
    }
}

class SMS extends Notifikasi
{
    public function kirim(string $pesan): void
    {
        printf('[%s] SMS ke %s: %s%s', $this->saluran(), $this->tujuan, $pesan, PHP_EOL);
    }
}

class WhatsApp extends Notifikasi
{
    public function kirim(string $pesan): void
    {
        printf('[%s] WhatsApp ke %s: %s%s', $this->saluran(), $this->tujuan, $pesan, PHP_EOL);
    }
}


/**
 * Kirim pesan ke seluruh notifikasi dalam daftar.
 *
 * ATURAN: tidak boleh ada instanceof, tidak boleh ada match/switch
 *         atas jenis notifikasi. Kalau Anda merasa membutuhkannya,
 *         berarti hierarki Anda belum benar.
 *
 * @param Notifikasi[] $daftar
 */
function kirimSemua(array $daftar, string $pesan): void
{
    foreach ($daftar as $notifikasi) {
        $notifikasi->kirim($pesan);
    }
}

// Uji ketiga implementasi melalui kontrak Notifikasi.
kirimSemua([
    new Email('ani@univpancasila.ac.id'),
    new SMS('081234567890'),
    new WhatsApp('081234567890'),
], 'Buku yang Anda pesan sudah tersedia.');
