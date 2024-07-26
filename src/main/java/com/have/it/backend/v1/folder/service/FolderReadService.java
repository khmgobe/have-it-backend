package com.have.it.backend.v1.folder.service;

import com.have.it.backend.v1.common.util.BaseException;
import com.have.it.backend.v1.common.util.enumeration.ExceptionInformation;
import com.have.it.backend.v1.folder.domain.Folder;
import com.have.it.backend.v1.folder.dto.response.FolderReadResponse;
import com.have.it.backend.v1.folder.repository.FolderJpaRepository;
import com.have.it.backend.v1.folder.service.usecase.FolderReadUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FolderReadService implements FolderReadUseCase {

    private final FolderJpaRepository repository;

    @Override
    public FolderReadResponse findFolderById(final Long folderId) {

        final Folder folder =
                repository
                        .findById(folderId)
                        .orElseThrow(() -> new BaseException(ExceptionInformation.ID_NO_CONTENT));

        return FolderReadResponse.toResponse(folder);
    }

    @Override
    public List<FolderReadResponse> findAllFolder() {

        return repository.findAll().stream().map(FolderReadResponse::toResponse).toList();
    }
}
