import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filepath = "resources/311_requests.csv";

        RequestLoader loader = new RequestLoader(new File(filepath));

        List<Neighborhood> neighborhoods = loader.load();

        for ( Neighborhood n : neighborhoods ) {
            String summary = String.format("%s - %d open / %d total, %d overdue (%.2f%%), %.2f avg. days to closure", 
                    n.getName(),
                    n.getOpenCases().size(),
                    n.getTotalRequestCount(),
                    n.getOverdueCases().size(),
                    n.getOverdueRate(),
                    n.getAverageDaysOpen()
            );

            System.out.println(summary);
        }

    }
}