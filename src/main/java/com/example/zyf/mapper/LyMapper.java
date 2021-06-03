/**
 * @projectName zyf
 * @package com.example.zyf.mapper
 * @className com.example.zyf.mapper.LyMapper
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.mapper;

import com.example.zyf.DTO.ArticleDTO;
import com.example.zyf.model.Article;
import com.example.zyf.model.Ly;

import java.util.List;

/**
 *
 * LyMapper
 *
 * @description 留言
 * @author zyf
 * @date 2021/6/3 10:45
 * @version 1.0
 */
public interface LyMapper {
    List<Ly> selectLy(Ly ly);
    int deleteLy(String bh);
    int updateLy(Ly ly);
    int insertLy(Ly ly);
}