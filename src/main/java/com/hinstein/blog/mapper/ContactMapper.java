package com.hinstein.blog.mapper;

import com.hinstein.blog.bean.Article;
import com.hinstein.blog.bean.Contact;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.mapper
 * @Author: Hinstein
 * @CreateTime: 2018-11-27 18:40
 * @Description:
 */
public interface ContactMapper {

    /**
     * 数据库新建留言
     * @param contact
     * @return 是否建立成功
     */
    @Insert("INSERT INTO contact (name,emailAddress,phoneNumber,message,date) VALUES (#{name},#{emailAddress},#{phoneNumber},#{message},#{date})")
    int insert(Contact contact);

    /**
     * 数据库查找所有留言
     * @return 所有留言
     */
    @Select("select * from contact ORDER BY id DESC")
    List<Contact> findAll();

    /**
     * 数据库通过id找到留言
     * @param id
     * @return 找到的留言
     */
    @Select("select * from contact where id = #{id}")
    Contact findById(@Param("id") int id);

    /**
     * 数据库通过id删除留言
     * @param id
     * @return 是否删除成功
     */
    @Delete("delete from contact where id=#{id}")
    int delete(int id);


}
