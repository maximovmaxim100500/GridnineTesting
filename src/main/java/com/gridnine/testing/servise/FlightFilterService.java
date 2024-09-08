package com.gridnine.testing.servise;

import com.gridnine.testing.entity.Flight;

import java.util.List;

public interface FlightFilterService {
    //Метод для получения перелетов до текущего момента времени.
    List<Flight> filterDepartureToTheCurrentPointInTime(List<Flight> flights);

    //Метод для получения перелетов с сегментами с датой прилёта раньше даты вылета.
    List<Flight> filterArrivalDateBeforeDepartureDate(List<Flight> flights);

    //Метод для получения перелетов, где общее время, проведённое на земле, превышает два часа
    List<Flight> filterFlightsWhereTimeSpentOnTheGroundMoreTwoHours(List<Flight> flights);
}
