package com.have.it.backend.v1.folder.dto.request;

import com.have.it.backend.v1.folder.domain.enumeration.Folder;
import com.have.it.backend.v1.folder.domain.enumeration.FolderPermission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record FolderUpdateRequest(

        @NotBlank(message = "폴더 제목은 비어있을 수 없습니다.")
        String title,
        @NotBlank(message = "폴더 설명은 비어있을 수 없습니다.")
        String description,
        @NotNull(message = "폴더 권한은 비어있을 수 없습니다.")
        FolderPermission folderPermission){

    public FolderUpdateRequest toServiceRequest() {
        return FolderUpdateRequest
                .builder()
                .title(title)
                .description(description)
                .folderPermission(folderPermission)
                .build();
    }

    public static Folder fromCreate(String title, String description, FolderPermission folderPermission) {
        return Folder
                .builder()
                .title(title)
                .description(description)
                .folderPermission(folderPermission)
                .build();
    }
}
