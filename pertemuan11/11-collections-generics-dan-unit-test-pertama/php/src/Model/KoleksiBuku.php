<?php
declare(strict_types=1);

namespace Perpustakaan\Model;

use ArrayIterator;
use Countable;
use IteratorAggregate;

final class KoleksiBuku implements IteratorAggregate, Countable
{
    /** @var array<string, Buku> */
    private array $items = [];

    /** @param Buku[] $buku */
    public function __construct(array $buku = [])
    {
        foreach ($buku as $item) {
            $this->simpan($item);
        }
    }

    public function simpan(Buku $buku): void
    {
        $this->items[$buku->isbn] = $buku;
    }

    public function cariByIsbn(string $isbn): ?Buku
    {
        return $this->items[$isbn] ?? null;
    }

    /** @return Buku[] */
    public function semua(): array
    {
        return array_values($this->items);
    }

    public function filter(callable $predikat): self
    {
        return new self(array_values(array_filter($this->items, $predikat)));
    }

    /** @return mixed[] */
    public function map(callable $fungsi): array
    {
        return array_map($fungsi, $this->semua());
    }

    public function jumlahkan(callable $fungsi): float
    {
        return (float) array_reduce(
            $this->semua(),
            static fn (float $total, Buku $item): float => $total + (float) $fungsi($item),
            0.0,
        );
    }

    /** @return array<string, Buku[]> */
    public function kelompokkan(callable $kunci): array
    {
        $hasil = [];
        foreach ($this->items as $item) {
            $namaKelompok = (string) $kunci($item);
            $hasil[$namaKelompok][] = $item;
        }
        return $hasil;
    }

    public function getIterator(): ArrayIterator
    {
        return new ArrayIterator($this->semua());
    }

    public function count(): int
    {
        return count($this->items);
    }
}
