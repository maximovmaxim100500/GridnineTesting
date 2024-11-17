package com.gridnine.testing.servise.Impl;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import com.gridnine.testing.servise.FlightFilterService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterFlightsWhereTimeSpentOnTheGroundMoreTwoHours implements FlightFilterService {
    @Override
    public List<Flight> flightFilter(List<Flight> flights) {
        List<Flight> flightsGroundMoreThenTwoHours = new ArrayList<>();
        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments();
            Duration duration = Duration.ZERO;
            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime NextDepartDate = segments.get(i + 1).getDepartureDate();
                LocalDateTime pastArrivalDate = segments.get(i).getArrivalDate();
                duration = duration.plus(Duration.between(pastArrivalDate, NextDepartDate));
            }
            if (duration.toHours() <= 2) {
                flightsGroundMoreThenTwoHours.add(flight);
            }
        }
        return flightsGroundMoreThenTwoHours;
    }
}
