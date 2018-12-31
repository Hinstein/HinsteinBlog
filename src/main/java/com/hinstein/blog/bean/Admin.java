package com.hinstein.blog.bean;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.entities
 * @Author: Hinstein
 * @CreateTime: 2018-11-23 21:07
 * @Description:
 */

public class Admin {
    private int id;
    private String name;
    private String userName;
    private String password;

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Admin(String name) {
        super();
        this.name = name;
    }

    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password="+password+"]";
    }

}
