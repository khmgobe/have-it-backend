package com.have.it.backend.v1.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileSaveService fileSaveService;

    public void upload(String path, List<MultipartFile> files) throws IOException {

        for(MultipartFile file : files) {
            fileSaveService.save(String.format("/have-it/%s/%s/%s", new SimpleDateFormat("yyyyMMdd").format(new Date()), path, file.getOriginalFilename()), file);
        }
    }
}
