package com.gridnine.testing.servise.Impl;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.servise.FlightFilterService;

import java.util.List;
/**
 * Фильтр для перелётов, который исключает сегменты, в которых дата прилёта
 * происходит раньше даты вылета.
 * <p>
 * Этот фильтр проверяет каждый сегмент перелёта и оставляет только те
 * перелёты, где все сегменты имеют корректные даты (дата прилёта позже даты вылета).
 * </p>
 */
public class FilterArrivalDateBeforeDepartureDate implements FlightFilterService {
    /**
     * Фильтрует список перелётов, исключая те, где хотя бы один сегмент
     * имеет дату прилёта раньше даты вылета.
     *
     * @param flights список перелётов для фильтрации
     * @return список перелётов, прошедших фильтрацию
     */
    @Override
    public List<Flight> flightFilter(List<Flight> flights) {

        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment
                                .getArrivalDate()
                                .isAfter(segment.getDepartureDate()))).toList();
    }
}
