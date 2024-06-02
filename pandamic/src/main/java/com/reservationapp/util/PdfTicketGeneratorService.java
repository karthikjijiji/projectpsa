package com.reservationapp.util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.reservationapp.entity.Passenger;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfTicketGeneratorService {

    public byte[] generatePdf(Passenger passenger) {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            document.add(new Paragraph("Ticket Information"));
            document.add(new Paragraph("First Name: " + passenger.getFirstName()));
            document.add(new Paragraph("Last Name: " + passenger.getLastName()));
            document.add(new Paragraph("Email: " + passenger.getEmail()));
            document.add(new Paragraph("Mobile: " + passenger.getMobile()));
            document.add(new Paragraph("Bus ID: " + passenger.getBuId()));
            document.add(new Paragraph("Route ID: " + passenger.getRouteId()));
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
