package csvprocessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

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
        assertFalse(dataProvider.loadDataFromCSV("D:/path/not/exists/tada"));
    }
    @Test
    void loadDataFromCSVIsValidPath()
    {
        assertTrue(dataProvider.loadDataFromCSV(Paths.get("src", "test", "java", "resources", "data.csv").toString()));
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
        assertTrue(dataProvider.getRecordsByCountryQuarter("Aaaaaaaaadada", "Q5").isEmpty());
    }

    @Test
    void getRecordsByCountryQuarterNotEmpty()
    {
        assertEquals(dataProvider.getRecordsByCountryQuarter("Czech Republic", "Q4").size(), 7);
    }
}