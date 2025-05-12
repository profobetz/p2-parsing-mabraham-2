import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {

        RequestLoader loader = new RequestLoader(new File("resources/311_requests.csv"));
        IncomeLoader loader2 = new IncomeLoader(new File("resources/PerCapitaIncome.csv"));

        List<Neighborhood> neighborhoods = loader.load();
        loader2.load(neighborhoods);

        List<Neighborhood> neighborhoods_per_capita_list = new ArrayList<>();

        for (Neighborhood neighborhood : neighborhoods) {
            if (neighborhood.getPerCapitaIncome() > 0.00) {
                neighborhoods_per_capita_list.add(neighborhood);
            }
        }
        NeighborhoodVisitor visitor = new NeighborhoodVisitor();
        List<Results> results_list = visitor.gather_results(neighborhoods_per_capita_list);

        // for (Results result : results_list) {
        //     if (result.getPerCapitaIncome() == 0.00) {
        //         results_list.remove(result);
        //     }
        // }

        double correlation = visitor.correlate(neighborhoods_per_capita_list);
        OutputResults output = new OutputResults(new File("resources/Data.csv"));
        output.unload(results_list, correlation);
    
        System.out.println("Neighborhoods: ");
        for ( Neighborhood n : neighborhoods_per_capita_list ) {
            String summary = String.format("%s - %d open / %d total, %d overdue (%.2f%%), %.2f avg. days to closure, $%.2f ",
                    n.getName(),
                    n.getOpenCases().size(),
                    n.getTotalRequestCount(),
                    n.getOverdueCases().size(),
                    n.getOverdueRate(),
                    n.getAverageDaysOpen(),
                    n.getPerCapitaIncome()
            );
            System.out.println(summary);
        }
    } 
}