package csvprocessor;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;


class LoaderTest
{
    private Loader loader = new Loader();

    @Test
    void loadDataFromCSVInvalidPath()
    {
        assertThat( loader.loadDataFromCSV("D:/path/not/exists/tada")).isFalse();
    }
    @Test
    void loadDataFromCSVIsValidPath()
    {
        assertThat( loader.loadDataFromCSV(Paths.get("src","test","java","resources","data.csv").toString())).isTrue();
    }

    @Test
    void getRecordsByCountryEmpty()
    {
        loader.loadDataFromCSV(Paths.get("src","test","java","resources","data.csv").toString());
        assertThat(loader.getRecordsByCountry("Aaaaaaaaadada")).isEmpty();
    }
    @Test
    void getRecordsByCountryNotEmpty()
    {
        loader.loadDataFromCSV(Paths.get("src","test","java","resources","data.csv").toString());
        assertThat(loader.getRecordsByCountry("Czech Republic")).hasSize(14);
    }

    @Test
    void getRecordsByCountryQuarterEmpty()
    {
        loader.loadDataFromCSV(Paths.get("src","test","java","resources","data.csv").toString());
        assertThat(loader.getRecordsByCountryQuarter("Aaaaaaaaadada", "Q5")).isEmpty();
    }

    @Test
    void getRecordsByCountryQuarterNotEmpty()
    {
        loader.loadDataFromCSV(Paths.get("src","test","java","resources","data.csv").toString());
        assertThat(loader.getRecordsByCountryQuarter("Czech Republic", "Q4")).hasSize(7);
    }
}