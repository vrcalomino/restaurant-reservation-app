package com.restaurant.reservation.controller;

import com.restaurant.reservation.model.Reservation;
import com.restaurant.reservation.model.RestaurantTable;
import com.restaurant.reservation.model.User;
import com.restaurant.reservation.service.IReservationService;
import com.restaurant.reservation.service.ITableService;
import com.restaurant.reservation.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    public IReservationService reservationService;

    @Autowired
    public IUserService userService;

    @Autowired
    public ITableService tableService;

    @PostMapping("/create")
    public ResponseEntity reservationCreation (@RequestBody Reservation reservation) {
        if (reservation == null) {
            return new ResponseEntity<>("No reservation provided", HttpStatus.BAD_REQUEST);
        }
        if (reservation.getReservationDate() == null) {
            return new ResponseEntity<>("No date provided", HttpStatus.BAD_REQUEST);
        }
        if (reservation.getTable() == null) {
            return new ResponseEntity<>("No table provided", HttpStatus.BAD_REQUEST);
        }
        if (reservation.getUser() == null) {
            return new ResponseEntity<>("No reservation owner provided", HttpStatus.BAD_REQUEST);
        }
        reservationService.createReservation(reservation);
        User user = userService.findById(reservation.getUser().getUser_id());
        if(user == null) {
            return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);
        }
        user.setReservation_id(reservation);
        userService.addReservation(user);

        RestaurantTable table = tableService.findById(reservation.getTable().getTable_id());
        if (table == null) {
            return new ResponseEntity<>("Table doesn't exist", HttpStatus.BAD_REQUEST);
        }
        List<Reservation> existingReservations = table.getReservations();
        if (existingReservations == null) {
            existingReservations = new ArrayList<>();
        }
        existingReservations.add(reservation);
        table.setReservations(existingReservations);
        tableService.addReservation(table);
        return new ResponseEntity<>(reservation.getReservation_id(), HttpStatus.CREATED);

    }
}
