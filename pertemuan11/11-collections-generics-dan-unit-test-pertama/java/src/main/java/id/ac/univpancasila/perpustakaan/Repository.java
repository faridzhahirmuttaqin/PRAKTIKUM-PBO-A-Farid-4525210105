package id.ac.univpancasila.perpustakaan;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public final class Repository<T> {
    private final Map<String, T> data = new LinkedHashMap<>();
    private final Function<T, String> pengambilKunci;

    public Repository(Function<T, String> pengambilKunci) {
        this.pengambilKunci = Objects.requireNonNull(pengambilKunci, "Pengambil kunci wajib diisi");
    }

    public void simpan(T item) {
        Objects.requireNonNull(item, "Item tidak boleh null");
        String id = Objects.requireNonNull(pengambilKunci.apply(item), "ID item tidak boleh null");
        if (id.isBlank()) {
            throw new IllegalArgumentException("ID item tidak boleh kosong");
        }
        data.put(id, item);
    }

    public Optional<T> cariById(String id) {
        return Optional.ofNullable(data.get(Objects.requireNonNull(id, "ID tidak boleh null")));
    }

    public List<T> cariSemua() {
        return List.copyOf(data.values());
    }

    public boolean hapus(String id) {
        return data.remove(Objects.requireNonNull(id, "ID tidak boleh null")) != null;
    }

    public int jumlah() {
        return data.size();
    }
}
