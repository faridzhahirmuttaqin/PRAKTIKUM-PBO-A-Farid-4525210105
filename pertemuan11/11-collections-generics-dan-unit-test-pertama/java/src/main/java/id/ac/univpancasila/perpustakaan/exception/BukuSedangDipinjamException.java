package id.ac.univpancasila.perpustakaan.exception;

public final class BukuSedangDipinjamException extends RuntimeException {
    public BukuSedangDipinjamException(String isbn, String judul) {
        super("Buku \"" + judul + "\" (ISBN " + isbn + ") sedang dipinjam atau tidak tersedia");
    }
}
