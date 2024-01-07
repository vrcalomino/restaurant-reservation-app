package com.restaurant.reservation.model;

import jakarta.persistence.*;
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
    @OneToMany
    private List<Reservation> reservations;
}
