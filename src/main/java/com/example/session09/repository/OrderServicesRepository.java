package com.example.session09.repository;

import com.example.session09.model.entity.OrderServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderServicesRepository extends JpaRepository<OrderServices, Long> {
    List<OrderServices> findByReservationId(long reservationId);
}
