// Finally, you will do your actual data computation. The Visitor pattern we discussed in the first half
// of the course may be a great way to 'visit' each neighborhood and compute the statistics for each in turn,
// though you are free to also call getters and setters in a loop. No matter what technique you decide on,
// you should end up with a method that takes your List of Neighborhood objects, and produces a List of the 
// Result objects you decided on when picking your question.

// The output of your question should be in the form of a List of objects (not a single number conclusion for the entire dataset.) 
// You should create a unique class to represent the attributes of each 'row' in your final answer. These rows may be for 
// each neighborhood, but are more likely to be for whatever demographic you are analyzing (For instance, you might have one 
// object for each income bracket, with simple String and double attributes representing things like the upper and lower bounds 
// of that bracket and the summaries you computed inside that range.)

// The question I intend to explore for this project is how the per capita income of each
// neighborhood correlates to the overdue rate of 311 requests.
// Trace which neighborhood is which. Don't need correlation. 

public class Results {

    private String neighborhood_name;
    private double per_capita_income;
    private double overdue_rate;

    public Results (String neighborhood_name, double overdue_rate, double per_capita_income) {
        this.neighborhood_name = neighborhood_name;
        this.per_capita_income = per_capita_income;
        this.overdue_rate = overdue_rate;
    }

    public double getPerCapitaIncome() {
        return this.per_capita_income;
    }

    public double getOverdueRate() {
        return this.overdue_rate;
    }

    public String getNeighborhoodName() {
        return this.neighborhood_name;
    }
}