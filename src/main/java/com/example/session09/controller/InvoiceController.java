package com.example.session09.controller;

import com.example.session09.model.entity.Invoice;
import com.example.session09.security.CustomerPrincipal;
import com.example.session09.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/pay/{reservationId}")
    public ResponseEntity<?> payInvoice(@PathVariable Long reservationId) {
        Invoice invoice = invoiceService.payInvoice(reservationId);
        if (invoice == null) {
            return new ResponseEntity<>("create invoice failed", HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(invoice, HttpStatus.CREATED);
        }
    }

    @GetMapping("/history")

    public ResponseEntity<List<Invoice>> getInvoiceHistoryByCustomerId(@AuthenticationPrincipal CustomerPrincipal customerPrincipal) {
        return new ResponseEntity<>(invoiceService.getInvoiceHistoryByCustomerId(customerPrincipal.getCustomer().getId()), HttpStatus.OK);
    }
}
