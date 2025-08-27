package com.example.session09.repository;

import com.example.session09.model.entity.RoomServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomServiceRepository extends JpaRepository<RoomServices, Long> {
}
