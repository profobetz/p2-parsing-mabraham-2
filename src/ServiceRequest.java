import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class ServiceRequest {
    private final LocalDate date_opened;
    private final LocalDate date_closed;
    private final boolean isClosed;
    private final boolean closed_on_time;
    private final String reason_for_request;
    private final Neighborhood neighborhood;

    public ServiceRequest (LocalDate date_opened, LocalDate date_closed, boolean isClosed, boolean closed_on_time, String reason_for_request, Neighborhood neighborhood){
        this.date_opened = date_opened;
        this.date_closed = date_closed;
        this.isClosed = isClosed;
        this.closed_on_time = closed_on_time;
        this.reason_for_request = reason_for_request;
        this.neighborhood = neighborhood;
    }

    public LocalDate getDateOpened() {
        return this.date_opened;
    }

    public LocalDate getDateClosed() {
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

    public long daysOpen(LocalDate date_opened, LocalDate date_closed) {
        long days_open = date_opened.until(date_closed, ChronoUnit.DAYS);
        return days_open;
    }
}