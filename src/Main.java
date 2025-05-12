import java.io.File;
import java.util.List;
public class Main {
    public static void main(String[] args) {

        RequestLoader loader = new RequestLoader(new File("resources/311_requests.csv"));
        IncomeLoader loader2 = new IncomeLoader(new File("resources/PerCapitaIncome.csv"));
        List<Neighborhood> neighborhoods = loader.load();
        loader2.load(neighborhoods);
        NeighborhoodVisitor visitor = new NeighborhoodVisitor();
        List<Results> results_list = visitor.gather_results(neighborhoods);
        double correlation = visitor.correlate(neighborhoods);
        OutputResults output = new OutputResults(new File("resources/Data.csv"));
        output.unload(results_list, correlation);
    
        System.out.println("Neighborhoods: ");
        for ( Neighborhood n : neighborhoods ) {
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