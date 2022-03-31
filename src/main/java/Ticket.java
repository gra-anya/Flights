import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Ticket {

    private Airport origin;
    private String originName;
    private Airport destination;
    private String destinationName;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String carrier;
    private int stops;
    private int price;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");


    public Ticket() {
    }

    public Ticket(@JsonProperty("origin") Airport origin,
                  @JsonProperty("origin_name") String originName,
                  @JsonProperty("destination") Airport destination,
                  @JsonProperty("destination_name") String destinationName,
                  @JsonProperty(value = "departure_date") String date,
                  @JsonProperty(value = "departure_time") String time,
                  @JsonProperty("arrival_date") String arrivalDate,
                  @JsonProperty("arrival_time") String arrivalTime,
                  @JsonProperty("carrier") String carrier,
                  @JsonProperty("stops") int stops,
                  @JsonProperty("price") int price
    ) {
        this.origin = origin;
        this.originName = originName;
        this.destination = destination;
        this.destinationName = destinationName;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;

        this.departureDate = LocalDateTime.parse(date + " " + time, dtf);
        this.arrivalDate = LocalDateTime.parse(arrivalDate + " " + arrivalTime, dtf);

    }


    public Airport getOrigin() {
        return origin;
    }

    public String getOriginName() {
        return originName;
    }

    public Airport getDestination() {
        return destination;
    }


    public String getCarrier() {
        return carrier;
    }

    public int getStops() {
        return stops;
    }

    public int getPrice() {
        return price;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "Аэропорт вылета: " + origin + '\'' +
                ", город вылета:'" + originName + '\'' +
                ", аэропорт прилета: " + destination + '\'' +
                ", город прилета: " + destinationName + '\'' +
                ", дата и время вылета: " + departureDate +
                ", дата и время прилета:" + arrivalDate +
                ", перевозчик" + carrier + '\'' +
                ", остановки: " + stops +
                ", цена: " + price +
                '}';
    }

    public long flightTime() {
        ZonedDateTime zdtOrigin = ZonedDateTime.of(this.getDepartureDate(), this.getOrigin().getZoneId());
        ZonedDateTime zdtDestination = ZonedDateTime.of(this.getArrivalDate(), this.getDestination().getZoneId());
        return zdtOrigin.until(zdtDestination,ChronoUnit.MINUTES);
    }

}
