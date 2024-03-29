package com.damian.ecommerce.backend.application;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadFileService {
    private final String FOLDER = "src//main//resources//static//images";
    private final String IMG_DEFAULT_NAME = "default.jpg";
    private final String URL_IMAGE = "http://localhost:8085/images/";

    public String uploadImage(MultipartFile multipartFile) throws IOException {
        if (multipartFile != null){
            byte [] bytes = multipartFile.getBytes();
            Path path = Paths.get(FOLDER+multipartFile.getOriginalFilename());
            Files.write(path, bytes);
            return URL_IMAGE + multipartFile.getOriginalFilename();
        }
        return URL_IMAGE + IMG_DEFAULT_NAME;
    }

    public void deleteImage(String imageName){
        File file = new File(FOLDER + imageName);
        file.delete();
    }

}
