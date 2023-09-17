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
    /**
     * Field represents a list of SalesRecord objects that store a sales data.
     * It is used to store and manipulate sales data.
     */
    private List<SalesRecord> salesRecords;

    /**
     * Loads data from a csv file located at the specified path into List.
     * @param path Path to the file where it is stored.
     * @return true if the data is successfully loaded from the csv file, otherwise false.
     */
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

    /**
     * Retrieve list of 'SalesRecord' objects.
     * @return A list of 'SalesRecord' objects.
     */
    public List<SalesRecord> getRecords()
    {
        return salesRecords;
    }

    /**
     * Retrieve list of 'SalesRecord' objects that match with country parameter.
     * @param country The name of country to filter by.
     * @return A list of 'SalesRecord' objects that belongs to the specified country.
     */
    public List<SalesRecord> getRecordsByCountry(String country)
    {
        return salesRecords.stream().filter(record -> record.getCountry().equals(country)).toList();
    }

    /**
     * Retrieve list of 'SalesRecord' objects that match with quarter parameter.
     * @param quarter The quarter to filter by.
     * @return A list of 'SalesRecord' objects that belongs to the specified the quarter.
     */
    public List<SalesRecord> getRecordsByQuarter(String quarter)
    {
        return salesRecords.stream().filter(record -> record.getTimescale().contains(quarter)).toList();
    }

    /**
     * Retrieve list of 'SalesRecords' objects that match the specified parameters (country and quarter).
     * @param country The name of the country to filter by.
     * @param quarter The quarter to filter by.
     * @return A list of 'SalesRecord' objects that belongs to the specified country and quarter.
     */
    public List<SalesRecord> getRecordsByCountryQuarter(String country, String quarter)
    {
        return salesRecords.stream()
                .filter(record -> record.getTimescale().contains(quarter) && record.getCountry().equals(country))
                .toList();
    }
}
