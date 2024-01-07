package com.restaurant.reservation.service;

import com.restaurant.reservation.model.Reservation;
import com.restaurant.reservation.repository.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    public IReservationRepository reservationRepo;

    @Override
    public void createReservation(Reservation reservation) {
        reservationRepo.save(reservation);
    }
}
