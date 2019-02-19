package com.hinstein.blog.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hinstein.blog.bean.Article;
import com.hinstein.blog.bean.Contact;
import com.hinstein.blog.service.ArticleService;
import com.hinstein.blog.service.ContactService;
import com.sun.deploy.net.HttpResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
 * @CreateTime: 2018-11-26 21:00
 * @Description:
 */
@Controller
public class BlogController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ContactService contactService;

    @GetMapping(value = {"/blog", "/"})
    public String list() {
        return "redirect:/blog/1";
    }

    /**
     * 来到第一页博客
     *
     * @param model
     * @param startPage
     * @return
     */
    @GetMapping("/blog/{startPage}")
    public String getAllUser(Model model, @PathVariable(name = "startPage") Integer startPage) {

        PageHelper.startPage(startPage, 5);
        List<Article> articles = articleService.findAll();
        PageInfo<Article> page = new PageInfo<>(articles);
        model.addAttribute("articles", articles);
        model.addAttribute("prePage", page.getPrePage());
        model.addAttribute("nextPage", page.getNextPage());
        model.addAttribute("maxPage", page.getPages());
        return "blog/index";
    }

    /**
     * 找到博客内容
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/blog/post/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Article article = articleService.findById(id);
        articleService.pageViews(id);
        model.addAttribute("article", article);
        //页面要显示所有的部门列表
        return "blog/post";
    }

    /**
     * 博客点赞，异步提交
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/blog/post/{id}")
    public String goodJob(@PathVariable("id") int id) {
        articleService.goodJbo(id);
//        return "redirect:/blog/post/" + id;
        return "1";
    }

    /**
     * 得到博客内容
     *
     * @return
     */
    @GetMapping("/blog/contact")
    public String returnContact() {
        return "blog/contact";
    }


    /**
     * 提交建议
     *
     * @param contact
     * @return
     */
    @ResponseBody
    @PostMapping("/blog/contact/submit")
    public Map<String, String> contact(Contact contact) {
        Map<String, String> res = new HashMap<>();
        res.put("msg", "xxx");
        res.put("err", "xxx");
        contact.setDate(new Date());
        contactService.insert(contact);
        return res;
    }


}
