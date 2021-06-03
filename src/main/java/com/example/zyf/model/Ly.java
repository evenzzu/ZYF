/**
 * @projectName zyf
 * @package com.example.zyf.model
 * @className com.example.zyf.model.Ly
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.model;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * Ly
 *
 * @description 留言
 * @author zyf
 * @date 2021/6/3 10:46
 * @version 1.0
 */
public class Ly extends BaseEntity{

    @ApiModelProperty(value = "留言文章编号")
    private String articleBh;

    @ApiModelProperty(value = "留言内容")
    private String lyNr;

    @ApiModelProperty(value = "点赞数")
    private Integer dzs;

    @ApiModelProperty(value = "留言人")
    private String lyr;

    @ApiModelProperty(value = "留言时间")
    private LocalDateTime lySj;

    @ApiModelProperty(value = "回复编号")
    private List hfBhList;

}
