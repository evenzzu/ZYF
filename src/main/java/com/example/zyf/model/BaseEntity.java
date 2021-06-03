/**
 * @projectName zyf
 * @package com.example.zyf.model
 * @className com.example.zyf.model.BaseEntity
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

/**
 *
 * BaseEntity
 *
 * @description 公共类
 * @author zyf
 * @date 2021/6/3 10:47
 * @version 1.0
 */
public class BaseEntity {
    @ApiModelProperty(value = "编号")
    private String bh = UUID.randomUUID().toString().replaceAll("-", "").substring(0,31);

}
