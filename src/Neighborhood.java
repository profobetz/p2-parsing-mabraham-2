import java.util.ArrayList;
import java.util.List;

public class Neighborhood {

    private String name;
    private ArrayList<ServiceRequest> ServiceRequestList;
    private double per_capita_income;

    public Neighborhood(String name) {
        this.name = name;
        this.ServiceRequestList = new ArrayList<>();
    }
    
    public String getName() {
        return this.name;
    }

    public void addRequest(ServiceRequest request) {
        this.ServiceRequestList.add(request);
    }

    public ArrayList<ServiceRequest> getServiceRequestList() {
        return this.ServiceRequestList;
    }

    public double getAverageDaysOpen() {
        long total_days = 0;

        for (ServiceRequest request: this.ServiceRequestList) {
            total_days += request.daysOpen();
        }
        double averageDaysOpen = total_days / (double) this.getTotalRequestCount();
        return averageDaysOpen;
    }  

    public List<ServiceRequest> getOpenCases() {
        List<ServiceRequest> open_cases = new ArrayList<>();

        for (ServiceRequest request: this.ServiceRequestList) {
            if (!request.isClosed()) {
                open_cases.add(request);
            }
        }
        return open_cases;
    }

    public List<ServiceRequest> getOverdueCases() {
        List <ServiceRequest> overdue_cases = new ArrayList<>();

        for (ServiceRequest request: this.ServiceRequestList) {
            if (!request.is_closed_on_time()) {
                overdue_cases.add(request);
            }
        }
        return overdue_cases;
    }

    public double getOverdueRate() {
        // int overdue_cases = 0;
        // for (ServiceRequest request: ServiceRequestList) {
        //     if (!request.is_closed_on_time()) {
        //         overdue_cases++;
        //     }
        // }
        // double overdue_rate = overdue_cases/this.ServiceRequestList.size() * 100;
        // return overdue_rate;

        return this.getOverdueCases().size() / (double) this.getTotalRequestCount() * 100;
    }

    public int getTotalRequestCount() {
        return this.ServiceRequestList.size();
    }

    public void setPerCapitaIncome(double per_capita_income) {
        this.per_capita_income = per_capita_income;
    }

    public double getPerCapitaIncome() {
        return this.per_capita_income;
    }
}