package com.example.carrental.controller;

import com.example.carrental.model.Invoice;
import com.example.carrental.model.Rental;
import com.example.carrental.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
@Slf4j
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Invoice> generateInvoice(@RequestBody Invoice invoice) {
        Invoice savedInvoice = invoiceService.generateInvoice(invoice);
        log.info("Invoice created with ID:"+savedInvoice.getId());
        return ResponseEntity.ok(savedInvoice);
    }

    @PostMapping("/by-rental")
    public ResponseEntity<Invoice> findByRental(@RequestBody Rental rental) {
        Optional<Invoice> invoice = invoiceService.findByRental(rental);
        return invoice.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("No invoice found for rental ID:"+ rental.getId());
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping("/invoicebetween")
    public ResponseEntity<List<Invoice>> findInvoicesBetween(
            @RequestParam("from") String from,
            @RequestParam("to") String to) {

        LocalDateTime fromDate = LocalDateTime.parse(from);
        LocalDateTime toDate = LocalDateTime.parse(to);
        List<Invoice> invoices = invoiceService.findInvoicesBetween(fromDate, toDate);

        if (invoices.isEmpty()) {
            log.warn("No invoices found between {} and {}", from, to);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/export/csv")
    public ResponseEntity<InputStreamResource> exportInvoicesToCsv() {
        ByteArrayInputStream csvStream = invoiceService.exportInvoicesToCsv();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=invoices.csv");

        log.info("Exporting invoices to CSV file");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(csvStream));
    }
}
