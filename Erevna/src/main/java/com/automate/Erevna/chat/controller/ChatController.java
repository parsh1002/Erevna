package com.automate.Erevna.chat.controller;

import com.automate.Erevna.chat.dto.request.ChatRequest;
import com.automate.Erevna.chat.dto.response.ChatResponse;
import com.automate.Erevna.chat.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat/")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatResponse chat(@Valid @RequestBody ChatRequest request){

        String question = chatService.chat(request.getQuestion());

        return new ChatResponse(question);
    }
}
