package com.gridnine.testing.servise;

import com.gridnine.testing.entity.Flight;

import java.util.List;

public interface FlightFilterService {
    List<Flight> flightFilter(List<Flight> flights);
}
