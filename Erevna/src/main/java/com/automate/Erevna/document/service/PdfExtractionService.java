package com.automate.Erevna.document.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PdfExtractionService {

    public String extractText(MultipartFile file){
        try(PDDocument document = Loader.loadPDF(file.getBytes())) {

            PDFTextStripper textStripper = new PDFTextStripper();

            return textStripper.getText(document);

        } catch (IOException e) {
            throw new RuntimeException("Failed to Extract PDF File... ", e);
        }

    }
}
