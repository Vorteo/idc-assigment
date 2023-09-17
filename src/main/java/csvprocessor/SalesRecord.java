package csvprocessor;

import com.opencsv.bean.CsvBindByName;

/**
 * The 'SalesRecord' class represents a sales record containing information about
 * country, timescale, vendor and units sold.
 */
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

    /**
     * Default constructor.
     */
    public SalesRecord()
    {
    }

    /**
     * Get the country.
     * @return The country.
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * Get the timescale.
     * @return The timescale (e.g. "2023 Q4")
     */
    public String getTimescale()
    {
        return timescale;
    }

    /**
     * Get the vendor name.
     * @return The vendor.
     */
    public String getVendor()
    {
        return vendor;
    }

    /**
     * Get the number of units sold.
     * @return The number of units.
     */
    public double getUnits()
    {
        return units;
    }

    /**
     * Get the year from the timescale.
     * @return The year obtained from timescale.
     */
    public int getYear()
    {
        return Integer.parseInt(timescale.split(" ")[0]);
    }

    /**
     * Get the quarter from the timescale.
     * @return The quarter part of the timescale (example: 2 for Q2)
     */
    public int getQuarter()
    {
        return Integer.parseInt(timescale.split(" ")[1]);
    }
}
