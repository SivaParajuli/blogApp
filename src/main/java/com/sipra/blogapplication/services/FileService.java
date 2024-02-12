package com.sipra.blogapplication.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String uploadImage(MultipartFile file) throws IOException;
    InputStream getResource(String filename) throws FileNotFoundException;
}
