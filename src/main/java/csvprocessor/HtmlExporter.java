package csvprocessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlExporter implements FileExporter<SalesData>
{
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
            e.printStackTrace();
        }
    }
}
