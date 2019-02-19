package com.hinstein.blog.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hinstein.blog.bean.Contact;
import com.hinstein.blog.service.ContactService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.Controller
 * @Author: Hinstein
 * @CreateTime: 2018-11-29 20:39
 * @Description:
 */
@Controller
public class NotificationController {

    @Autowired
    ContactService contactService;

    /**
     * 来到留言列表第一页
     *
     * @param model
     * @param session
     * @param startPage
     * @return
     */
    @GetMapping("/admin/notifications/{startPage}")
    public String finAllNotifications(Model model, HttpSession session, @PathVariable(name = "startPage") Integer startPage) {
        PageHelper.startPage(startPage, 5);
        List<Contact> contacts = contactService.findAll();
        PageInfo<Contact> page = new PageInfo<>(contacts);
        model.addAttribute("contacts", contacts);
        model.addAttribute("prePage", page.getPrePage());
        model.addAttribute("nextPage", page.getNextPage());
        model.addAttribute("maxPage", page.getPages());
        session.setAttribute("contactsSize", page.getTotal());
        return "admin/notification/notifications";
    }

    /**
     * 查看
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/admin/contact/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Contact contact = contactService.findById(id);
        model.addAttribute("contact", contact);
        return "admin/notification/contact";
    }

    /**
     * 删除留言
     *
     * @param id
     * @param session
     * @return
     */
    @DeleteMapping("/admin/contact/{id}")
    public String deletContact(@PathVariable("id") Integer id, HttpSession session) {
        contactService.deleteContact(id);
        List<Contact> contacts = contactService.findAll();
        session.setAttribute("contactsSize", contacts.size());
        return "redirect:/admin/notifications/1";
    }
}
