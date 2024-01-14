package com.restaurant.reservation.controller;

import com.restaurant.reservation.model.RestaurantTable;
import com.restaurant.reservation.model.User;
import com.restaurant.reservation.service.ITableService;
import com.restaurant.reservation.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    public ITableService tableService;

    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    public ResponseEntity tableCreation(@RequestBody RestaurantTable table, @RequestHeader(name = "user_id") Long user_id) {
        if(table == null) {
            return new ResponseEntity<>("No table provided", HttpStatus.BAD_REQUEST);
        }
        if (table.getDescription() == null) {
            return new ResponseEntity<>("No description provided", HttpStatus.BAD_REQUEST);
        }
        if (user_id == null) {
            return new ResponseEntity<>("Missing header auth", HttpStatus.UNAUTHORIZED);
        }
        User authorizedUser = userService.findById(user_id);
        if (!authorizedUser.getIsAdmin()) {
            return new ResponseEntity<>("Missing header auth", HttpStatus.UNAUTHORIZED);
        }
        tableService.createTable(table);
        return new ResponseEntity<>(table.getTable_id(), HttpStatus.CREATED);
    }
}
