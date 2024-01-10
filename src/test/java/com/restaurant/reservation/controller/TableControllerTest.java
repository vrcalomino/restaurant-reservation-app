package com.restaurant.reservation.controller;

import com.restaurant.reservation.model.RestaurantTable;
import com.restaurant.reservation.service.ITableService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;


@ExtendWith(MockitoExtension.class)
public class TableControllerTest {

    @InjectMocks
    TableController tableController;

    @Mock
    ITableService tableService;

    @Test
    void create_success() {
        RestaurantTable table = new RestaurantTable();
        table.setDescription("Nice table next to the door.");

        ResponseEntity response = tableController.tableCreation(table);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void create_fail_notable(){
        ResponseEntity response = tableController.tableCreation(null);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void create_fail_nodescription(){
        RestaurantTable table = new RestaurantTable();
        ResponseEntity response = tableController.tableCreation(table);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
