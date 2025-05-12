import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class OutputResults {
    private final File outfile;

    public OutputResults (File outfile) {
        this.outfile = outfile;
    }

    public void unload(List<Results> results, double correlation) {
        Results CSV_result;
        String [] header = {"Neighborhood, Overdue Rate, Per Capita Income, Correlation"};
        try (PrintWriter writer = new PrintWriter(outfile)) {
            writer.println(String.join(",", header));
            for (Results result : results) {
                if (!results.isEmpty()) {
                    CSV_result = result;
                    String [] data = {CSV_result.getNeighborhoodName(),
                        Double.toString(CSV_result.getOverdueRate()).formatted(" %.2f%% "),
                        Double.toString(CSV_result.getPerCapitaIncome()).formatted(" $%.2f "),
                        Double.toString(correlation).formatted(".2f")};
                    writer.println(String.join(",", data));
                }
            }
        System.out.println("CSV file created successfully.");
        } catch (FileNotFoundException e) {
            System.err.println("Error creating CSV file: " + e.getMessage());
        }

        // try (BufferedReader breader = new BufferedReader(new FileReader("resources/Data.csv"));
        //     BufferedWriter bwriter = new BufferedWriter(new FileWriter("resources/Data2.csv"))) {
        //     String line;
        //     int correlate_col = 3;
        //     List<String> lines = new ArrayList<>();
        //     String correlateString = Double.toString(correlation);
        //     while ((line = breader.readLine()) != null) {
        //         String [] values = line.split(",");
        //         if (values.length > correlate_col) {
        //             values[correlate_col] = correlateString;
        //         }
        //         lines.add(String.join(",", values));
        //         for (String line1 : lines) {
        //             bwriter.write(line1);
        //             bwriter.newLine();
        //         }
        //     }
        // } catch (IOException e) {
        //     e.getMessage();
        // }
    }
}