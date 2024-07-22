package com.have.it.backend.v1.folder.domain.enumeration;

import com.have.it.backend.v1.common.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Folder extends BaseTimeEntity {

    @Id
    @Column(name = "folder_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "folder_title", nullable = false)
    private String title;

    @Column(name = "folder_description", nullable = false)
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "folder_permission", nullable = false)
    FolderPermission folderPermission;

    @Builder
    private Folder(Long id, String title, String description, FolderPermission folderPermission) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.folderPermission = folderPermission;
    }

    public void updateFolder(String title, String description, FolderPermission folderPermission) {
            this.title = title;
        this.description = description;
        this.folderPermission = folderPermission;
    }
}
