package com.have.it.backend.v1.folder.controller;

import com.have.it.backend.v1.common.util.BaseResponse;
import com.have.it.backend.v1.folder.service.usecase.FolderDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FolderDeleteApiController {

    private final FolderDeleteUseCase folderDeleteUseCase;

    @DeleteMapping(path = "/api/v1/folder/{folderId}")
    public ResponseEntity<BaseResponse<Long>> deleteFolder(@PathVariable("folderId") Long folderId) {
        folderDeleteUseCase.deleteFolder(folderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(folderId));
    }
}
