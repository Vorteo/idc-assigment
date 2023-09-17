package csvprocessor;

/**
 * The interface for classes that export data.
 * @param <T> The type of data to be exported.
 */
public interface FileExporter<T>
{

    /**
     * Exports the provided data of type T to a file at specified path.
     * @param path The path where the data will be stored.
     * @param data Data that will be exported to a file.
     */
    void export(String path, T data);
}
