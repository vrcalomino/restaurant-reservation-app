package com.restaurant.reservation.service;

import com.restaurant.reservation.model.User;
import com.restaurant.reservation.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    IUserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void register_user_success() {
        User user = new User();
        user.setName("Valentin");
        user.setSurname("Calomino");
        user.setUsername("vcalomi");
        user.setPassword("1234");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User result = userService.registerUser(user);
        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void checkCredentials_valid(){
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setName("Valentin");
        user.setSurname("Calomino");
        user.setUsername("vcalomi");
        user.setPassword("1234");
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);

        User result = userService.checkCredentials("vcalomi", "1234");

        assertEquals(user, result);
    }

    @Test
    void checkCredentials_invalid() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setName("Valentin");
        user.setSurname("Calomino");
        user.setUsername("vcalomi");
        user.setPassword("1234");
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);

        User result = userService.checkCredentials("msanchez", "1234");

        assertNull(result);
    }

    @Test
    void findById() {
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User result = userService.findById(userId);
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void addReservation() {
        User user = new User();
        userService.addReservation(user);
        verify(userRepository, times(1)).save(user);
    }
}
