package com.restaurant.reservation.service;

import com.restaurant.reservation.model.User;
import com.restaurant.reservation.repository.IUserRepository;
import org.springframework.stereotype.Service;

public interface IUserService {
    User registerUser(User user) throws Exception;

    User checkCredentials(String username, String password) throws Exception;

    User findById(Long reservationOwnerId);

    User addReservation(User user);
}
