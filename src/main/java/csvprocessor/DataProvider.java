package csvprocessor;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;


/**
 * The 'Loader' class represents a simple database into which data is loaded.
 */
public class DataProvider
{
    private List<SalesRecord> salesRecords;

    public boolean loadDataFromCSV(String path)
    {
        try
        {
            salesRecords = new CsvToBeanBuilder<SalesRecord>(new FileReader(path))
                    .withType(SalesRecord.class).build().parse();
            return true;
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File not found! Please provide a valid file path.");
            return false;
        }
    }

    public List<SalesRecord> getRecords()
    {
        return salesRecords;
    }

    public List<SalesRecord> getRecordsByCountry(String country)
    {
        return salesRecords.stream().filter(record -> record.getCountry().equals(country)).toList();
    }

    public List<SalesRecord> getRecordsByQuarter(String quarter)
    {
        return salesRecords.stream().filter(record -> record.getTimescale().contains(quarter)).toList();
    }

    public List<SalesRecord> getRecordsByCountryQuarter(String country, String quarter)
    {
        return salesRecords.stream()
                .filter(record -> record.getTimescale().contains(quarter) && record.getCountry().equals(country))
                .toList();
    }
}
