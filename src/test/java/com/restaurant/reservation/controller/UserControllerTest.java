package com.restaurant.reservation.controller;

import com.restaurant.reservation.dto.UserCredentialsDTO;
import com.restaurant.reservation.model.User;
import com.restaurant.reservation.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Test
    void register_success(){
        User user = new User();
        user.setName("Marta");
        user.setSurname("Sanchez");
        user.setUsername("msanchez");
        user.setPassword("msanchez");
        ResponseEntity response = userController.register(user);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void register_fail_nouser(){
        ResponseEntity response = userController.register(null);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void register_fail_noname(){
        User user = new User();
        user.setSurname("Sanchez");
        user.setUsername("msanchez");
        user.setPassword("msanchez");
        ResponseEntity response = userController.register(null);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void register_fail_nosurname(){
        User user = new User();
        user.setName("Marta");
        user.setUsername("msanchez");
        user.setPassword("msanchez");
        ResponseEntity response = userController.register(null);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void register_fail_nousername(){
        User user = new User();
        user.setName("Marta");
        user.setSurname("Sanchez");
        user.setPassword("msanchez");
        ResponseEntity response = userController.register(null);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void register_fail_nopassword(){
        User user = new User();
        user.setName("Marta");
        user.setSurname("Sanchez");
        user.setUsername("msanchez");
        ResponseEntity response = userController.register(null);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void login_success() {
        User user = new User();
        user.setName("Marta");
        user.setSurname("Sanchez");
        user.setUsername("msanchez");
        user.setPassword("msanchez");

        UserCredentialsDTO loginUser = new UserCredentialsDTO();
        loginUser.setUsername("msanchez");
        loginUser.setPassword("msanchez");
        when(userService.checkCredentials("msanchez", "msanchez")).thenReturn(user);
        ResponseEntity response = userController.login(loginUser);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void login_fail_nousername() {
        UserCredentialsDTO user = new UserCredentialsDTO();
        user.setPassword("msanchez");
        ResponseEntity response = userController.login(user);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void login_fail_nopassword() {
        UserCredentialsDTO user = new UserCredentialsDTO();
        user.setUsername("msanchez");
        ResponseEntity response = userController.login(user);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void login_fail_nouser() {
        UserCredentialsDTO user = new UserCredentialsDTO();
        user.setUsername("prodriguez");
        user.setPassword("1234");
        ResponseEntity response = userController.login(user);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
