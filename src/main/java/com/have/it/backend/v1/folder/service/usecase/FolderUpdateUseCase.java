package com.have.it.backend.v1.folder.service.usecase;

import com.have.it.backend.v1.folder.dto.request.FolderUpdateRequest;

public interface FolderUpdateUseCase {

    void updateFolder(Long folderId, FolderUpdateRequest serviceRequest);
}
