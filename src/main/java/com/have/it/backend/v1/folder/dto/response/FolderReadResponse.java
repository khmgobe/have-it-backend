package com.have.it.backend.v1.folder.dto.response;

import com.have.it.backend.v1.folder.domain.enumeration.Folder;
import com.have.it.backend.v1.folder.domain.enumeration.FolderPermission;
import lombok.Builder;

@Builder
public record FolderReadResponse (Long id, String title, String description, FolderPermission folderPermission) {


    public static FolderReadResponse toResponse(Folder folder) {

        return FolderReadResponse
                .builder()
                .id(folder.getId())
                .title(folder.getTitle())
                .description(folder.getDescription())
                .folderPermission(folder.getFolderPermission())
                .build();
    }
}