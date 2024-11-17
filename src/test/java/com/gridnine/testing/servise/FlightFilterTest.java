package com.gridnine.testing.servise;

import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import com.gridnine.testing.servise.Impl.FilterArrivalDateBeforeDepartureDate;
import com.gridnine.testing.servise.Impl.FilterDepartureToTheCurrentPointInTime;
import com.gridnine.testing.servise.Impl.FilterFlightsWhereTimeSpentOnTheGroundMoreTwoHours;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightFilterTest {
    List<Flight> flights = FlightBuilder.createFlights();

    @Test
    void filterDepartureToTheCurrentPointInTime() {
        FlightFilterService filterService = new FilterDepartureToTheCurrentPointInTime();
        List<Flight> expected = flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> LocalDateTime.now().isBefore(segment
                                .getDepartureDate()))).toList();
        assertEquals(expected, filterService.flightFilter(flights));
    }

    @Test
    void filterArrivalDateBeforeDepartureDate() {
        FlightFilterService filterService = new FilterArrivalDateBeforeDepartureDate();
        List<Flight> expected = flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment
                                .getArrivalDate()
                                .isAfter(segment.getDepartureDate()))).toList();
        assertEquals(expected, filterService.flightFilter(flights));
    }

    @Test
    void filterFlightsWhereTimeSpentOnTheGroundMoreTwoHours() {
        FlightFilterService filterService = new FilterFlightsWhereTimeSpentOnTheGroundMoreTwoHours();
        List<Flight> expected = new ArrayList<>();
        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments();
            Duration duration = Duration.ZERO;
            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime NextDepartDate = segments.get(i + 1).getDepartureDate();
                LocalDateTime pastArrivalDate = segments.get(i).getArrivalDate();
                duration = duration.plus(Duration.between(pastArrivalDate, NextDepartDate));
            }
            if (duration.toHours() <= 2) {
                expected.add(flight);
            }
        }
        assertEquals(expected, filterService.flightFilter(flights));
    }
}