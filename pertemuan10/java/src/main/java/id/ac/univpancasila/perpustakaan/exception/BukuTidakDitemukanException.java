package id.ac.univpancasila.perpustakaan.exception;

public final class BukuTidakDitemukanException extends PerpustakaanException {
    private final String isbn;

    public BukuTidakDitemukanException(String isbn) {
        super("Buku dengan ISBN " + isbn + " tidak ditemukan");
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }
}
