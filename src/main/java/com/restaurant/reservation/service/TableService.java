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
    public void createTable(RestaurantTable table) {
        tableRepo.save(table);
    }
}
