package com.automate.Erevna.document.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UploadResponse {

    private UUID documentId;

    private String message;


}
