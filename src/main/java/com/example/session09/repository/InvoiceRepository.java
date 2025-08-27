package com.example.session09.repository;

import com.example.session09.model.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByReservation_CustomerId(Long customerId);
}
