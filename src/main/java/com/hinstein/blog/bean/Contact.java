package com.hinstein.blog.bean;

import org.apache.tomcat.jni.Address;

import java.util.Date;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.bean
 * @Author: Hinstein
 * @CreateTime: 2018-11-27 18:38
 * @Description:
 */
public class Contact {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;
    private String emailAddress;
    private String phoneNumber;
    private String message;
    private Date date;



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "contact [name=" + name + ", emailAddress=" + emailAddress + ", phoneNumber=" + phoneNumber + ",message=" + message +
                ",Date=" + date + "]";
    }
}
