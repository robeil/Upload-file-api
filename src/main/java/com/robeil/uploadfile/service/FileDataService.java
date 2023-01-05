package com.robeil.uploadfile.service;

import com.robeil.uploadfile.entity.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface FileDataService {
    Optional<FileData> findByFileName(String fileName);
    String uploadFile(MultipartFile file) throws IOException;
    byte[] downloadFile(String fileName);

}
