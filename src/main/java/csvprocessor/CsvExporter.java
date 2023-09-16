package csvprocessor;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CsvExporter implements FileExporter<SalesData>
{
    private final String extension = ".csv";
    @Override
    public void export(String path, SalesData data)
    {
        File file = new File(path);
        if(file.getParent() == null)
        {
            file = new File(System.getProperty("user.dir"), path);
        }

        File parentDir = file.getParentFile();
        if(!parentDir.exists())
        {
            System.err.println("Invalid path");
            return;
        }

        if(!file.getName().endsWith(extension))
        {
            System.err.println("Invalid extension");
            return;
        }

        try {

            Writer writer = new FileWriter(path);
            CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String[] columns = {"Vendor", "Units", "Share"};
            csvWriter.writeNext(columns);

            for (SalesRecord r : data.getRecords()) {
                String[] valuesToWrite = {r.getVendor(), String.valueOf(r.getUnits()), String.valueOf((r.getUnits() / data.getTotalUnits()) * 100) };
                csvWriter.writeNext(valuesToWrite);
            }

            csvWriter.writeNext(new String[] {"Total", String.valueOf(data.getTotalUnits()), "100%"});

            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
