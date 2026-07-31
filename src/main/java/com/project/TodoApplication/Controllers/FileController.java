package com.project.TodoApplication.Controllers;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

@RestController
public class FileController {

    Logger logger = LoggerFactory.getLogger(FileController.class);
    @PostMapping("/singleupload")
    public String UploadSingleFile(@RequestParam("file")MultipartFile file) throws IOException {
        logger.info("File name{}",file.getName());
        logger.info("Original FIle name{}",file.getOriginalFilename());
        logger.info("File Type{}",file.getContentType());
        logger.info("File Size{}",file.getSize());
        InputStream input = file.getInputStream();
        FileOutputStream output = new FileOutputStream("RecievedIMAGE.jpg");
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }

        output.close();
        input.close();
        return "File Sent";
    }

    @PostMapping("/multipleupload")
    public String UploadMultipleFile(@RequestParam("images")MultipartFile[] files){
        Arrays.stream(files).forEach(file->{

            logger.info("FIle name : {}",file.getName());
            logger.info("File Size: {}",file.getSize());
            logger.info("File Original name{}",file.getOriginalFilename());
            logger.info("File Type:{}",file.getContentType());
            System.out.println("------------------------------------------------");

        });
        return "Handling Multiple FIles";
    }
}
