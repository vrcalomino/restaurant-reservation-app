package com.restaurant.reservation.controller;

import com.restaurant.reservation.model.RestaurantTable;
import com.restaurant.reservation.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    public ITableService tableService;

    @PostMapping("/create")
    public ResponseEntity tableCreation(@RequestBody RestaurantTable table) {
        if(table == null) {
            return new ResponseEntity<>("No table provided", HttpStatus.BAD_REQUEST);
        }
        if (table.getDescription() == null) {
            return new ResponseEntity<>("No description provided", HttpStatus.BAD_REQUEST);
        }
        tableService.createTable(table);
        return new ResponseEntity<>(table.getTable_id(), HttpStatus.CREATED);
    }
}
