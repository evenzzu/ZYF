/**
 * @projectName ZYF
 * @package model
 * @className model.User
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;

import lombok.Data;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

/**
 * User
 * @description
 * @author zyf
 * @date 2020/12/21 20:29
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "user")
@Validated // 验证@ConfigurationProperties 类
public class User implements Serializable {
    private String id ;
    private String username;
    private String password;
    private List<String> list;
    private List<Tests> tests;
    private Map<String,Tests> map;
    @DurationUnit(ChronoUnit.CENTURIES)
    private Duration sessionTime = Duration.ofSeconds(20);
    private Duration readTimeout = Duration.ofMillis(1000);
    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize msize = DataSize.ofMegabytes(2);
    private DataSize bsize = DataSize.ofBytes(200);



}
