import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;


class TicketTest {

    @Test
    void flightTimeTest() {
        LocalDateTime ldt = LocalDateTime.of(2022, 10, 25, 12, 45);
        Ticket ticket = new Ticket(Airport.VVO, "Владивосток", Airport.TLV, "Тель-Авив",
                "25.10.22", "12:45", "25.10.22", "12:45",
                null,0,0);

        ZonedDateTime zdtOrigin = ZonedDateTime.of(ldt, Airport.VVO.getZoneId());
        ZonedDateTime zdtDestination = ZonedDateTime.of(ldt, Airport.TLV.getZoneId());
        long expected = zdtOrigin.until(zdtDestination, ChronoUnit.MINUTES);
        long actual = ticket.flightTime();
        Assertions.assertEquals(expected, actual);
    }
}