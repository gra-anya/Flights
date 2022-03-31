import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import java.io.IOException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("tickets.json");

        try {
            JsonNode jsonNode = mapper.readTree(file);
            List<Ticket> tickets = mapper.readValue(jsonNode.get("tickets").traverse(), new TypeReference<>() {
            });
            List<Ticket> vvoToTlv = tickets.stream().filter(value -> value.getOrigin() == Airport.VVO)
                    .filter(value -> value.getDestination() == Airport.TLV)
                    .toList();

            System.out.println("Среднее время полета Владивосток - Тель-Авив: "
                    + timeToString(averageFlightTime(vvoToTlv)));
            System.out.println("90-й процентиль полета Владивосток - Тель-Авив: "
                    + timeToString(percentile(vvoToTlv, 90)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static long averageFlightTime(List<Ticket> tickets) {
        long sumFlightTime = 0;
        for (Ticket ticket : tickets) {
            sumFlightTime += ticket.flightTime();
        }
        return sumFlightTime / tickets.size();
    }

    public static long percentile(List<Ticket> tickets, double percentile) {
        List<Long> minutesFlight = tickets.stream()
                .map(Ticket::flightTime)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        int index = (int) Math.ceil(percentile / 100.0 * minutesFlight.size());
        return minutesFlight.get(index - 1);
    }

    public static String timeToString(long time) {
        return String.format(" %d часов, %d минут", time / 60, time % 60);
    }

}
