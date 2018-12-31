package com.hinstein.blog.service;


import com.hinstein.blog.bean.Admin;
import com.hinstein.blog.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.service
 * @Author: Hinstein
 * @CreateTime: 2018-11-23 21:02
 * @Description:
 */

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin findByUserName(String username) {
        return adminMapper.findByUserName(username);
    }

    public int changePassword(Admin admin){
        return adminMapper.changePassword(admin);
    }
}