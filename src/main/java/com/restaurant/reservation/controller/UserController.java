package com.restaurant.reservation.controller;

import com.restaurant.reservation.dto.UserCredentialsDTO;
import com.restaurant.reservation.model.User;
import com.restaurant.reservation.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public IUserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return new ResponseEntity<>(user.getUser_id(), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserCredentialsDTO user) {
        try {
            User validatedUser = userService.checkCredentials(user.getUsername(), user.getPassword());
            return new ResponseEntity<>(validatedUser.getUser_id(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }









}
