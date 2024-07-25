package com.have.it.backend.v1.common.controller;

import com.have.it.backend.v1.common.service.FileUploadService;
import com.have.it.backend.v1.common.util.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Tag(name = "FILE-UPLOAD", description = "파일 업로드 관련 API ")
public class FileUploadApiController {

    private final FileUploadService service;

    @PostMapping(
            path = "/api/v1/file/{path}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Void>> upload(
            @PathVariable String path, @RequestPart List<MultipartFile> files) throws IOException {

        service.upload(path, files);

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.created());
    }
}
