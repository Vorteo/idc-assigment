package csvprocessor.exporter;

public interface FileExporter<T>
{
    void export(String path, T data);
}
