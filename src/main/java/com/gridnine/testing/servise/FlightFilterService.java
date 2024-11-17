package com.gridnine.testing.servise;

import com.gridnine.testing.entity.Flight;

import java.util.List;
/**
 * Интерфейс для фильтрации перелётов.
 */
public interface FlightFilterService {
    /**
     * Фильтрует список перелётов на основе определённых критериев.
     *
     * @param flights список перелётов для фильтрации
     * @return список перелётов, прошедших фильтрацию
     */
    List<Flight> flightFilter(List<Flight> flights);
}
