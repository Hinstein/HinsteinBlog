package com.hinstein.blog.Controller;

import com.hinstein.blog.bean.Admin;
import com.hinstein.blog.bean.Article;
import com.hinstein.blog.bean.Contact;
import com.hinstein.blog.bean.RecycleBin;
import com.hinstein.blog.service.AdminService;
import com.hinstein.blog.service.ArticleService;
import com.hinstein.blog.service.ContactService;
import com.hinstein.blog.service.RecycleBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.Controller
 * @Author: Hinstein
 * @CreateTime: 2018-11-17 22:20
 * @Description:
 */
@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private RecycleBinService recycleBinService;

    /**
     * 登录请求
     *
     * @param username
     * @param password
     * @param map
     * @param session
     * @param model
     * @return
     */
    @PostMapping(value = "/admin/login/submit")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session, Model model) {
        Admin admin = adminService.findByUserName(username);
        //判读是否有该管理员账号
        if (admin != null) {
            //判断密码是否正确
            if (admin.getPassword().equals(password)) {
                //添加session
                session.setAttribute("loginUser", username);
                int likes = 0;
                int views = 0;
                Collection<Article> articles = articleService.findAll();
                Collection<Contact> contacts = contactService.findAll();
                Collection<RecycleBin> recycleBin = recycleBinService.recycleBinFindAll();
                for (Article article : articles) {
                    likes = likes + article.getLikes();
                    views = views + article.getViews();
                }

                //放在请求域中
                session.setAttribute("articlesSize", articles.size());
                session.setAttribute("contactsSize", contacts.size());
                session.setAttribute("recycleBinSize", recycleBin.size());
                session.setAttribute("articlesLikes", likes);
                session.setAttribute("articlesViews", views);
                //登录成功
                //防止重复提交表单，可以重定向到主页
                return "redirect:/admin/main.html";
            } else {
                map.put("msg", "管理员密码错误");
                return "admin/login";
            }
        } else {
            map.put("msg", "无该后台管理员");
        }
        return "admin/login";
    }
}
