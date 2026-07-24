package com.automate.Erevna.document.service;

import com.automate.Erevna.document.entity.Document;
import com.automate.Erevna.document.entity.DocumentStatus;
import com.automate.Erevna.document.processor.DocumentProcessor;
import com.automate.Erevna.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentProcessor documentProcessor;

    public Document uploadDocument(MultipartFile file) {
        validate(file);


        Document document = Document.builder()
                .name(file.getOriginalFilename())
                .status(DocumentStatus.PROCESSING)
                .build();

        document =  documentRepository.save(document);

        documentProcessor.process(document, file);
        return document;
    }
    private void validate(MultipartFile file){
        if(file == null || file.isEmpty()){
            throw new IllegalArgumentException("File cannot be empty");
        }
    }
}
