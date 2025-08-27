package com.example.session09.model.dto.request;

import com.example.session09.model.entity.Reservation;
import com.example.session09.model.entity.RoomServices;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderServicesDTO {
    private long reservationId;
    private long roomServicesId;
    private int quantity;
}
