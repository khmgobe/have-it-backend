package com.have.it.backend.v1.folder.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.folder.dto.request.FolderUpdateRequest;
import com.have.it.backend.v1.folder.service.usecase.FolderUpdateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FolderUpdateApiController {

    private final FolderUpdateUseCase folderUpdateUseCase;

    @PatchMapping("/api/v1/folder/update/{folderId}")
    public ResponseEntity<BaseResponse<Void>> updateFolder(@PathVariable("folderId") Long folderId, @Valid @RequestBody FolderUpdateRequest folderUpdateRequest) {

        folderUpdateUseCase.updateFolder(folderId, folderUpdateRequest.toServiceRequest());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success());
    }
}
