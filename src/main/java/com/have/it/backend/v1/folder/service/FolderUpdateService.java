package com.have.it.backend.v1.folder.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.folder.domain.Folder;
import com.have.it.backend.v1.folder.dto.request.FolderUpdateRequest;
import com.have.it.backend.v1.folder.repository.FolderJpaRepository;
import com.have.it.backend.v1.folder.service.usecase.FolderUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FolderUpdateService implements FolderUpdateUseCase {

    private final FolderJpaRepository folderJpaRepository;

    @Override
    public void updateFolder(final Long folderId, final FolderUpdateRequest request) {

        final Folder findFolder =
                folderJpaRepository
                        .findById(folderId)
                        .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        findFolder.updateFolder(request.title(), request.description(), request.folderPermission());
    }
}
