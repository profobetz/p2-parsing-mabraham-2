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
        this.ServiceRequestList.add(request);
    }

    public ArrayList<ServiceRequest> getServiceRequestList() {
        return this.ServiceRequestList;
    }

    public double getAverageDaysOpen() {
        long [] days_open_array = {};
        int count = 0;
        int sum = 0;
        for (ServiceRequest request: ServiceRequestList) {
            days_open_array[count] = request.daysOpen(request.getDateOpened(), request.getDateClosed());
            count++;  
        }
        for (long days_open : days_open_array) {
            sum += days_open;
        }

        double averageDaysOpen = sum/count;
        return averageDaysOpen;
    }  

    public List<ServiceRequest> getOpenCases() {
        ArrayList <ServiceRequest> open_cases = new ArrayList<>();
        for (ServiceRequest request: ServiceRequestList) {
            if (!request.isClosed()) {
                open_cases.add(request);
            }
        }
        return open_cases;
    }

    public List<ServiceRequest> getOverdueCases() {
        ArrayList <ServiceRequest> overdue_cases = new ArrayList<>();
        for (ServiceRequest request: ServiceRequestList) {
            if (!request.is_closed_on_time()) {
                overdue_cases.add(request);
            }
        }
        return overdue_cases;
    }

    public double getOverdueRate() {
        int overdue_cases = 0;
        for (ServiceRequest request: ServiceRequestList) {
            if (!request.is_closed_on_time()) {
                overdue_cases++;
            }
        }
        double overdue_rate = overdue_cases/ServiceRequestList.size();
        return overdue_rate;
    }

    public int getTotalRequestCount() {
        int requestCount = ServiceRequestList.size();
        return requestCount;
    }
}