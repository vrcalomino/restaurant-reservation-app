package com.restaurant.reservation.service;

import com.restaurant.reservation.model.User;
import com.restaurant.reservation.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    public IUserRepository userRepo;

    @Override
    public User registerUser(User user) throws Exception {
        if (user == null) {
            throw new Exception("Missing user");
        }
        if (user.getName() == null) {
            throw new Exception("Missing name");
        }
        if (user.getSurname() == null) {
            throw new Exception("Missing surname");
        }
        if (user.getUsername() == null) {
            throw new Exception("Missing username");
        }
        if (user.getPassword() == null) {
            throw new Exception("Missing password");
        }
        userRepo.save(user);
        return user;
    }

    @Override
    public User checkCredentials(String username, String password) throws Exception {
        if (username == null) {
            throw new Exception("Missing username");
        }
        if (password == null) {
            throw new Exception("Missing password");
        }
        List<User> users = userRepo.findAll();
        User foundUser = null;
        for(User user: users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    foundUser = user;
                }
            }
        }
        if (foundUser == null) {
            throw new Exception("Password or username doesn't exist");
        }
        return foundUser;
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
