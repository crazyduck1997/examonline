package com.qf.examonline.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class MultioartFileUp {

    public static File upLoad(MultipartFile file,String path){
        UUID uuid = UUID.randomUUID();
        String originalFilename = file.getOriginalFilename();
        String fileName = uuid.toString().replace("-","");

        File dest = new File(path + fileName + originalFilename);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }

}
