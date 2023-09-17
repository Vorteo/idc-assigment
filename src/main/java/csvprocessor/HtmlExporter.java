package csvprocessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The 'HtmlExporter' class is responsible for exporting sales data to Excel file.
 * It implements the FileExporter interface with focus on HTML format.
 * @see FileExporter
 */
public class HtmlExporter implements FileExporter<SalesData>
{
    /**
     * HTML template string for generating tabular reports.
     * String includes HTML and CSS for creating table with headers and tag for dynamically insert the reports' data.
     */
    private final String htmlTemplateString =
            "<html>" +
                "<head>" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                    "<title>Table</title>" +
                    "<style>table { width: 50%; border-collapse: collapse;} th, td {border: 1px solid black; padding: 8px; text-align: center;}</style>" +
                "</head>" +
            "<body>" +
                    "<table border=\"1\">" +
                        "<tr>" +
                            "<th>Vendor</th>" +
                            "<th>Units</th>" +
                            "<th>Share</th>" +
                        "</tr>" +
                        "$body" +
                    "</table>" +
            "</body>" +
            "</html>";
    private final String extension = ".html";

    /**
     * Exports sales data to an HTML file with table format.
     * @param path The path to save the file to disk.
     * @param data The 'SalesData' object containing sales records to be exported to the HTML file.
     * @see SalesData
     */
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

        StringBuilder tableString = new StringBuilder();
        for (SalesRecord record: data.getRecords())
        {
            tableString
                    .append("<tr><td>")
                    .append(record.getVendor())
                    .append("</td><td>")
                    .append(record.getUnits())
                    .append("</td><td>")
                    .append((record.getUnits() / data.getTotalUnits()) * 100)
                    .append("</td></tr>");
        }

        tableString
                .append("<tr><td>Total</td><td>")
                .append(data.getTotalUnits())
                .append("</td><td>100%<t/d></tr>");

        String htmlResult = htmlTemplateString;
        htmlResult = htmlResult.replace("$body", tableString.toString());

        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(htmlResult);
            bw.close();
            System.out.println("Export to file completed");
        }
        catch (IOException e)
        {
            System.err.println("Error exporting data to HTML file.");
        }
    }
}
