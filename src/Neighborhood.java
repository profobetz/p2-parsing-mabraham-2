import java.util.ArrayList;
import java.util.List;

public class Neighborhood {
    private final String name;
    private ArrayList<ServiceRequest> ServiceRequestList;

    public Neighborhood(String name) {
        this.name = name;
        this.ServiceRequestList = new ArrayList<>();     
    }
    
    public String getName() {
        return this.name;
    }

    public void addRequest(ServiceRequest request) {
        ServiceRequestList.add(request);
    }

    public ArrayList<ServiceRequest> getServiceRequestList() {
        return this.ServiceRequestList;
    }

    public double getAverageDaysOpen() {
        double averageDaysOpen;
        return averageDaysOpen;
    }  

    public List<ServiceRequest> getOpenCases() {
        int open_cases;
        return open_cases;
    }

    public List<ServiceRequest> getOverdueCases() {
        int overdue_cases;
        return overdueCases;
    }

    public double getOverdueRate() {
        double overdueRate;
        return overdueRate;
    }

    public int getTotalRequestCount() {
        int requestCount = ServiceRequestList.size();
        return requestCount;
    }
}