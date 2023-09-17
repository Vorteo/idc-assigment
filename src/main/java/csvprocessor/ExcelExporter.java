package csvprocessor;

/**
 * The 'ExcelExporter' class is responsible for exporting sales data to Excel file.
 * It implements the FileExporter interface with focus on Excel format.
 * @see FileExporter
 */
public class ExcelExporter implements FileExporter<SalesData>
{
    /**
     * Exports sales data to an Excel file.
     * Java Apache POI library
     * Check if there is a path to the file.
     * Creating a header row with column names.
     * In the loop fill with data (e.g. Create a new row and fill the columns with data from object).
     * Save file, catch exceptions.
     *
     * @param path The path to save the file to disk.
     * @param data The sales data to be exported to the Excel file.
     */
    @Override
    public void export(String path, SalesData data) {}

}
