package com.gridnine.testing;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.servise.FlightFilterService;
import com.gridnine.testing.servise.FlightFilterServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilterService flightFilterService = new FlightFilterServiceImpl();
        System.out.println(flights);
        System.out.println("======================================================");
        System.out.println("Вылеты до текущего момента времени:");
        System.out.println(flightFilterService.filterDepartureToTheCurrentPointInTime(flights));
        System.out.println("======================================================");
        System.out.println("Сегменты с датой прилёта раньше даты вылета:");
        System.out.println(flightFilterService.filterArrivalDateBeforeDepartureDate(flights));
        System.out.println("======================================================");
        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа:");
        System.out.println(flightFilterService.filterFlightsWhereTimeSpentOnTheGroundMoreTwoHours(flights));
    }
}