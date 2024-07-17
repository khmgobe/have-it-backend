package com.have.it.backend.v1.folder.repository;

import com.have.it.backend.v1.folder.domain.enumeration.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderJpaRepository extends JpaRepository<Folder, Long> {

    boolean existsByTitle(String title);
}
