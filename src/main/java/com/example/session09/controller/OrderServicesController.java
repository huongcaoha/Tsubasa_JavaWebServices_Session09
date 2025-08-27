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
    public ResponseEntity<List<OrderServices>> getAllServicesByReservationId(@PathVariable Long reservationId) {
        return new ResponseEntity<>(orderServicesService.getAllServicesByReservationId(reservationId), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<?> addOrderService(@RequestBody OrderServicesDTO orderServicesDTO) {
        OrderServices orderServices = orderServicesService.addOrderService(orderServicesDTO);
        if (orderServices != null) {
            return new ResponseEntity<>(orderServices, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("add order service failed !",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editOrderService(@PathVariable Long id, @RequestBody OrderServicesDTO orderServicesDTO) {
        OrderServices updateOrderServices = orderServicesService.editServiceOrder(id, orderServicesDTO);
        if (updateOrderServices != null) {
            return new ResponseEntity<>(updateOrderServices, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("edit order service failed !",HttpStatus.BAD_REQUEST);
        }
    }
}
