public class ServiceRequest {
    private String date_opened;
    private String date_closed;
    private boolean isClosed;
    private boolean closed_on_time;
    private String reason_for_request;
    private Neighborhood neighborhood;

    public ServiceRequest (String date_opened, String date_closed, boolean isClosed, boolean closed_on_time, String reason_for_request, Neighborhood neighborhood){
        this.date_opened = date_opened;
        this.date_closed = date_closed;
        this.isClosed = isClosed;
        this.closed_on_time = closed_on_time;
        this.reason_for_request = reason_for_request;
        this.neighborhood = neighborhood;
    }

    public String getDateOpened() {
        return this.date_opened;
    }

    public String getDateClosed() {
        return this.date_closed;
    }

    public boolean isClosed() {
        return this.isClosed;
    }

    public boolean is_closed_on_time() {
        return this.closed_on_time;
    }

    public String get_reason_for_request() {
        return this.reason_for_request;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public int daysOpen(String date_opened, String date_closed) {
        int days_open;
        return days_open;
    }
}