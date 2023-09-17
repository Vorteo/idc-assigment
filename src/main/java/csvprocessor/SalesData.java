package csvprocessor;

import java.util.List;

/**
 * Represents a collection of sales records with total unit count.
 */
public class SalesData
{
    private List<SalesRecord> records;
    private double totalUnits;

    /**
     * Creates a 'SalesData' instance with the provided sales records and total unit count.
     * @param records A list of sales records.
     * @param totalUnits The total number of units across all records.
     */
    public SalesData(List<SalesRecord> records, double totalUnits)
    {
        this.records = records;
        this.totalUnits = totalUnits;
    }

    /**
     * Retrieves the list of sales records.
     * @return A list of all sales records.
     */
    public List<SalesRecord> getRecords()
    {
        return records;
    }

    /**
     * Retrieves the total number of units across al sales records.
     * @return The total unit count.
     */
    public double getTotalUnits()
    {
        return totalUnits;
    }
}
