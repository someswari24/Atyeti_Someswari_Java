package com.example.carrental.util;

import com.example.carrental.model.Invoice;
import com.example.carrental.model.Payment;
import com.example.carrental.model.Rental;
import com.opencsv.CSVWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvExportUtil {
    public static ByteArrayInputStream invoicesToCsv(List<Invoice> invoices) {
        String[] headers = {"Invoice ID", "Rental ID", "Amount", "Generated At", "File Path"};
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (CSVWriter writer = new CSVWriter(
                new OutputStreamWriter(out, StandardCharsets.UTF_8),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {

            writer.writeNext(headers);

            for (Invoice invoice : invoices) {
                writer.writeNext(new String[]{
                        invoice.getId() != null ? invoice.getId().toString() : "",
                        invoice.getRental() != null ? invoice.getRental().getId().toString() : "",
                        invoice.getRental().getTotalCost()
                                != null ? invoice.getRental().getTotalCost().toString() : "",
                        invoice.getGeneratedDate() != null ? invoice.getGeneratedDate().toString() : "",
                        invoice.getFilePath() != null ? invoice.getFilePath() : ""
                });
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while exporting invoices to CSV", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream paymentsToCsv(List<Payment> payments) {
        String[] headers = {"Payment ID", "Rental ID", "Amount", "Method", "Status"};
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (CSVWriter writer = new CSVWriter(
                new OutputStreamWriter(out, StandardCharsets.UTF_8),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {

            writer.writeNext(headers);

            for (Payment payment : payments) {
                writer.writeNext(new String[]{
                        payment.getId() != null ? payment.getId().toString() : "",
                        payment.getRental() != null ? payment.getRental().getId().toString() : "",
                        payment.getAmount() != null ? payment.getAmount().toString() : "",
                        payment.getMethod() != null ? payment.getMethod() : "",
                        payment.getStatus() != null ? payment.getStatus().toString() : ""
                });
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while exporting payments to CSV", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream rentalsToCsv(List<Rental> rentals) {
        String[] headers = {"Rental ID", "User ID", "Car ID", "Start Date", "End Date", "Status"};
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (CSVWriter writer = new CSVWriter(
                new OutputStreamWriter(out, StandardCharsets.UTF_8),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {

            writer.writeNext(headers);

            for (Rental rental : rentals) {
                writer.writeNext(new String[]{
                        rental.getId() != null ? rental.getId().toString() : "",
                        rental.getUser() != null ? rental.getUser().getId().toString() : "",
                        rental.getCar() != null ? rental.getCar().getId().toString() : "",
                        rental.getStartDate() != null ? rental.getStartDate().toString() : "",
                        rental.getEndDate() != null ? rental.getEndDate().toString() : "",
                        rental.getStatus() != null ? rental.getStatus().toString() : ""
                });
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while exporting rentals to CSV", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
