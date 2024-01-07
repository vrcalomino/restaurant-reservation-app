package com.restaurant.reservation.service;

import com.restaurant.reservation.model.User;
import com.restaurant.reservation.repository.IUserRepository;
import org.springframework.stereotype.Service;

public interface IUserService {
    void registerUser(User user);

    User checkCredentials(String username, String password);
}
