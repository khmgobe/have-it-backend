package com.have.it.backend.v1.folder.service.usecase;

import com.have.it.backend.v1.folder.dto.response.FolderReadResponse;
import java.util.List;

public interface FolderReadUseCase {

    FolderReadResponse findFolderById(Long folderId);

    List<FolderReadResponse> findAllFolder();
}
