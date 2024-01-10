package com.restaurant.reservation.service;

import com.restaurant.reservation.model.User;
import com.restaurant.reservation.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    public IUserRepository userRepo;

    @Override
    public User registerUser(User user) {
        userRepo.save(user);
        return user;
    }

    @Override
    public User checkCredentials(String username, String password) {
        List<User> users = userRepo.findAll();
        for(User user: users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public User findById(Long reservationOwnerId) {
        return userRepo.findById(reservationOwnerId).get();
    }

    @Override
    public User addReservation(User user) {
        userRepo.save(user);
        return user;
    }
}
