package id.ac.univpancasila.perpustakaan.exception;

public final class BatasPinjamTerlampauiException extends RuntimeException {
    public BatasPinjamTerlampauiException(String idAnggota, int batas) {
        super("Anggota " + idAnggota + " telah mencapai batas " + batas + " buku");
    }
}
