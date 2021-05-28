/**
 * @projectName ZYF
 * @package com.example.zyf.controller
 * @className com.example.zyf.controller.Upload
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.controller;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.zyf.service.UploadService;

import io.lettuce.core.dynamic.annotation.Param;

/**
 * Upload
 * @description
 * @author zyf
 * @date 2020/12/28 15:50
 * @version 1.0
 */
@RestController
public class UploadController {

    @Resource
    private UploadService uploadService;
    @PostMapping("upload")
    public void upload(@RequestParam MultipartFile multipartFile){
        try {
            uploadService.load(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
