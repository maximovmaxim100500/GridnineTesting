package com.gridnine.testing.servise;

import com.gridnine.testing.entity.Flight;

import java.util.List;

public interface FlightFilterService {
    List<Flight> filterDepartureToTheCurrentPointInTime(List<Flight> flights);
    List<Flight> filterArrivalDateBeforeDepartureDate(List<Flight> flights);
    List<Flight> filterFlightsWhereTheTotalTimeSpentOnTheGroundExceedsTwoHours(List<Flight> flights);
}
