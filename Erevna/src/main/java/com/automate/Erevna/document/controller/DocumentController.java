package com.automate.Erevna.document.controller;

import com.automate.Erevna.document.dto.response.UploadResponse;
import com.automate.Erevna.document.entity.Document;
import com.automate.Erevna.document.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.InvalidKeyException;


@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private  final DocumentService documentService;

    @PostMapping("/upload")
    public UploadResponse upload(@RequestParam("file")MultipartFile file) throws InvalidKeyException {

        Document document = documentService.uploadDocument(file);

        return UploadResponse.builder()
                .documentId(document.getId())
                .message("Document Uploaded Successfully")
                .build();


    }

}
