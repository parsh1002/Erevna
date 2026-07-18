package com.automate.Erevna.document.chunker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FixedSizeTextChunker {

    @Value("${document.chunk.size}")
    private int size = 1000;
    @Value("${document.chunk.overlap}")
    private  int overlap;


    public List<String> chunk(String text){

        if(text == null || text.isBlank()) return List.of();

        int start = 0;
        List<String> chunks = new ArrayList<>();

        while(start<text.length()){
            int end = Math.min(start + size, text.length());
            chunks.add(text.substring(start, end));
            start += size - overlap;

        }
        return chunks;

    }
}
