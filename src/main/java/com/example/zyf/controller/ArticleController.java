/**
 * @projectName ZYF
 * @package com.example.zyf.controller
 * @className com.example.zyf.controller.ArticleController
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.controller;

import com.example.zyf.DTO.ArticleDTO;
import com.example.zyf.model.Article;
import com.example.zyf.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 *
 * ArticleController
 *
 * @description ArticleController
 * @author zyf
 * @date 2021/2/26 17:59
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/articleList")
public class ArticleController {
    @Resource
    private ArticleService articleService;


    @GetMapping(value = "/Article")
    private void getArticle(ArticleDTO articleDTO) {
//        articleDTO.setBt("完美");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 3, 16, 18, 30);
        articleDTO.setKsrq(localDateTime);
        articleDTO.setJsrq(LocalDateTime.now());
        List<Article> articles = articleService.selectArticle(articleDTO);
        System.out.println(articles);
    }
    @PostMapping(value = "/Article")
    private int insertArticle(Article article) {
        article.setBh(UUID.randomUUID().toString().replaceAll("-", "").substring(0,31));
        article.setBq("军事");
        article.setBt("叙利亚战争");
        article.setNr("美国入侵西利亚");
        article.setZz("白岩松");
        article.setFbsj(LocalDateTime.now());
        return articleService.insertArticle(article);
    }
    @PutMapping(value = "/Article")
    private int updateArticle(ArticleDTO articleDTO) {
        articleDTO.setBh("1");
        articleDTO.setBt("完美");
        return articleService.updateArticle(articleDTO);
    }
    @DeleteMapping(value = "/Article")
    private int deleteArticle(String aid) {
        aid = "2";
       return articleService.deleteArticle(aid);
    }
}
