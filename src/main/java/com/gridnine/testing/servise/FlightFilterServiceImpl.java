package com.gridnine.testing.servise;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterServiceImpl implements FlightFilterService {
    @Override
    public List<Flight> filterDepartureToTheCurrentPointInTime(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment
                                .getDepartureDate()
                                .isBefore(LocalDateTime.now()))).toList();
    }

    @Override
    public List<Flight> filterArrivalDateBeforeDepartureDate(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment
                                .getArrivalDate()
                                .isBefore(segment.getDepartureDate()))).toList();
    }

    @Override
    public List<Flight> filterFlightsWhereTimeSpentOnTheGroundMoreTwoHours(List<Flight> flights) {
        List<Flight> flightsGroundMoreThenTwoHours = new ArrayList<>();
        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments();
            Duration duration = Duration.ZERO;
            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime NextDepartDate = segments.get(i + 1).getDepartureDate();
                LocalDateTime pastArrivalDate = segments.get(i).getArrivalDate();
                duration = duration.plus(Duration.between(pastArrivalDate, NextDepartDate));
            }
            if (duration.toHours() >= 2) {
                flightsGroundMoreThenTwoHours.add(flight);
            }
        }
        return flightsGroundMoreThenTwoHours;
    }
}
