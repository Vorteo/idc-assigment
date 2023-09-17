package csvprocessor;

import java.util.List;

public class SalesData
{
    private List<SalesRecord> records;
    private double totalUnits;

    public SalesData(List<SalesRecord> records, double totalUnits)
    {
        this.records = records;
        this.totalUnits = totalUnits;
    }

    public List<SalesRecord> getRecords()
    {
        return records;
    }

    public double getTotalUnits()
    {
        return totalUnits;
    }
}
