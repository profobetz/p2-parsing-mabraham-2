import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
public class ServiceRequest {

    private LocalDate date_opened;
    private Optional <LocalDate> date_closed;
    private boolean is_closed;
    private boolean closed_on_time;
    private String reason_for_request;
    private Neighborhood neighborhood;

    public ServiceRequest (LocalDate date_opened, LocalDate date_closed, boolean is_closed, boolean closed_on_time, String reason_for_request, Neighborhood neighborhood){
        this.date_opened = date_opened;
        this.date_closed = Optional.ofNullable(date_closed);
        this.is_closed = is_closed;
        this.closed_on_time = closed_on_time;
        this.reason_for_request = reason_for_request;
        this.neighborhood = neighborhood;
    }

    public LocalDate getDateOpened() {
        return this.date_opened;
    }

    public Optional<LocalDate> getDateClosed() {
        return this.date_closed;
    }

    public boolean isClosed() {
        return this.is_closed;
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

    public long daysOpen() {
        return ChronoUnit.DAYS.between(
            date_opened, date_closed.orElse(LocalDate.now()));
        // long days_open = date_opened.until(date_closed, ChronoUnit.DAYS);
        // return days_open;
    }
}