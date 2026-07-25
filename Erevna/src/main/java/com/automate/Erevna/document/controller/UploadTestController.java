package com.automate.Erevna.document.controller;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadTestController {

    @PostMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping(
            value = "/upload-test",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String upload(@RequestParam("file") MultipartFile file) {
        return file.getOriginalFilename();
    }
}