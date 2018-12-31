package com.hinstein.blog.service;

import com.hinstein.blog.bean.Admin;
import com.hinstein.blog.bean.Article;
import com.hinstein.blog.mapper.AdminMapper;
import com.hinstein.blog.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.service
 * @Author: Hinstein
 * @CreateTime: 2018-11-24 17:11
 * @Description:
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public List<Article> findAll() {
        return articleMapper.findAll();
    }

    public Article findById(int id) {
        return articleMapper.findById(id);
    }

    public int insert(Article article) {
        return articleMapper.insert(article);
    }

    public int update(Article article) {
        return articleMapper.update(article);
    }

    public int delete(int id) {
        return articleMapper.delete(id);
    }

    public int pageViews(int id) {
        return articleMapper.pageViews(id);
    }

    public int goodJbo(int id){
        return articleMapper.goodJob(id);
    }
}
