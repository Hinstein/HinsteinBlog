package com.hinstein.blog.service;

import com.hinstein.blog.bean.Article;
import com.hinstein.blog.bean.Contact;
import com.hinstein.blog.mapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.service
 * @Author: Hinstein
 * @CreateTime: 2018-11-27 18:42
 * @Description:
 */
@Service
public class ContactService {

    @Autowired
    private ContactMapper contactMapper;

    public int insert(Contact contact) {
        return contactMapper.insert(contact);
    }

    public List<Contact> findAll() {
        return contactMapper.findAll();
    }

    public Contact findById(int id) {
        return contactMapper.findById(id);
    }

    public int deleteContact(int id) {
        return contactMapper.delete(id);
    }
}
