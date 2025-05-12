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
        String correlate_string = Double.toString(correlation);
        try (PrintWriter writer = new PrintWriter(outfile)) {
            writer.println(String.join(",", header));

            for (int i = 0; i < 1; i++) {
                if (!results.isEmpty()) {
                    CSV_result = results.get(i);
                    String [] data = {CSV_result.getNeighborhoodName(),
                                    Double.toString(CSV_result.getOverdueRate()).formatted(" %.2f%% "),
                                    Double.toString(CSV_result.getPerCapitaIncome()).formatted(" $%.2f ")};
                    String line = String.join(",", data) + "," + correlate_string;
                    writer.println(line);
                }
            }

            for (int i = 1; i < results.size(); i++) {
                CSV_result = results.get(i);
                String [] data = {CSV_result.getNeighborhoodName(),
                                Double.toString(CSV_result.getOverdueRate()).formatted(" %.2f%% "),
                                Double.toString(CSV_result.getPerCapitaIncome()).formatted(" $%.2f ")};
                String line = String.join(",", data);
                writer.println(line);
            }
            System.out.println("CSV file created successfully.");

        } catch (FileNotFoundException e) {
            System.err.println("Error creating CSV file: " + e.getMessage());
        }
    }
}