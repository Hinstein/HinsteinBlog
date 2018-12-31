package com.hinstein.blog.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hinstein.blog.bean.Article;
import com.hinstein.blog.bean.RecycleBin;
import com.hinstein.blog.service.ArticleService;
import com.hinstein.blog.service.RecycleBinService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.Controller
 * @Author: Hinstein
 * @CreateTime: 2018-11-24 16:48
 * @Description:
 */
@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    RecycleBinService recycleBinService;

//    @GetMapping(value = "/admin/articles")
//    public String list(Model model, HttpSession session) {
//        List<Article> articles = articleService.findAll();
//
////        for(Article article:articles)
////        {
////            System.out.println(article);
////        }
//        //放在请求域中
//        //倒序输出
//        Collections.reverse(articles);
//        model.addAttribute("articles", articles);
//        session.setAttribute("articlesSize", articles.size());
//        //thymeleaf默认就会自动拼串
//        return "/admin/article";
//    }

    @RequestMapping("/admin/articles")
    public String getFirstPage(Model model,HttpSession session) {

        return "redirect:/admin/articles/1";
    }


    /**
     * 文章分页
     * @param model
     * @param session
     * @param startPage
     * @return
     */
    @GetMapping("/admin/articles/{startPage}")
    public String getAllArticle(Model model,HttpSession session, @PathVariable(name="startPage") Integer startPage) {
        PageHelper.startPage(startPage, 5);
        List<Article> articles = articleService.findAll();
        PageInfo<Article> page = new PageInfo<>(articles);
        model.addAttribute("articles",articles);
        model.addAttribute("prePage",page.getPrePage());
        model.addAttribute("nextPage",page.getNextPage());
        model.addAttribute("maxPage",page.getPages());
        session.setAttribute("articlesSize", page.getTotal());
        return "/admin/article/article";
    }

    /**
     * 编写博客页面
     * @param model
     * @return
     */
    @GetMapping(value = "/admin/articleadd")
    public String toAddPage(Model model) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "/admin/article/articleadd";
    }


    /**
     * 添加博客
     * @param article
     * @return
     */
    @PostMapping(value = "/admin/articleadd")
    public String addArticles(Article article) {
        //SpringMvc 自动将请求参数和入参对象的属性进行一一绑定：要求参数名字和javabean入参的对象里面的属性名是一样的
        //来到博客列表页面
        article.setDate(new Date());
        article.setCount(0);
        article.setLikes(0);
        article.setViews(0);
        articleService.insert(article);

        //保存博客到dao
        //redirect:表示重定向到一个地址 /代表当前项目路径
        //forward:表示转发到一个地址
        return "redirect:/admin/articles/1";
    }

    /**
     * 来到修改页面，查出当前博客，在页面回显
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/admin/article/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Article article = articleService.findById(id);
        model.addAttribute("article", article);
        //页面要显示所有的部门列表
        return "/admin/article/articleedit";
    }

    /**
     * 博客修改:需要更新的博客id
     * @param article
     * @return
     */
    @PutMapping("/admin/articleadd")
    public String updateEmployee(Article article) {

        articleService.update(article);
        return "redirect:/admin/articles";
    }

    /**
     * 博客删除
     * @param id
     * @return
     */
    @DeleteMapping("/admin/article/{id}")
    public String deleteArticle(@PathVariable("id") Integer id) {
        RecycleBin recycleBin=new RecycleBin();
        Article article= articleService.findById(id);

        //将article对象复制到recycleBIn对象
        BeanUtils.copyProperties(article,recycleBin);

        recycleBinService.recycleBinInsert(recycleBin);

        articleService.delete(id);
        return "redirect:/admin/articles";
    }
}
