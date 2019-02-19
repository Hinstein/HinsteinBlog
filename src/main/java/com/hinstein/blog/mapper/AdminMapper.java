package com.hinstein.blog.mapper;

import com.hinstein.blog.bean.Admin;
import com.hinstein.blog.bean.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.mapper
 * @Author: Hinstein
 * @CreateTime: 2018-11-23 21:02
 * @Description:
 */

//@Mapper可以加在这里，也可以在启动项加

public interface AdminMapper {

    /**
     * 数据库查询，找所有管理员
     *
     * @param userName
     * @return
     */
    @Select("select * from admin where name = #{userName}")
    Admin findByUserName(String userName);

    /**
     * 更改密码
     *
     * @param
     * @return
     */
    @Update("update admin set password=#{password} WHERE ID LIKE #{id}")
    int changePassword(Admin admin);
}



