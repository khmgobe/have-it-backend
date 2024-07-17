package com.have.it.backend.v1.folder.service.usecase;

import com.have.it.backend.v1.folder.dto.request.FolderCreateRequest;

public interface FolderCreateUseCase {

    void register(FolderCreateRequest serviceRequest);
}
