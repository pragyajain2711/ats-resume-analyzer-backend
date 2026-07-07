package com.pragya.ats_analyzer_backend.service;

import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfService {

    public String extractText(MultipartFile file) throws IOException {

        System.out.println("========== FILE DETAILS ==========");
        System.out.println("Name : " + file.getOriginalFilename());
        System.out.println("Size : " + file.getSize());
        System.out.println("Content Type : " + file.getContentType());
        System.out.println("==================================");

        if (file.isEmpty()) {
            throw new IOException("Uploaded file is empty.");
        }

        PDDocument document = Loader.loadPDF(file.getBytes());

        PDFTextStripper stripper = new PDFTextStripper();

        String text = stripper.getText(document);

        document.close();

        return text;
    }
}