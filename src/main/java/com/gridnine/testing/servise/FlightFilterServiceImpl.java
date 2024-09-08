package com.gridnine.testing.servise;

import com.gridnine.testing.entity.Flight;

import java.time.LocalDateTime;
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
        return null;
    }

    @Override
    public List<Flight> filterFlightsWhereTheTotalTimeSpentOnTheGroundExceedsTwoHours(List<Flight> flights) {
        return null;
    }
}
