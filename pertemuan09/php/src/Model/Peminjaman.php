<?php
declare(strict_types=1);

namespace Perpustakaan\Model;

use Carbon\CarbonImmutable;

final class Peminjaman
{
    private ?CarbonImmutable $tanggalKembali = null;

    public function __construct(
        private readonly string $id,
        private readonly Anggota $anggota,
        private readonly Buku $buku,
        private readonly CarbonImmutable $tanggalPinjam,
        private readonly CarbonImmutable $tanggalJatuhTempo,
    ) {}

    public function getId(): string
    {
        return $this->id;
    }

    public function getAnggota(): Anggota
    {
        return $this->anggota;
    }

    public function getBuku(): Buku
    {
        return $this->buku;
    }

    public function getTanggalPinjam(): CarbonImmutable
    {
        return $this->tanggalPinjam;
    }

    public function getTanggalJatuhTempo(): CarbonImmutable
    {
        return $this->tanggalJatuhTempo;
    }

    public function sudahDikembalikan(): bool
    {
        return $this->tanggalKembali !== null;
    }

    public function kembalikan(CarbonImmutable $tanggalKembali): void
    {
        if ($this->sudahDikembalikan()) {
            throw new \LogicException('Peminjaman sudah dikembalikan');
        }
        $this->tanggalKembali = $tanggalKembali;
    }
}