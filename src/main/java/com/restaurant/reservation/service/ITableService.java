package com.restaurant.reservation.service;

import com.restaurant.reservation.model.RestaurantTable;

import java.util.List;

public interface ITableService {
    RestaurantTable createTable(RestaurantTable table);

    RestaurantTable findById(Long tableId);

    RestaurantTable addReservation(RestaurantTable table);

    List<RestaurantTable> findAll();
}
