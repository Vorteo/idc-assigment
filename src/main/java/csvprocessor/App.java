package csvprocessor;

import java.util.List;
import java.util.Scanner;

public class App
{
    private static boolean dataLoaded = false;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        Loader loader = new Loader();
        DataProcessor dataProcessor;
        List<SalesRecord> tableData;

        System.out.println("Table Component");

        while(true)
        {
            System.out.println("*******************");
            System.out.println("Select one of the following options");
            if(!dataLoaded)
            {
                System.out.println("1 - Read data from CSV file");
            }
            else
            {
                System.out.println("2 - View data");
            }
            System.out.println("3 - Exit");
            System.out.println("*******************");

            int  selectedOption = scanner.nextInt();
            scanner.nextLine();

            switch (selectedOption)
            {
                case 1 -> {
                    System.out.println("Enter the path to the file you want to read: ");
                    String filepath = scanner.nextLine();
                    dataLoaded = loader.loadDataFromCSV(filepath);
                }
                case 2 -> {
                    if (dataLoaded)
                    {

                        System.out.println("Enter  the country name for which you want to view the data: ");
                        String country = scanner.nextLine();

                        System.out.println("Enter the time period Q1-Q4: ");
                        String quarter = scanner.nextLine();

                        tableData = loader.getRecordsByCountryQuarter(country, quarter);
                        dataProcessor = new DataProcessor(tableData, new HtmlExporter());

                        while(true)
                        {
                            System.out.println("*******************");
                            System.out.println("Select one of the following options: ");
                            System.out.println("1 - Print table into console");
                            System.out.println("2 - Export into file");
                            System.out.println("3 - Sort by Vendor");
                            System.out.println("4 - Sort by Units");
                            System.out.println("5 - Print Vendor stats");
                            System.out.println("6 - Find row by vendor");
                            System.out.println("0 - Back");
                            System.out.println("*******************");

                            int viewOption = scanner.nextInt();
                            scanner.nextLine();
                            switch (viewOption) {
                                case 1 -> dataProcessor.printTable();
                                case 2 -> {
                                    System.out.println("*******************");
                                    System.out.println("Select export format: ");
                                    System.out.println("1 - HTML");
                                    System.out.println("2 - CSV");
                                    System.out.println("3 - Excel");
                                    System.out.println("*******************");

                                    int exportOption = scanner.nextInt();
                                    scanner.nextLine();

                                    System.out.println("Enter the path where you want to export the data");
                                    String exportPath = scanner.nextLine();
                                    switch (exportOption) {
                                        case 1 -> dataProcessor.setFileExporter(new HtmlExporter());
                                        case 2 -> dataProcessor.setFileExporter(new CsvExporter());
                                        case 3 -> dataProcessor.setFileExporter(new ExcelExporter());
                                    }

                                    dataProcessor.export(exportPath);

                                }
                                case 3 -> dataProcessor.sortByName(false);
                                case 4 -> dataProcessor.sortByUnits(false);
                                case 5 -> {
                                    System.out.println("Enter the vendor name: ");
                                    String vendorName = scanner.nextLine();
                                    dataProcessor.printVendorStats(vendorName);
                                }
                                case 6 -> {
                                    System.out.println("Enter the vendor name: ");
                                    String vendorName2 = scanner.nextLine();
                                    dataProcessor.getVendorRowNumber(vendorName2);
                                }

                            }
                            if(viewOption == 0)
                            {
                                break;
                            }
                        }
                    } else
                    {
                        System.out.println("Data has not yet been loaded");
                    }
                }
                case 3 -> {
                    System.out.println("Exit the application");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Wrong option");
            }
        }
    }
}
