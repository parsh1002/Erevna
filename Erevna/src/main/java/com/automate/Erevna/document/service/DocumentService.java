package com.automate.Erevna.document.service;

import com.automate.Erevna.document.entity.Document;
import com.automate.Erevna.document.entity.DocumentStatus;
import com.automate.Erevna.document.extractor.PdfExtractor;
import com.automate.Erevna.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.InvalidKeyException;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final PdfExtractor pdfExtractor;

    public Document uploadDocument(MultipartFile file) throws InvalidKeyException {
        validate(file);

        String extractedText = pdfExtractor.extractText(file);

        Document document = Document.builder()
                .name(file.getOriginalFilename())
                .extractedText(extractedText)
                .status(DocumentStatus.PROCESSING)
                .build();

        return documentRepository.save(document);
    }
    private void validate(MultipartFile file){
        if(file == null || file.isEmpty()){
            throw new IllegalArgumentException("File cannot be empty");
        }
    }
}
