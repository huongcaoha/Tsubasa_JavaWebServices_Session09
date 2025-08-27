package com.example.session09.service;

import com.example.session09.model.entity.Invoice;
import com.example.session09.model.entity.OrderServices;
import com.example.session09.model.entity.Reservation;
import com.example.session09.model.entity.Room;
import com.example.session09.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private OrderServicesService orderServicesService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ReservationService reservationService; // Dịch vụ để tính toán hóa đơn

    public Invoice payInvoice(Long reservationId) {
        // Tính toán tổng tiền dựa trên dịch vụ và phòng đã đặt
        Reservation reservation = reservationService.findById(reservationId);
        List<OrderServices> orderServices = orderServicesService.getAllServicesByReservationId(reservationId);
        double totalPriceRoom = reservation.getRoom().getPrice();
        double totalPriceOrderService = orderServices.stream().map(ods -> ods.getRoomServices().getPrice() * ods.getQuantity())
                .reduce(0.0, Double::sum);
        double totalAmount = totalPriceRoom + totalPriceOrderService;

        Invoice invoice = new Invoice();
        invoice.setReservation(reservation);
        invoice.setTotalAmount(totalAmount);
        invoice.setCreatedDate(LocalDateTime.now());

        try {
          Invoice newInvoice =  invoiceRepository.save(invoice);
            Room room = reservation.getRoom();
            room.setReservation(null);
            roomService.save(room);
            return newInvoice;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public List<Invoice> getInvoiceHistoryByCustomerId(Long customerId) {
        return invoiceRepository.findByReservation_CustomerId(customerId);
    }
}
