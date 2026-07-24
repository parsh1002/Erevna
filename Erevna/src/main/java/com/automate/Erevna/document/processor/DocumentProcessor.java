package com.automate.Erevna.document.processor;


import com.automate.Erevna.document.chunker.FixedSizeTextChunker;
import com.automate.Erevna.document.entity.Document;
import com.automate.Erevna.document.entity.DocumentChunk;
import com.automate.Erevna.document.entity.DocumentStatus;
import com.automate.Erevna.document.extractor.PdfExtractor;
import com.automate.Erevna.document.repository.DocumentChunkRepository;
import com.automate.Erevna.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DocumentProcessor {

    private final PdfExtractor pdfExtractor;
    private final FixedSizeTextChunker fixedSizeTextChunker;


    private final DocumentRepository documentRepository;
    private final DocumentChunkRepository documentChunkRepository;

    public void process(Document document, MultipartFile file){

        try {
            String extractedText = pdfExtractor.extractText(file);

            document.setExtractedText(extractedText);

            List<String> chunks = fixedSizeTextChunker.chunk(extractedText);

            List<DocumentChunk> documentChunks = new ArrayList<>();

            for(int i = 0; i<chunks.size(); i++){

                DocumentChunk chunk =  new DocumentChunk();

                chunk.setChunkIndex(i);
                chunk.setContent(chunks.get(i));
                chunk.setDocument(document);

                documentChunks.add(chunk);

            }
            documentChunkRepository.saveAll(documentChunks);
            document.setStatus(DocumentStatus.READY);

            documentRepository.save(document);
        } catch (Exception e) {
            document.setStatus(DocumentStatus.FAILED);
            documentRepository.save(document);

            throw e;
        }
    }



}
