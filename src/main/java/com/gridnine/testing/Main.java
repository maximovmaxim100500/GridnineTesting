package com.gridnine.testing;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.servise.Impl.FilterArrivalDateBeforeDepartureDate;
import com.gridnine.testing.servise.Impl.FilterDepartureToTheCurrentPointInTime;
import com.gridnine.testing.servise.Impl.FilterFlightsWhereTimeSpentOnTheGroundMoreTwoHours;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FilterArrivalDateBeforeDepartureDate arrivalDateBeforeDepartureDate = new FilterArrivalDateBeforeDepartureDate();
        FilterDepartureToTheCurrentPointInTime departureToTheCurrentPointInTime = new FilterDepartureToTheCurrentPointInTime();
        FilterFlightsWhereTimeSpentOnTheGroundMoreTwoHours flightsWhereTimeSpentOnTheGroundMoreTwoHours = new FilterFlightsWhereTimeSpentOnTheGroundMoreTwoHours();

        System.out.println(flights);
        System.out.println("======================================================");
        System.out.println("Исключение перелетов с вылетом до текущего момента времени:");
        System.out.println(departureToTheCurrentPointInTime.flightFilter(flights));
        System.out.println("======================================================");
        System.out.println("Исключение перелетов с сегментом с датой прилёта раньше даты вылета:");
        System.out.println(arrivalDateBeforeDepartureDate.flightFilter(flights));
        System.out.println("======================================================");
        System.out.println("Исключение перелетов, где общее время, проведённое на земле, превышает два часа:");
        System.out.println(flightsWhereTimeSpentOnTheGroundMoreTwoHours.flightFilter(flights));
    }
}