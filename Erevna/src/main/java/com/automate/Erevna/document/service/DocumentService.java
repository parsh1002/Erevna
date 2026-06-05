package com.automate.Erevna.document.service;

import com.automate.Erevna.document.entity.Document;
import com.automate.Erevna.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final PdfExtractionService pdfExtractionService;

    public Document uploadDocument(MultipartFile file){

        String extractedText = pdfExtractionService.extractText(file);

        Document document = Document.builder()
                .name(file.getOriginalFilename())
                .extractedText(extractedText)
                .status("UPLOADED")
                .createdAt(LocalDateTime.now())
                .build();

        return documentRepository.save(document);
    }
}
