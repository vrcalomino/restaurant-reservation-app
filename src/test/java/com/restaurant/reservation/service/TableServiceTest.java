package com.restaurant.reservation.service;

import com.restaurant.reservation.model.RestaurantTable;
import com.restaurant.reservation.repository.ITableRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TableServiceTest {

    @InjectMocks
    TableService tableService;

    @Mock
    ITableRepository tableRepo;

    @Test
    void testCreateTable() {
        RestaurantTable table = new RestaurantTable();
        table.setDescription("Nice table.");

        when(tableRepo.save(table)).thenReturn(table);

        RestaurantTable result = tableService.createTable(table);
        assertEquals(table, result);
        verify(tableRepo, times(1)).save(table);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        RestaurantTable table = new RestaurantTable();
        when(tableRepo.findById(id)).thenReturn(Optional.of(table));

        RestaurantTable result = tableService.findById(id);
        assertEquals(table, result);
        verify(tableRepo, times(1)).findById(id);
    }

    @Test
    public void testAddReservation() {
        RestaurantTable table = new RestaurantTable();
        when(tableRepo.save(table)).thenReturn(table);
        RestaurantTable result = tableService.addReservation(table);
        verify(tableRepo, times(1)).save(table);
        assertSame(table, result);
    }
}
