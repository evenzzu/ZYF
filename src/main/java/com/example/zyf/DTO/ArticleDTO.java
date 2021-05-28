/**
 * @projectName ZYF
 * @package com.example.zyf.DTO
 * @className com.example.zyf.DTO.ArticleDTO
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 * ArticleDTO
 *
 * @description 文章DTO
 * @author zyf
 * @date 2021/2/26 17:26
 * @version 1.0
 */
@Data
public class ArticleDTO {
    @ApiModelProperty(value = "发布时间")
    private LocalDateTime fbsj;

    @ApiModelProperty(value = "开始日期")
    private LocalDateTime ksrq;

    @ApiModelProperty(value = "结束日期")
    private LocalDateTime jsrq;

    @ApiModelProperty(value = "文章标签")
    private String bq;

    @ApiModelProperty(value = "文章标题")
    private String bt;

    @ApiModelProperty(value = "文章作者")
    private String zz;

    @ApiModelProperty(value = "文章内容")
    private String nr;

    @ApiModelProperty(value = "编号")
    private String bh;

}
