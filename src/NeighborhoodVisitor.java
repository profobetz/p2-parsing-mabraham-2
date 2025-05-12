import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class NeighborhoodVisitor {

    private final List<Results> results_list;

    public NeighborhoodVisitor() {
        this.results_list = new ArrayList<>();
    }

    public int getResultsCount() {
        return this.results_list.size();
    }

    public List<Results> gather_results (List<Neighborhood> nlist) {
        double [] overdue_rate_array = new double [nlist.size()];
        double [] per_capita_income_array = new double [nlist.size()];
        String [] neighborhood_name_array = new String[nlist.size()];
        //scatterplot and trend line

        for (int i = 0; i < nlist.size(); i++) {
            neighborhood_name_array[i] = nlist.get(i).getName();
            overdue_rate_array[i] = nlist.get(i).getOverdueRate();
            per_capita_income_array[i] = nlist.get(i).getPerCapitaIncome();
            Results result = new Results(neighborhood_name_array[i], overdue_rate_array[i], per_capita_income_array[i]);
            results_list.add(result);

            // correlate = new PearsonsCorrelation().correlation(per_capita_income_array, overdue_rate_array);
            // Results result = new Results(overdue_rate_array[index++], per_capita_income_array[index++], correlate);
            // results_list.add(result);
        }
        return results_list;
    }
    
    public double correlate(List<Neighborhood> nlist) {
        double [] overdue_rate_array = new double [nlist.size()];
        double [] per_capita_income_array = new double [nlist.size()];

        for (int i = 0; i < nlist.size(); i++) {
            overdue_rate_array[i] = nlist.get(i).getOverdueRate();
            per_capita_income_array[i] = nlist.get(i).getPerCapitaIncome();
        }
        double correlation_of_total_set =  new PearsonsCorrelation().correlation(per_capita_income_array, overdue_rate_array);
        return correlation_of_total_set;
    }
}