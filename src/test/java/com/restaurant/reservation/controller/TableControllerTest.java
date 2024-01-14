package com.restaurant.reservation.controller;

import com.restaurant.reservation.model.RestaurantTable;
import com.restaurant.reservation.model.User;
import com.restaurant.reservation.service.ITableService;
import com.restaurant.reservation.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TableControllerTest {

    @InjectMocks
    TableController tableController;
    @Mock
    IUserService userService;

    @Test
    void create_success() throws Exception {
        User adminUser = new User(1L, "vcalomi", "Valentin", "Calomino", "1234", true, null);

        when(userService.registerUser(adminUser)).thenReturn(adminUser);
        when(userService.findById(adminUser.getUser_id())).thenReturn(adminUser);

        RestaurantTable table = new RestaurantTable();
        table.setDescription("Nice table next to the door.");

        try {
            User user = userService.registerUser(adminUser);
            ResponseEntity response = tableController.tableCreation(table, user.getUser_id());
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    void create_fail_notable() throws Exception {
        User adminUser = new User(1L, "vcalomi", "Valentin", "Calomino", "1234", true, null);

        // Mocking userService behavior
        when(userService.registerUser(adminUser)).thenReturn(adminUser);


        try {
            User user = userService.registerUser(adminUser);
            ResponseEntity response = tableController.tableCreation(null, user.getUser_id());
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void create_fail_nodescription() throws Exception {
        User adminUser = new User(1L, "vcalomi", "Valentin", "Calomino", "1234", true, null);

        when(userService.registerUser(adminUser)).thenReturn(adminUser);

        RestaurantTable table = new RestaurantTable();
        try {

            User user = userService.registerUser(adminUser);
            ResponseEntity response = tableController.tableCreation(table, user.getUser_id());
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
