/**
 * @projectName ZYF
 * @package com.example.zyf.model
 * @className com.example.zyf.model.Article
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 *
 * Article
 *
 * @description 文章实体类
 * @author zyf
 * @date 2021/2/26 17:13
 * @version 1.0
 */
@Data
@Component
public class Article extends BaseEntity{

    @ApiModelProperty(value = "文章标题")
    private String bt;

    @ApiModelProperty(value = "文章标签")
    private String bq;

    @ApiModelProperty(value = "文章内容")
    private String nr;

    @ApiModelProperty(value = "文章描述")
    private String ms;

    @ApiModelProperty(value = "文章作者")
    private String zz;

    @ApiModelProperty(value = "发布时间")
    private LocalDateTime fbsj;
}
