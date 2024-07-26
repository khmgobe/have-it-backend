package com.have.it.backend.v1.folder.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.folder.dto.response.FolderReadResponse;
import com.have.it.backend.v1.folder.service.usecase.FolderReadUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FolderReadApiController {

    private final FolderReadUseCase folderReadUseCase;

    @GetMapping("/api/v1/folder/read/{folderId}")
    public ResponseEntity<BaseResponse<FolderReadResponse>> findFolder(@PathVariable Long folderId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.success(folderReadUseCase.findFolderById(folderId)));
    }

    @GetMapping("/api/v1/folder/read")
    public ResponseEntity<BaseResponse<List<FolderReadResponse>>> findAllFolders() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.success(folderReadUseCase.findAllFolder()));
    }
}
