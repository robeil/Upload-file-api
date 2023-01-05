package com.robeil.uploadfile.service.impl;

import com.robeil.uploadfile.entity.FileData;
import com.robeil.uploadfile.repository.FileDataRepository;
import com.robeil.uploadfile.service.FileDataService;
import com.robeil.uploadfile.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileDataServiceImpl implements FileDataService {

    @Autowired
    private FileDataRepository fileDataRepository;
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .fileData(FileUtil.compressImage(file.getBytes())).build()
        );
        if(fileData !=null ){
            return "File uploaded successfully " + file.getOriginalFilename();
        }
        return null;
    }

    @Override
    public byte[] downloadFile(String fileName){
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        byte[] files =  FileUtil.decompressImage(fileData.get().getFileData());

        return files;
    }
    @Override
    public Optional<FileData> findByFileName(String fileName) {
        return Optional.empty();
    }
}
