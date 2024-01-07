package com.restaurant.reservation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long table_id;
    private String description;
    private List<String> reservation_ids;
}
