import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    static List<Ticket> tickets;

    @BeforeAll
    static void initialTicketList() {
        Ticket ticket1 = Mockito.mock(Ticket.class);
        Mockito.when(ticket1.flightTime()).thenReturn(100l);
        Ticket ticket2 = Mockito.mock(Ticket.class);
        Mockito.when(ticket2.flightTime()).thenReturn(200l);
        Ticket ticket3 = Mockito.mock(Ticket.class);
        Mockito.when(ticket3.flightTime()).thenReturn(300l);

        tickets = Arrays.asList(ticket1, ticket2, ticket3);
    }

    @Test
    void averageFlightTimeTest() {

        long expected = (100 + 200 + 300) / 3;
        long actual = Main.averageFlightTime(tickets);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void percentile() {
        List<Long> minutesFlight = tickets.stream()
                .map(Ticket::flightTime)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        double percentile = 90;
        int index = (int) Math.ceil(percentile / 100.0 * 3);
        long expected = minutesFlight.get(index - 1);
        long actual = Main.percentile(tickets, percentile);

        Assertions.assertEquals(expected, actual);
    }
}