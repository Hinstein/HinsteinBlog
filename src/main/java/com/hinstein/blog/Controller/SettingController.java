package com.hinstein.blog.Controller;

import com.hinstein.blog.bean.Admin;
import com.hinstein.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.Controller
 * @Author: Hinstein
 * @CreateTime: 2018-12-23 16:51
 * @Description:
 */
@Controller
public class SettingController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admin/settings")
    public String setting() {
        return "admin/settings/setting";
    }

    @ResponseBody
    @PostMapping("/admin/setting/changePassword")
    public Map<String, String> changePassword(@RequestParam("newPassword") String newPassword,
                                              @RequestParam("oldPassword") String oldPassword,
                                              HttpSession session) {
        Map<String, String> map = new HashMap<>();
        String username = (String) session.getAttribute("loginUser");
        Admin admin = adminService.findByUserName(username);
        if (admin.getPassword().equals(oldPassword)) {
            admin.setPassword(newPassword);
            map.put("msg", "更新密码成功！");
            adminService.changePassword(admin);
        } else if (newPassword.equals("") || oldPassword.equals("")) {
            map.put("msg", "密码不能为空");
        } else {
            map.put("msg", "旧密码错误");
        }
        return map;
    }
}
