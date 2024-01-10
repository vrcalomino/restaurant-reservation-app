package com.restaurant.reservation.service;

import com.restaurant.reservation.model.RestaurantTable;
import com.restaurant.reservation.repository.ITableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService implements ITableService {

    @Autowired
    public ITableRepository tableRepo;

    @Override
    public RestaurantTable createTable(RestaurantTable table) {
        tableRepo.save(table);
        return table;
    }

    @Override
    public RestaurantTable findById(Long tableId) {
        return tableRepo.findById(tableId).get();
    }

    @Override
    public RestaurantTable addReservation(RestaurantTable table) {
        tableRepo.save(table);
        return table;
    }
}
