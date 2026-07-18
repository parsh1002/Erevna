package com.automate.Erevna.document.service;


import com.automate.Erevna.document.entity.Document;
import com.automate.Erevna.document.entity.DocumentChunk;
import com.automate.Erevna.document.repository.DocumentChunkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentChunkService {

    private final DocumentChunkRepository documentChunkRepository;

    public void saveChunks(Document document, List<String> chunks){

        List<DocumentChunk> entities = new ArrayList<>();

        for(int i = 0; i<chunks.size(); i++){

            DocumentChunk chunk =  new DocumentChunk();

            chunk.setChunkIndex(i);
            chunk.setContent(chunks.get(i));
            chunk.setDocument(document);

            entities.add(chunk);

        }
        documentChunkRepository.saveAll(entities);
    }
}
