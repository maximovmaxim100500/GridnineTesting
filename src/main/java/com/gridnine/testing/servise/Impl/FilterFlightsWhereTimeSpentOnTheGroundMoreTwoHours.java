package com.gridnine.testing.servise.Impl;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import com.gridnine.testing.servise.FlightFilterService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Фильтр для перелётов, который исключает все перелёты, где общее время,
 * проведённое на земле, превышает два часа.
 * <p>
 * Время на земле определяется как интервал между прилётом одного сегмента
 * и вылетом следующего за ним.
 * </p>
 */
public class FilterFlightsWhereTimeSpentOnTheGroundMoreTwoHours implements FlightFilterService {
    /**
     * Фильтрует список перелётов, исключая те, где общее время на земле
     * превышает два часа.
     *
     * @param flights список перелётов для фильтрации
     * @return список перелётов, прошедших фильтрацию
     */
    @Override
    public List<Flight> flightFilter(List<Flight> flights) {
        List<Flight> flightsGroundMoreThenTwoHours = new ArrayList<>();
        for (Flight flight : flights) { //проходим по всем перелетам и сегментам в них
            List<Segment> segments = flight.getSegments();
            Duration duration = Duration.ZERO;
            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime NextDepartDate = segments.get(i + 1).getDepartureDate();//время вылета следующего сегмента
                LocalDateTime pastArrivalDate = segments.get(i).getArrivalDate();//время прилета предыдущего сегмента
                duration = duration.plus(Duration.between(pastArrivalDate, NextDepartDate));//вычисляем разницу и
                //присваиваем ее переменной duration
            }
            if (duration.toHours() <= 2) {    //проверяем, что общее время на земле не превышает 2 часа
                flightsGroundMoreThenTwoHours.add(flight);
            }
        }
        return flightsGroundMoreThenTwoHours;
    }
}
