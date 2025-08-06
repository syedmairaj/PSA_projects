package com.durgasoft.Service;

import com.durgasoft.Entity.Bookings;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import org.springframework.stereotype.Service;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;


import java.io.IOException;

@Service
public class PDFGenerator {

    public String generatePDFInvoice(Bookings bookings) throws IOException{
//creating pdffilename with guestname
        String fileName ="Invoice_" + bookings.getGuestname()+ ".pdf";
  //storing pdf file in local system
        String filePath = "C:/Users/Ayaan/Desktop/2025/pdf/" + fileName;


        PdfWriter pdfWriter = new PdfWriter(filePath);
            PdfDocument pdfDoc = new PdfDocument(pdfWriter);
            Document doc = new Document(pdfDoc);
            doc.add(new Paragraph("Hotel Booking Invoice")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(18));
            doc.add(new Paragraph("Booking ID" + bookings.getId()));
            doc.add(new Paragraph("GuestName:" +bookings.getGuestname()));
            doc.add(new Paragraph("Email:" + bookings.getEmail()));
            doc.add(new Paragraph("Mobile:" + bookings.getMobile()));
            doc.add(new Paragraph("Booking Status:" +bookings.getBooking_status()));
            doc.add(new Paragraph("Room Type:" + bookings.getRoomsAvaliability().getRoomtype()));
            doc.add(new Paragraph("Check-in-Date:" + bookings.getRoomsAvaliability().getDate()));
            doc.add(new Paragraph("Price:" +bookings.getRoomsAvaliability().getPrice()));
            doc.close();
            return filePath;
        }






}
