package com.hinstein.blog.mapper;

import com.hinstein.blog.bean.Admin;
import com.hinstein.blog.bean.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.mapper
 * @Author: Hinstein
 * @CreateTime: 2018-11-24 16:59
 * @Description:
 */
public interface ArticleMapper {

    /**
     * 数据库查询，找到所有文章
     * @return 所有文章
     */
    @Select("select * from article ORDER BY date DESC")
    List<Article> findAll();

    /**
     * 数据库查询，通过id找到文章
     * @param id
     * @return 相应的文章
     */
    @Select("select * from article where id = #{id}")
    Article findById(@Param("id") int id);

    /**
     * 数据库插入新的文章
     * @param article
     * @return 是否插入成功
     */
    @Insert("INSERT INTO article (title,content,date,count,views,likes) VALUES (#{title},#{content},#{date},#{count},#{views},#{likes})")
    int insert(Article article);

    /**
     * 数据库更新文章
     * @param article
     * @return 是否更新成功
     */
    @Update("update article set title=#{title},content=#{content} where id=#{id}")
    int update(Article article);

    /**
     * 数据库删除文章
     * @param id
     * @return 是否删除成功
     */
    @Delete("delete from article where id=#{id}")
    int delete(int id);


    /**
     * 数据库更新浏览数
     * @param id
     * @return 是否更新成功
     */
    @Update("UPDATE article SET views=views+1 WHERE ID LIKE #{id}")
    int pageViews(int id);

    /**
     * 数据库更新点赞数
     * @param id
     * @return 是否更新成功
     */
    @Update("UPDATE article SET likes=likes+1 WHERE ID LIKE #{id}")
    int goodJob(int id);
}
