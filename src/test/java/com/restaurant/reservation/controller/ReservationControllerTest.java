package com.restaurant.reservation.controller;

import com.restaurant.reservation.model.Reservation;
import com.restaurant.reservation.model.RestaurantTable;
import com.restaurant.reservation.model.User;
import com.restaurant.reservation.service.IReservationService;
import com.restaurant.reservation.service.ITableService;
import com.restaurant.reservation.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationControllerTest {

    @InjectMocks
    ReservationController reservationController;

    @Mock
    ITableService tableService;

    @Mock
    IUserService userService;

    @Mock
    IReservationService reservationService;

    @Test
    void create_success() {
        User user = new User();
        user.setUser_id(1L);
        user.setName("Marta");
        user.setSurname("Sanchez");
        user.setUsername("msanchez");
        user.setPassword("msanchez");

        RestaurantTable table = new RestaurantTable();
        table.setTable_id(1L);
        table.setDescription("Nice table.");

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setTable(table);
        reservation.setReservationDate(new Date("02/17/2024 17:00:00"));

        when(userService.findById(user.getUser_id())).thenReturn(user);
        when(tableService.findById(table.getTable_id())).thenReturn(table);

        ResponseEntity response = reservationController.reservationCreation(reservation);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void create_fail_nullUser() {

        RestaurantTable table = new RestaurantTable();
        table.setTable_id(1L);
        table.setDescription("Nice table.");

        Reservation reservation = new Reservation();
        reservation.setTable(table);
        reservation.setReservationDate(new Date("02/17/2024 17:00:00"));

        ResponseEntity response = reservationController.reservationCreation(reservation);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void create_fail_nullTable() {
        User user = new User();
        user.setUser_id(1L);
        user.setName("Marta");
        user.setSurname("Sanchez");
        user.setUsername("msanchez");
        user.setPassword("msanchez");

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setReservationDate(new Date("02/17/2024 17:00:00"));

        ResponseEntity response = reservationController.reservationCreation(reservation);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void create_fail_nullReservation() {
        ResponseEntity response = reservationController.reservationCreation(null);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void create_fail_noReservationDate() {
        User user = new User();
        user.setUser_id(1L);
        user.setName("Marta");
        user.setSurname("Sanchez");
        user.setUsername("msanchez");
        user.setPassword("msanchez");

        RestaurantTable table = new RestaurantTable();
        table.setTable_id(1L);
        table.setDescription("Nice table.");

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setTable(table);

        ResponseEntity response = reservationController.reservationCreation(reservation);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
