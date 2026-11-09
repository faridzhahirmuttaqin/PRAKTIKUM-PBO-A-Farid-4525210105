package id.ac.univpancasila.perpustakaan.exception;

public final class AnggotaTidakDitemukanException extends PerpustakaanException {
    private final String idAnggota;

    public AnggotaTidakDitemukanException(String idAnggota) {
        super("Anggota dengan ID " + idAnggota + " tidak terdaftar");
        this.idAnggota = idAnggota;
    }

    public String getIdAnggota() {
        return idAnggota;
    }
}
