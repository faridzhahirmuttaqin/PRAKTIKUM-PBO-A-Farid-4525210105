package id.ac.univpancasila.perpustakaan.exception;

public final class BukuTidakDitemukanException extends RuntimeException {
    public BukuTidakDitemukanException(String isbn) {
        super("Buku dengan ISBN " + isbn + " tidak ditemukan");
    }
}
