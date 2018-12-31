package com.hinstein.blog.Controller;

import com.hinstein.blog.bean.Article;
import com.hinstein.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.Controller
 * @Author: Hinstein
 * @CreateTime: 2018-12-22 10:54
 * @Description:
 */

@Controller
public class TimeLineController {

    @Autowired
    ArticleService articleService;

    /**
     * 得到所有文章并返回时间线页面
     * @param model
     * @return
     */
    @GetMapping("/admin/timeline")
    public String timeline(Model model){

        List<Article> articles=articleService.findAll();

        model.addAttribute("articles",articles);
        return "admin/timeline/timeline";
    }
}
