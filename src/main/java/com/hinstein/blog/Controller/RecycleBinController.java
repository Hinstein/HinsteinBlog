package com.hinstein.blog.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hinstein.blog.bean.Article;
import com.hinstein.blog.bean.RecycleBin;
import com.hinstein.blog.service.ArticleService;
import com.hinstein.blog.service.RecycleBinService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.PublicKey;
import java.util.List;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.Controller
 * @Author: Hinstein
 * @CreateTime: 2018-12-18 10:18
 * @Description:
 */

@Controller
public class RecycleBinController {

    @Autowired
    RecycleBinService recycleBinService;

    @Autowired
    ArticleService articleService;

    /**
     * 得到第一页回收站的信息
     * @param model
     * @param session
     * @param startPage
     * @return
     */
    @GetMapping("/admin/recycleBin/{startPage}")
    public String getAllRecycleBin(Model model, HttpSession session, @PathVariable(name="startPage") Integer startPage) {
        PageHelper.startPage(startPage, 5);
        List<RecycleBin> recycleBins = recycleBinService.recycleBinFindAll();
        PageInfo<RecycleBin> page = new PageInfo<RecycleBin>(recycleBins);
        model.addAttribute("recycleBins",recycleBins);
        model.addAttribute("prePage",page.getPrePage());
        model.addAttribute("nextPage",page.getNextPage());
        model.addAttribute("maxPage",page.getPages());
        session.setAttribute("recycleBinSize", page.getTotal());
        return "admin/recycleBin/recycleBin";
    }


    /**
     * 查看
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/admin/recycleBin/content/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        RecycleBin recycleBin = recycleBinService.recycleBinFindById(id);
        model.addAttribute("recycleBin", recycleBin);

        return "/admin/recycleBin/content";
    }

    /**
     * 删除
     * @param id
     * @param session
     * @return
     */
    @DeleteMapping("/admin/recycleBin/{id}")
    public String  deletContact(@PathVariable("id") Integer id ,HttpSession session)
    {
        recycleBinService.recycleBinDelete(id);
        List<RecycleBin> recycleBins = recycleBinService.recycleBinFindAll();
        session.setAttribute("recycleBinSize", recycleBins.size());
        return "redirect:/admin/recycleBin/1";
    }

    /**
     * 恢复
     */
    @GetMapping("/admin/recycleBin/recover/{id}")
    public String recover(@PathVariable("id") Integer id)
    {
        Article article= new Article();
        RecycleBin recycleBin= recycleBinService.recycleBinFindById(id);

        //将recycleBin对象复制到article对象
        BeanUtils.copyProperties(recycleBin,article);

        articleService.insert(article);
        recycleBinService.recycleBinDelete(id);
        return "redirect:/admin/recycleBin/1";
    }
}
