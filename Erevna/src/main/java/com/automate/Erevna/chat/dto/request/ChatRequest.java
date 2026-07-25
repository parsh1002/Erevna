package com.automate.Erevna.chat.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatRequest {

    @NotBlank(message = "Question cannot be empty")
    private String question;
}
