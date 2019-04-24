package com.newer.mall.admin.commodity.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.newer.mall.common.utils.ImageUtil;

@Service
public class ImageService {
	
	public String upload(MultipartFile image) {
        int dotPos = image.getOriginalFilename().lastIndexOf(".");
        if (dotPos < 0) {
            //throw new MyException(ResultEnum.IMAGE_FORMAT_ERROR);
        	throw new RuntimeException();
        }
        String imageExt = image.getOriginalFilename().substring(dotPos + 1).toLowerCase();
        if (!ImageUtil.isImageAllowed(imageExt)) {
            //throw new MyException(ResultEnum.IMAGE_FORMAT_ERROR);
        	throw new RuntimeException();
        }
        String imageName = UUID.randomUUID().toString().replace("-", "") + "." + imageExt;
        try {
            Files.copy(image.getInputStream(), new File(ImageUtil.IMAGE_DIR + imageName).toPath(), StandardCopyOption.REPLACE_EXISTING);
            return imageName;
        } catch (IOException e) {
            System.err.println("本地图片上传失败：" + e.getMessage());
            //throw new MyException(ResultEnum.FAILED_UPLOAD);
        	throw new RuntimeException();
        }
    }

}
