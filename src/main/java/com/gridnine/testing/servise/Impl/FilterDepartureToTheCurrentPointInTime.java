package com.gridnine.testing.servise.Impl;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.servise.FlightFilterService;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Фильтр для перелётов, который исключает все перелёты, где хотя бы у одного сегмента
 * вылет до текущего момента времени.
 * * <p>
 *  * Этот фильтр проверяет каждый сегмент перелёта и оставляет только те
 *  * перелёты, где все сегменты имеют корректные даты (дата вылета после текущего момента времени).
 *  * </p>
 */
public class FilterDepartureToTheCurrentPointInTime implements FlightFilterService {
    /**
     * Фильтрует список перелётов, исключая те, где хотя бы у одного сегмента
     * вылет до текущего момента времени.
     *
     * @param flights список перелётов для фильтрации
     * @return список перелётов, прошедших фильтрацию
     */
    @Override
    public List<Flight> flightFilter(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> now.isBefore(segment
                                        .getDepartureDate()))).toList();
    }
}
