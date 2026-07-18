package com.automate.Erevna.document.processor;


import com.automate.Erevna.document.entity.Document;
import com.automate.Erevna.document.entity.DocumentStatus;
import com.automate.Erevna.document.extractor.PdfExtractor;
import com.automate.Erevna.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class DocumentProcessor {

    private final PdfExtractor pdfExtractor;

    private final DocumentRepository documentRepository;

    public void process(Document document, MultipartFile file){

        document.setStatus(DocumentStatus.PROCESSING);
        documentRepository.save(document);

        /*Even though the process is very fast and we dont need this first save
        but in production level systems if the extraction of text takes about a few to many
        seconds so it must show PORCESSING... in the frontend hence this */

        String extractedText = pdfExtractor.extractText(file);

        document.setExtractedText(extractedText);
        document.setStatus(DocumentStatus.READY);

        documentRepository.save(document);
    }



}
