package csvprocessor;

import csvprocessor.exporter.FileExporter;

import java.util.*;

public class DataProcessor
{
    private final List<SalesRecord> records;
    private final double totalUnits;
    private FileExporter<SalesData> fileExporter;

    public DataProcessor(List<SalesRecord> records)
    {
        this.records = new ArrayList<>(records);
        this.totalUnits = calculateTotalUnits();
    }

    private double calculateTotalUnits()
    {
        return records.stream().mapToDouble(SalesRecord::getUnits).sum();
    }

    public void printTable()
    {
        System.out.printf("%-20s%-20s%-10s%n", "Vendor", "Units", "Share");
        for (SalesRecord s : records)
        {
            System.out.printf("%-20s%-20.6f%-5.2f%%%n",s.getVendor(), s.getUnits(), (s.getUnits() / totalUnits) * 100);

        }
        System.out.printf("%-20s%-20.6f%-10s%n","Total", totalUnits, "100%");
        System.out.println();
    }

    public void printVendorStats(String vendor)
    {
        Optional<SalesRecord> first = records.stream().filter(x -> x.getVendor().equals(vendor)).findFirst();

        if(first.isPresent())
        {
            SalesRecord sale = first.get();
            System.out.println("Vendor: "+  sale.getVendor() +"  Units: " + sale.getUnits() + "  Share: " + (sale.getUnits() / totalUnits) * 100);
        }
    }

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

    public void export(String path)
    {
        fileExporter.export(path, new SalesData(records, totalUnits));
    }

    public void setFileExporter(FileExporter<SalesData> fileExporter)
    {
        this.fileExporter = fileExporter;
    }
}
