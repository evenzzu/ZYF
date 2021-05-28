/**
 * @projectName ZYF
 * @package com.example.zyf.service
 * @className com.example.zyf.service.UploadService
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zyf.model.User;
import com.google.gson.Gson;

import io.netty.handler.codec.json.JsonObjectDecoder;

/**
 * UploadService
 * @description 下载
 * @author zyf
 * @date 2020/12/28 15:52
 * @version 1.0
 */
@Service
public class UploadService {
    public void load(MultipartFile m) throws Exception {
        UUID uuid = UUID.randomUUID();
        File file = new File("D:\\Project\\ZYF\\src\\main\\resources\\templates\\"+uuid+"java.txt");
        System.out.println(file);
        m.transferTo(file);
        String name = m.getName();
        System.out.println(name);
        String contentType = m.getContentType();
        System.out.println("类型:"+contentType);
        String originalFilename = m.getOriginalFilename();
        System.out.println("originalFilename:"+originalFilename);
        long size = m.getSize();
        System.out.println("size:"+size);
        boolean empty = m.isEmpty();
        System.out.println("empty:"+empty);
        System.out.println("=====================================");
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        String s="";
        int len = 0;
        while ((len = inputStream.read(bytes)) != -1){
            s += new String(bytes);
//            System.out.println(new String(bytes));
        }
        System.out.println(s);
        User user = new User();
        JSONObject jsonObject = new JSONObject();
        Gson gson = new Gson();
        String s1 = gson.toJson(s);
        User user1 = gson.fromJson(s1,User.class);
        System.out.println(user1);
    }


}
