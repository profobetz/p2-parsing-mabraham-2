import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class IncomeLoader {

    private final File income_file;

    public IncomeLoader(File income_file) {
        this.income_file = income_file;
    }

    public void load(List<Neighborhood> nlist) {

        try {
            CSVParser parser = CSVParser.parse(income_file, StandardCharsets.UTF_8, CSVFormat.DEFAULT.withHeader());

            Neighborhood neighborhood;

            for (CSVRecord next_row: parser.getRecords()) {

                String neighborhood_name = next_row.get("Neighborhood").trim();

                String per_capita_income_string = next_row.get("Per Capita Income").trim();

                Double per_capita_income = Double.valueOf(per_capita_income_string);

                // Need both list's neighborhood orders to match.
                for (Neighborhood next_neighbor : nlist) {
                    if (next_neighbor.getName().equals(neighborhood_name) ) {
                        neighborhood = next_neighbor;
                        if (per_capita_income > 0.00) {
                            neighborhood.setPerCapitaIncome(per_capita_income); 
                        } else {
                            per_capita_income = null;
                        }
                    }
                }
            }
        } catch (FileNotFoundException f) {
            System.err.println("File not found: " + f.getMessage());
        } catch (DateTimeParseException p) {
            System.err.println(p.getMessage());
        } catch (IOException e ) {
            System.err.println("Unable to open file: " + e.getMessage());
        } catch (IOError ex) {
            System.err.println("An error occurred: " +  ex.getMessage());
        }
    }
}