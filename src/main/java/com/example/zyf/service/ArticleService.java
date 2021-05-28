/**
 * @projectName ZYF
 * @package com.example.zyf.service
 * @className com.example.zyf.service.ArticleService
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.service;

import com.example.zyf.DTO.ArticleDTO;
import com.example.zyf.mapper.ArticleMapper;
import com.example.zyf.mapper.UserMapper;
import com.example.zyf.model.Article;
import com.example.zyf.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * ArticleService
 *
 * @description 文章Service
 * @author zyf
 * @date 2021/2/26 17:57
 * @version 1.0
 */
@Service
public class ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    public List<Article> selectArticle(ArticleDTO articleDTO){
        return articleMapper.selectArticle(articleDTO);
    }
    public int deleteArticle(String aid){
        return articleMapper.deleteArticle(aid);
    }
    public int updateArticle(ArticleDTO articleDTO){
        return articleMapper.updateArticle(articleDTO);
    }
    public int insertArticle(Article article){
        return articleMapper.insertArticle(article);
    }
}
