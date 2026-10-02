<?php
declare(strict_types=1);

abstract class BangunDatar
{
    public function __construct(private readonly string $nama) {}

    abstract public function luas(): float;
    abstract public function keliling(): float;

    public function getNama(): string { return $this->nama; }

    public function __toString(): string
    {
        return sprintf('%-12s luas=%10.2f  keliling=%10.2f',
            $this->nama, $this->luas(), $this->keliling());
    }
}

class Lingkaran extends BangunDatar
{
    public function __construct(private readonly float $jariJari)
    {
        parent::__construct('Lingkaran');
        if (!is_finite($jariJari) || $jariJari <= 0) {
            throw new InvalidArgumentException('Jari-jari harus berupa angka positif dan finite.');
        }
    }

    public function luas(): float     { return M_PI * $this->jariJari * $this->jariJari; }
    public function keliling(): float { return 2 * M_PI * $this->jariJari; }

    public function getJariJari(): float { return $this->jariJari; }
}

class Persegi extends BangunDatar
{
    public function __construct(private readonly float $sisi)
    {
        parent::__construct('Persegi');
        if (!is_finite($sisi) || $sisi <= 0) {
            throw new InvalidArgumentException('Sisi harus berupa angka positif dan finite.');
        }
    }

    public function luas(): float     { return $this->sisi * $this->sisi; }
    public function keliling(): float { return 4 * $this->sisi; }
}

class Segitiga extends BangunDatar
{
    public function __construct(
        private readonly float $sisiA,
        private readonly float $sisiB,
        private readonly float $sisiC
    ) {
        parent::__construct('Segitiga');
        if (!is_finite($sisiA) || !is_finite($sisiB) || !is_finite($sisiC)
            || $sisiA <= 0 || $sisiB <= 0 || $sisiC <= 0
            || $sisiA + $sisiB <= $sisiC || $sisiA + $sisiC <= $sisiB
            || $sisiB + $sisiC <= $sisiA) {
            throw new InvalidArgumentException('Ketiga sisi harus membentuk segitiga yang valid.');
        }
    }

    public function luas(): float
    {
        $semiperimeter = $this->keliling() / 2;
        return sqrt($semiperimeter * ($semiperimeter - $this->sisiA)
            * ($semiperimeter - $this->sisiB) * ($semiperimeter - $this->sisiC));
    }

    public function keliling(): float
    {
        return $this->sisiA + $this->sisiB + $this->sisiC;
    }
}

class Trapesium extends BangunDatar
{
    public function __construct(
        private readonly float $sisiSejajarAtas,
        private readonly float $sisiSejajarBawah,
        private readonly float $tinggi,
        private readonly float $sisiMiringKiri,
        private readonly float $sisiMiringKanan
    ) {
        parent::__construct('Trapesium');
        $ukuran = [$sisiSejajarAtas, $sisiSejajarBawah, $tinggi, $sisiMiringKiri, $sisiMiringKanan];
        foreach ($ukuran as $nilai) {
            if (!is_finite($nilai) || $nilai <= 0) {
                throw new InvalidArgumentException('Semua ukuran trapesium harus positif dan finite.');
            }
        }
    }

    public function luas(): float
    {
        return ($this->sisiSejajarAtas + $this->sisiSejajarBawah) * $this->tinggi / 2;
    }

    public function keliling(): float
    {
        return $this->sisiSejajarAtas + $this->sisiSejajarBawah
            + $this->sisiMiringKiri + $this->sisiMiringKanan;
    }
}
