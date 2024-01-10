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
        if (user == null) {
            return new ResponseEntity<>("Missing data", HttpStatus.BAD_REQUEST);
        }
        if (user.getName() == null) {
            return new ResponseEntity<>("Missing name", HttpStatus.BAD_REQUEST);
        }
        if (user.getSurname() == null) {
            return new ResponseEntity<>("Missing surname", HttpStatus.BAD_REQUEST);
        }
        if (user.getUsername() == null) {
            return new ResponseEntity<>("Missing username", HttpStatus.BAD_REQUEST);
        }
        if (user.getPassword() == null) {
            return new ResponseEntity<>("Missing password", HttpStatus.BAD_REQUEST);
        }
        userService.registerUser(user);
        return new ResponseEntity<>(user.getUser_id(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserCredentialsDTO user) {
        if (user == null) {
            return new ResponseEntity<>("Missing data", HttpStatus.BAD_REQUEST);
        }
        if (user.getUsername() == null) {
            return new ResponseEntity<>("Missing username", HttpStatus.BAD_REQUEST);
        }
        if (user.getPassword() == null) {
            return new ResponseEntity<>("Missing password", HttpStatus.BAD_REQUEST);
        }
        User validatedUser = userService.checkCredentials(user.getUsername(), user.getPassword());
        if (validatedUser == null) {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(validatedUser.getUser_id(), HttpStatus.OK);
    }









}
