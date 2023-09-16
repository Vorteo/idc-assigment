package csvprocessor;

public interface FileExporter<T>
{
    void export(String path, T data);
}
