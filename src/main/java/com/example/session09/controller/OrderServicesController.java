package com.example.session09.controller;

import com.example.session09.model.dto.request.OrderServicesDTO;
import com.example.session09.model.entity.OrderServices;
import com.example.session09.service.OrderServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderServices")
public class OrderServicesController {
    @Autowired
    private OrderServicesService orderServicesService;

    @GetMapping("/{reservationId}")
    public ResponseEntity<?> getAllServicesByReservationId(@PathVariable Long reservationId) {
       return orderServicesService.getAllServicesByReservationId(reservationId);
    }

    @PostMapping("/order")
    public ResponseEntity<?> addOrderService(@RequestBody OrderServicesDTO orderServicesDTO) {
        return orderServicesService.addOrderService(orderServicesDTO);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editOrderService(@PathVariable Long id, @RequestBody OrderServicesDTO orderServicesDTO) {
        ResponseEntity<?> response = orderServicesService.editServiceOrder(id, orderServicesDTO);
        return response ;
    }
}
