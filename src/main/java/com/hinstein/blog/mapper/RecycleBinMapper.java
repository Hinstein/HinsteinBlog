package com.hinstein.blog.mapper;

import com.hinstein.blog.bean.Article;
import com.hinstein.blog.bean.RecycleBin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.mapper
 * @Author: Hinstein
 * @CreateTime: 2018-12-18 10:32
 * @Description:
 */

public interface RecycleBinMapper {

    /**
     * 数据库查找所有回收站博客
     *
     * @return
     */
    @Select("select * from recyclebin ORDER BY id DESC")
    List<RecycleBin> recycleBinFindAll();

    /**
     * 数据库根据id找到回收站里的博客
     *
     * @param id
     * @return
     */
    @Select("select * from recyclebin where id = #{id}")
    RecycleBin recycleBinFindById(@Param("id") int id);

    /**
     * 数据库删除回收站里的博客
     *
     * @param id
     * @return
     */
    @Delete("delete from recyclebin where id=#{id}")
    int recycleBinDelete(int id);

    /**
     * 数据库插入博客
     *
     * @param recyclebin
     * @return
     */
    @Insert("INSERT INTO recyclebin (title,content,date,count,views,likes) VALUES (#{title},#{content},#{date},#{count},#{views},#{likes})")
    int recycleBinInsert(RecycleBin recyclebin);
}
