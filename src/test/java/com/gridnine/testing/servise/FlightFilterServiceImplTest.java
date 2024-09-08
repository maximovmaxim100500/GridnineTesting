package com.gridnine.testing.servise;

import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightFilterServiceImplTest {
    FlightFilterService flightFilterService = new FlightFilterServiceImpl();
    List<Flight> flights = FlightBuilder.createFlights();

    @Test
    void filterDepartureToTheCurrentPointInTime() {
        List<Flight> expected = new ArrayList<>();
        expected.add(flights.get(2));
        assertEquals(expected, flightFilterService.filterDepartureToTheCurrentPointInTime(flights));
    }

    @Test
    void filterArrivalDateBeforeDepartureDate() {
        List<Flight> expected = new ArrayList<>();
        expected.add(flights.get(3));
        assertEquals(expected, flightFilterService.filterArrivalDateBeforeDepartureDate(flights));
    }

    @Test
    void filterFlightsWhereTimeSpentOnTheGroundMoreTwoHours() {
        List<Flight> expected = new ArrayList<>();
        expected.add(flights.get(4));
        expected.add(flights.get(5));
        assertEquals(expected, flightFilterService.filterFlightsWhereTimeSpentOnTheGroundMoreTwoHours(flights));
    }
}