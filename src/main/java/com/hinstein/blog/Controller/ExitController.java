package com.hinstein.blog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.Controller
 * @Author: Hinstein
 * @CreateTime: 2018-11-18 15:41
 * @Description:
 */
@Controller
public class ExitController {

    /**
     * 退出请求，清除session
     *
     * @param map
     * @param session
     * @return
     */
    @GetMapping(value = "/admin/exit")
    public String exit(Map<String, Object> map, HttpSession session) {
        //退出，移除session
        session.removeAttribute("loginUser");
        session.removeAttribute("articlesSize");
        session.removeAttribute("contactSize");
        map.put("msg", "您已经退出，请重新登录");
        return "admin/login";
    }
}
