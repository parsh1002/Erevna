package com.automate.Erevna.document.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TextChunkingService {
    private final int CHUNK_SIZE = 1000;
    private final int CHUNK_OVERLAP = 200;

    public List<String> textChunking(String text){

        int start = 0;
        List<String> chunks = new ArrayList<>();

        while(start<text.length()){
            int end = Math.min(start + CHUNK_SIZE, text.length());
            chunks.add(text.substring(start, end));
            start += CHUNK_SIZE - CHUNK_OVERLAP;

        }
        return chunks;

    }
}
