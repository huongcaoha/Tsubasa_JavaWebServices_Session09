package com.example.session09.model.dto.response;

import com.example.session09.model.constant.Status;
import com.example.session09.model.entity.Customer;
import com.example.session09.model.entity.Room;
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
public class ReservationResponse {
    private Long id;
    private String customerName;
    private String roomName;
    private Status status;
}
