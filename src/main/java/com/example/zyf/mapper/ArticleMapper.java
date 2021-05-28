/**
 * @projectName ZYF
 * @package com.example.zyf.mapper
 * @className com.example.zyf.mapper.ArticleMapper
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.mapper;

import com.example.zyf.DTO.ArticleDTO;
import com.example.zyf.model.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * ArticleMapper
 *
 * @description 文章描述
 * @author zyf
 * @date 2021/2/26 17:12
 * @version 1.0
 */
@Mapper
public interface ArticleMapper {
    List<Article> selectArticle(ArticleDTO articleDTO);
    int deleteArticle(String aid);
    int updateArticle(ArticleDTO articleDTO);
    int insertArticle(Article article);

}