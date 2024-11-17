package com.gridnine.testing.servise.Impl;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.servise.FlightFilterService;

import java.util.List;

public class FilterArrivalDateBeforeDepartureDate implements FlightFilterService {

    @Override
    public List<Flight> flightFilter(List<Flight> flights) {

        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment
                                .getArrivalDate()
                                .isAfter(segment.getDepartureDate()))).toList();
    }
}
