package csvprocessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataProviderTest
{
    private final DataProvider dataProvider = new DataProvider();

    @BeforeEach
    void init()
    {
        dataProvider.loadDataFromCSV(Paths.get("src","test","java","resources","data.csv").toString());
    }

    @Test
    void loadDataFromCSVInvalidPath()
    {
        assertEquals( dataProvider.loadDataFromCSV("D:/path/not/exists/tada"), false);
    }
    @Test
    void loadDataFromCSVIsValidPath()
    {
        assertEquals( dataProvider.loadDataFromCSV(Paths.get("src","test","java","resources","data.csv").toString()),true);
    }

    @Test
    void getRecordsByCountryEmpty()
    {
        assertEquals(dataProvider.getRecordsByCountry("Aaaaaaaaadada").size(), 0);
    }
    @Test
    void getRecordsByCountryNotEmpty()
    {
        assertEquals(dataProvider.getRecordsByCountry("Czech Republic").size(), 14);
    }

    @Test
    void getRecordsByCountryQuarterEmpty()
    {
        assertEquals(dataProvider.getRecordsByCountryQuarter("Aaaaaaaaadada", "Q5").isEmpty(), true);
    }

    @Test
    void getRecordsByCountryQuarterNotEmpty()
    {
        assertEquals(dataProvider.getRecordsByCountryQuarter("Czech Republic", "Q4").size(), 7);
    }
}