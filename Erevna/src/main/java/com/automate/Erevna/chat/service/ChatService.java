package com.automate.Erevna.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private static final int TOP_K = 5;
    private final RetrievalService retrievalService;
    private final PromptBuilder promptBuilder;
    private final ChatClient chatClient;

    public String chat(String question){

        var relevantChunks = retrievalService.retrieveRelevantChunks(question, TOP_K);

        String prompt = promptBuilder.buildPrompt(question, relevantChunks);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

}
