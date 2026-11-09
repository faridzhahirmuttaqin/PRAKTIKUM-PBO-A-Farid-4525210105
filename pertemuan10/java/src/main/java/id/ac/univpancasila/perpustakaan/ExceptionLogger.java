package id.ac.univpancasila.perpustakaan;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

public final class ExceptionLogger {
    private ExceptionLogger() {}

    public static void catat(Exception exception, Path tujuan) throws IOException {
        String data = switch (exception) {
            case BukuTidakDitemukanException e -> "isbn=" + e.getIsbn();
            case AnggotaTidakDitemukanException e -> "idAnggota=" + e.getIdAnggota();
            case BukuSedangDipinjamException e -> "isbn=" + e.getIsbn() + ", judul=" + e.getJudul();
            case BatasPinjamTerlampauiException e -> "idAnggota=" + e.getIdAnggota() + ", batas=" + e.getBatas();
            default -> "pesan=" + exception.getMessage();
        };
        Path parent = tujuan.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Files.writeString(
                tujuan,
                Instant.now() + " | " + exception.getClass().getSimpleName() + " | " + data
                        + System.lineSeparator(),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }
}
