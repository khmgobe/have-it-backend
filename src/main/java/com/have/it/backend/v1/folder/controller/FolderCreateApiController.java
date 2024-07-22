package com.have.it.backend.v1.folder.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.folder.dto.request.FolderCreateRequest;
import com.have.it.backend.v1.folder.service.usecase.FolderCreateUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "FOLDER-CREATE", description = "폴더 생성 관련 API ")
public class FolderCreateApiController {

    private final FolderCreateUseCase folderCreateUseCase;

    @PostMapping(path = "/api/v1/folder/register")
    public ResponseEntity<BaseResponse<Void>> register(@Valid @RequestBody FolderCreateRequest request) {
            folderCreateUseCase.registerFolder(request.toServiceRequest());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.created());
    }
}
