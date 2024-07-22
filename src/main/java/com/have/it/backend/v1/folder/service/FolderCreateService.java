package com.have.it.backend.v1.folder.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.folder.domain.enumeration.Folder;
import com.have.it.backend.v1.folder.dto.request.FolderCreateRequest;
import com.have.it.backend.v1.folder.repository.FolderJpaRepository;
import com.have.it.backend.v1.folder.service.usecase.FolderCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FolderCreateService implements FolderCreateUseCase {

    private final FolderJpaRepository folderJpaRepository;

    @Override
    public void registerFolder(final FolderCreateRequest request) {

        if(folderJpaRepository.existsByTitle(request.title())) {
            throw new BaseException(ExceptionInformation.FOLDER_TITLE_DUPLICATED);
        }

        final Folder folder = FolderCreateRequest.fromCreate(request.title(), request.description(), request.folderPermission());

        folderJpaRepository.save(folder);
    }
}
