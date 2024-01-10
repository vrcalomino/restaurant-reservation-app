package com.restaurant.reservation.service;

import com.restaurant.reservation.model.Reservation;
import com.restaurant.reservation.model.RestaurantTable;
import com.restaurant.reservation.model.User;
import com.restaurant.reservation.repository.IReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @InjectMocks
    ReservationService reservationService;

    @Mock
    IReservationRepository reservationRepository;

    @Test
    void test_createReservation() {
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

        reservationService.createReservation(reservation);
        verify(reservationRepository, times(1)).save(reservation);
    }
}
