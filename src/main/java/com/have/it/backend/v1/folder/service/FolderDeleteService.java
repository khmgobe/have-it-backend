package com.have.it.backend.v1.folder.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.folder.repository.FolderJpaRepository;
import com.have.it.backend.v1.folder.service.usecase.FolderDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FolderDeleteService implements FolderDeleteUseCase {

    private final FolderJpaRepository folderJpaRepository;

    @Override
    public void deleteFolder(Long folderId) {

        folderJpaRepository
                .findById(folderId)
                .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        folderJpaRepository.deleteById(folderId);
    }
}
