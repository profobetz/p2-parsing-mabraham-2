import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVFormat.Builder;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
public class RequestLoader {

    private File SOME_FILE;

    public RequestLoader(File SOME_FILE) {
        this.SOME_FILE = SOME_FILE;
    }
    
    public List<Neighborhood> load() throws IOException {
    List<Neighborhood> nlist = new ArrayList<>();
    // List<Neighborhood> nlist = null

    // Parse a CSV file where values are delimited with commas, 
    // that has a header row, that uses " quotes for literal cells....
    CSVFormat format = Builder.create()
                              .setHeader()
                              .setDelimiter(',')
                              .setQuote('"')
                              .build();
    try {
        // Use that CSV format to read SOME_FILE, a UTF-8 coded .csv
        CSVParser parser = CSVParser.parse(SOME_FILE, StandardCharsets.UTF_8, format);
        
        // Then later on, you can have the parser parse the file and
        // read each CSVRecord's columns based on the header names.

        // If the top of the file has a "closed_date" column, 
        // this will get that column for this "next_row"
        // Each row is a ServiceRequest. Each column is an attribute.
        boolean closed_on_time = false;
        boolean isClosed = false;
        LocalDate today = LocalDate.now();
        
        for (CSVRecord next_row: parser.getRecords()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String this_rows_open_date = next_row.get("open_dt");
            LocalDate this_rows_open_LocalDate = LocalDate.parse(this_rows_open_date, formatter);

            String this_rows_closed_date = next_row.get("closed_dt");
            LocalDate this_rows_closed_LocalDate;
            if ("".equals(this_rows_closed_date)) {
                this_rows_closed_LocalDate = today;
            } else {
                this_rows_closed_LocalDate = LocalDate.parse(this_rows_closed_date, formatter);
            }

            String onTime = next_row.get("on_time");
            if ( onTime.equals("OVERDUE") ) {
                closed_on_time = false;
            } else if (onTime.equals("ONTIME")) {
                closed_on_time = true;
            }

            String case_status = next_row.get("case_status");
            if (case_status.equals("Closed")) {
                isClosed = true;
            } else if (case_status.equals("Open")) {
                isClosed = false;
            }

            String reason_for_request = next_row.get("reason");
            String neighborhood_name = next_row.get("neighborhood");
            
            Neighborhood neighborhood = null;
            for (Neighborhood next_neighbor : nlist) {
                if (next_neighbor.getName().equals(neighborhood_name)){
                    neighborhood = next_neighbor;
                }
            }
            if (neighborhood == null) {
                neighborhood = new Neighborhood(neighborhood_name);
                nlist.add(neighborhood);
            }
            neighborhood.addRequest(new ServiceRequest(this_rows_open_LocalDate, this_rows_closed_LocalDate, isClosed, closed_on_time, reason_for_request, neighborhood));
        }
    // 1. Neighborhood. 
    // 2. ServiceRequest.
    // 3. Neighborhood.addRequest() to the Neighborhood. Not all neighborhoods are new.
    // 4. Add new neighborhood to neighborhood list after the loop.
    // 5. Check to see if object already exists before adding it to the list.
    
    // If you prefer, you can instead use a Scanner and basic String splitting
    // to read out the individual values, but in either case you will be responsible
    // for converting each field to an appropriate datatype (e.g. LocalDate for dates,
    // booleans for binary values, etc.) Also make sure you handle any exceptions that
    // may occur as a result of the file reading and parsing process using try-catch blocks.

    } catch (IOError ex) {
        System.err.println("An error occurred.");
    } catch (FileNotFoundException ex) {
        System.err.println("File not found.");
    } catch (DateTimeParseException p) {
        System.err.println(p.getMessage());
    } 
        return nlist; 
    }
}