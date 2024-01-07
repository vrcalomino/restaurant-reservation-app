package com.restaurant.reservation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservation_id;
    @ManyToOne
    private RestaurantTable table;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "UTC-3")
    private Date reservationDate;
    @OneToOne
    private User user;
}
