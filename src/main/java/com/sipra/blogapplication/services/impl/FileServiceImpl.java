package com.sipra.blogapplication.services.impl;

import com.sipra.blogapplication.config.AppConstants;
import com.sipra.blogapplication.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(MultipartFile multipartFile) throws IOException {

            File dirPath = new File(AppConstants.FILE_PATH);
            if (!dirPath.exists())
                dirPath.mkdirs();
            String fileName = UUID.randomUUID().toString().concat("_").concat(multipartFile.getOriginalFilename());
            String filePath = AppConstants.FILE_PATH.concat(File.separator.concat(fileName));
            File file = new File(filePath);
            multipartFile.transferTo(file);
            return fileName;
    }

    @Override
    public InputStream getResource(String filename) throws FileNotFoundException {
            String filePath = AppConstants.FILE_PATH.concat(File.separator.concat(filename));
        return new FileInputStream(filePath);
    }
}
