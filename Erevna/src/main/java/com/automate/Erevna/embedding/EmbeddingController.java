package com.automate.Erevna.embedding;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/embedding/")
public class EmbeddingController {

    private final EmbeddingService embeddingService;

    @PostMapping("test")
    public int test(@RequestBody String text){

        float[] embedding = embeddingService.generateEmbedding(text);

        return embedding.length;

    }

}
