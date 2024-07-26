package com.have.it.backend.v1.common.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class FileSaveService {

    private static final URI rootPath = URI.create("/Users/kimhyeongmin/Documents");

    public void save(String path, MultipartFile multipartFile) throws IOException {

        createDirectory(path);
        File file = new File(getPathString(path));
        multipartFile.transferTo(file);
        log.info("path: {}", file.getPath());
    }

    private String getPathString(String path) {
        return rootPath + path;
    }

    private Path getPath(String path) {
        return Paths.get(rootPath + path);
    }

    private void createDirectory(String fullPath) {

        final int value = fullPath.lastIndexOf("/");

        if (fullPath.lastIndexOf("/") > -1) {
            getPath(fullPath.substring(0, value)).toFile().mkdirs();
        }
    }
}
