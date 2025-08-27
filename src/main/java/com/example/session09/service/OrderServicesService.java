package com.example.session09.service;

import com.example.session09.model.dto.request.OrderServicesDTO;
import com.example.session09.model.entity.OrderServices;
import com.example.session09.model.entity.Reservation;
import com.example.session09.model.entity.RoomServices;
import com.example.session09.repository.OrderServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderServicesService {
    @Autowired
    private RoomServicesService roomServicesService;
    @Autowired
    private OrderServicesRepository orderServiceRepository;
    @Autowired
    private ReservationService reservationService;

    public OrderServices addOrderService(OrderServicesDTO orderServicesDTO) {
       OrderServices orderServices = new OrderServices();
       orderServices.setRoomServices(roomServicesService.findById(orderServicesDTO.getRoomServicesId()));
       orderServices.setReservation(reservationService.findById(orderServicesDTO.getReservationId()));
       orderServices.setCreatedDate(LocalDateTime.now());
       orderServices.setQuantity(orderServicesDTO.getQuantity());
       try {
           return orderServiceRepository.save(orderServices);
       }catch (Exception e){
           return null;
       }
    }

    public OrderServices findById(long id) {
        return orderServiceRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Order Service Not Found"));
    }

    public OrderServices editServiceOrder(Long id, OrderServicesDTO orderServicesDTO) {
        OrderServices orderServices = findById(id);
        if (orderServices != null) {
            orderServices.setRoomServices(roomServicesService.findById(orderServicesDTO.getRoomServicesId()));
            orderServices.setReservation(reservationService.findById(orderServicesDTO.getReservationId()));
            orderServices.setQuantity(orderServicesDTO.getQuantity());
            try {
                return orderServiceRepository.save(orderServices);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }

    public List<OrderServices> getAllServicesByReservationId(Long reservationId) {
        return orderServiceRepository.findByReservationId(reservationId);
    }
}