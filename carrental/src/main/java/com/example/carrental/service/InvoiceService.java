package com.example.carrental.service;

import com.example.carrental.model.Invoice;
import com.example.carrental.model.Rental;
import com.example.carrental.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public Invoice generateInvoice(Invoice invoice){
        log.info("Generating Invoice: "+invoice.getFilePath());
        return invoiceRepository.save(invoice);
    }

    public Optional<Invoice> findByRental(Rental rental){
        return invoiceRepository.findByRental(rental);
    }

    public List<Invoice> findInvoicesBetween(LocalDateTime from, LocalDateTime to){
        return invoiceRepository.findInvoicesBetween(from,to);
    }

    public ByteArrayInputStream exportInvoicesToCsv(){
        List<Invoice>invoices=invoiceRepository.findAll();
        return CsvExportUtil.invoicesToCsv(invoices);
    }
}
