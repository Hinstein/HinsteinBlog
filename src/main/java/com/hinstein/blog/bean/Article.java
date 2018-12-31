package com.hinstein.blog.bean;

import java.util.Date;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.bean
 * @Author: Hinstein
 * @CreateTime: 2018-11-24 17:02
 * @Description:
 */
public class Article {
    private Integer id;
    private String title;
    private String content;
    private Integer views;
    private Integer count;
    private Date date;
    private Integer likes;

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", content=" + content + ",views=" + views +
                ",count=" + count + ",date=" + date + ",likes=" + likes + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }


    public Article() {
        super();
        // TODO Auto-generated constructor stub
    }

}
