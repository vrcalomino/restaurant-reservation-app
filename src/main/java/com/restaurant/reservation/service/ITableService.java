package com.restaurant.reservation.service;

import com.restaurant.reservation.model.RestaurantTable;

public interface ITableService {
    void createTable(RestaurantTable table);

    RestaurantTable findById(Long tableId);

    void addReservation(RestaurantTable table);
}
