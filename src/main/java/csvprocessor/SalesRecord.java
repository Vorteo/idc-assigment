package csvprocessor;

import com.opencsv.bean.CsvBindByName;

public class SalesRecord
{
    @CsvBindByName(column = "Country")
    private String country;
    @CsvBindByName(column = "Timescale")
    private String timescale;
    @CsvBindByName(column = "Vendor")
    private String vendor;
    @CsvBindByName(column = "Units")
    private double units;

    public SalesRecord()
    {
    }

    public String getCountry()
    {
        return country;
    }

    public String getTimescale()
    {
        return timescale;
    }

    public String getVendor()
    {
        return vendor;
    }

    public double getUnits()
    {
        return units;
    }

    public int getYear()
    {
        return Integer.parseInt(timescale.split(" ")[0]);
    }

    public int getQuarter()
    {
        return Integer.parseInt(timescale.split(" ")[1]);
    }
}
