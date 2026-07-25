package com.automate.Erevna.chat.service;

import com.automate.Erevna.embedding.EmbeddingService;
import com.automate.Erevna.vector.VectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrievalService {

    private final EmbeddingService embeddingService;
    private final VectorRepository vectorRepository;

    public List<String> retrieveRelevantChunks(String question, int topK){

        float[] queryEmbedding = embeddingService.generateEmbedding(question);

        return vectorRepository.findRelevantChunks(queryEmbedding, topK);
    }
}
