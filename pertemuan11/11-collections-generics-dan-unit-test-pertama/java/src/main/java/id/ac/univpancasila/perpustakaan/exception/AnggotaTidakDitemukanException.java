package id.ac.univpancasila.perpustakaan.exception;

public final class AnggotaTidakDitemukanException extends RuntimeException {
    public AnggotaTidakDitemukanException(String idAnggota) {
        super("Anggota dengan ID " + idAnggota + " tidak terdaftar");
    }
}
