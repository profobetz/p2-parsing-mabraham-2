import java.util.ArrayList;
import java.util.List;

public class Neighborhood {
    private final String name;
    private ArrayList<ServiceRequest> ServiceRequestList;
    // private boolean exists;

    public Neighborhood(String name) {
        this.name = name;
        this.ServiceRequestList = new ArrayList<>();
        // this.exists = true;     
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

    public boolean isInServiceRequestList (ServiceRequest target) {
        boolean isInList = false;
        for (ServiceRequest request: this.ServiceRequestList) {
            if (target.equals(request)) {
                isInList = true;
                break;
            } else {
                isInList = false;
            }
        }
        return isInList;
    }

    public double getAverageDaysOpen() {
        long [] days_open_array = new long [ServiceRequestList.size()];
        int count = 0;
        long sum = 0;
        for (ServiceRequest request: ServiceRequestList) {
            days_open_array[count] = request.daysOpen(request.getDateOpened(), request.getDateClosed());
            count++;  
        }
        for (long days_open : days_open_array) {
            sum = sum + days_open;
        }

        double averageDaysOpen = (sum/count);
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

    // public boolean exists(){
    //     return this.exists;
    // }
}