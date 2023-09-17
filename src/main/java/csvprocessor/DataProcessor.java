package csvprocessor;

import java.util.*;

/**
 *
 */
public class DataProcessor
{
    private List<SalesRecord> records;
    private double totalUnits;
    private FileExporter<SalesData> fileExporter;

    /**
     * Initialize a DataProcessor with list of sales records and a FileExporter.
     * Constructor create a DataProcessor instance with the provided list of sales records
     * and FileExporter for exporting.
     * Also calculate total number of units.
     * @param records A list of sales records to be processed and exported.
     * @param fileExporter The fileExporter instance to be used for exporting SalesData.
     */
    public DataProcessor(List<SalesRecord> records, FileExporter<SalesData> fileExporter)
    {
        this.records = new ArrayList<>(records);
        this.fileExporter = fileExporter;
        this.totalUnits = calculateTotalUnits();
    }

    /**
     * Calculates the total number of units.
     * @return Total number of units.
     */
    private double calculateTotalUnits()
    {
        return records.stream().mapToDouble(SalesRecord::getUnits).sum();
    }

    /**
     * Prints to the console a list of vendors with their units and share.
     */
    public void printTable()
    {
        System.out.println("Vendor\t\t Units\t Share\t");
        for (SalesRecord s : records)
        {
            System.out.println(s.getVendor() + " " + s.getUnits() + "\t" + (s.getUnits() / totalUnits) * 100 + "%");

        }
        System.out.println("Total " + totalUnits + " 100%");
    }

    /**
     * Prints the vendor's statistics to the console
     * Name, Units and his Share
     * @param vendor The name of vendor.
     */
    public void printVendorStats(String vendor)
    {
        Optional<SalesRecord> first = records.stream().filter(x -> x.getVendor().equals(vendor)).findFirst();

        if(first.isPresent())
        {
            SalesRecord sale = first.get();
            System.out.println("Vendor: "+  sale.getVendor() +"  Units: " + sale.getUnits() + "  Share: " + (sale.getUnits() / totalUnits) * 100);
        }
    }

    /**
     * Gets the vendor row number based on his name.
     * @param vendor The name of vendor to search for in sales records.
     */
    public void getVendorRowNumber(String vendor)
    {
       for(int i = 0; i < records.size(); i++)
       {
           SalesRecord record = records.get(i);
           if(record.getVendor().equals(vendor))
           {
               System.out.println("Row number: " + i);
               return;
           }
       }
        System.out.println("Vendor not found");
    }

    /**
     * Sort the list of sales records by vendor in ascending or descending order.
     * @param asc If 'true', the list will be sorted in ascending order;
     *            If 'false', the list will be sorted in descending order
     */
    public void sortByName(boolean asc)
    {
        if(asc)
        {
            records.sort(Comparator.comparing(SalesRecord::getVendor));
        }
        else
        {
            records.sort(Comparator.comparing(SalesRecord::getVendor).reversed());
        }
    }

    /**
     * Sort the list of sales records by units in ascending or descending order.
     * @param asc If 'true', the list will be sorted in ascending order;
     *            If 'false', the list will be sorted in descending order
     */
    public void sortByUnits(boolean asc)
    {
        if(asc)
        {
            records.sort(Comparator.comparing(SalesRecord::getUnits));
        }
        else {
            records.sort(Comparator.comparing(SalesRecord::getUnits).reversed());
        }
    }

    /**
     * Exports the sales data to a file using the assigned FileExporter.
     * @param path The file path where the sales data should be exported.
     */
    public void export(String path)
    {
        fileExporter.export(path, new SalesData(records, totalUnits));
    }

    /**
     * Sets the FileExporter to be used for exporting SalesData.
     * (e.g. HtmlExporter, CsvExporter, ExcelExporter)
     * @param fileExporter The FileExporter instance to be used for exporting SalesData.
     * @see FileExporter
     */
    public void setFileExporter(FileExporter<SalesData> fileExporter)
    {
        this.fileExporter = fileExporter;
    }
}
