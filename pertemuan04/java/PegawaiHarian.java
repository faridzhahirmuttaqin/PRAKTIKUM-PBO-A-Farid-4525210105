public class PegawaiHarian extends Pegawai {

    private final int hariKerja;

    public PegawaiHarian(String nip, String nama, double gajiPerHari, int hariKerja) {
        super(nip, nama, gajiPerHari);
        if (hariKerja < 0) {
            throw new IllegalArgumentException("Hari kerja tidak boleh negatif");
        }
        this.hariKerja = hariKerja;
    }

    @Override
    public double hitungGaji() {
        return super.hitungGaji() * hariKerja;
    }

    @Override
    public String jenis() { return "HARIAN"; }
}
