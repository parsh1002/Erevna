package com.automate.Erevna.document.DtoResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadResponse {

    private Long documentId;

    private String message;


}
