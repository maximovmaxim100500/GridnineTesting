package com.gridnine.testing.servise.Impl;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.servise.FlightFilterService;

import java.time.LocalDateTime;
import java.util.List;

public class FilterDepartureToTheCurrentPointInTime implements FlightFilterService {
    @Override
    public List<Flight> flightFilter(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> now.isBefore(segment
                                        .getDepartureDate()))).toList();
    }
}
