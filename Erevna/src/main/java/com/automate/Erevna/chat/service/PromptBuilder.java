package com.automate.Erevna.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromptBuilder {

    public String buildPrompt(String question, List<String> chunks){

        String context = String.join("\n\n", chunks);

        return """
                You are Erevna, an AI assistant that answers questions using only the provided context.
                
                                Instructions:
                                - Answer only using the information from the context.
                                - If the answer cannot be found in the context, say:
                                  "I couldn't find that information in the provided documents."
                                - Do not make up facts.
                                - Keep your answers clear and concise.
                
                                Context:
                                %s
                
                                Question:
                                %s
                
                                Answer:
                """.formatted(context, question);
    }
}
