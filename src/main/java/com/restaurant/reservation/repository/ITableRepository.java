package com.restaurant.reservation.repository;

import com.restaurant.reservation.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITableRepository extends JpaRepository<RestaurantTable, Long> {
}
