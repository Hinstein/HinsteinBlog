package com.hinstein.blog.service;

import com.hinstein.blog.bean.Article;
import com.hinstein.blog.bean.RecycleBin;
import com.hinstein.blog.mapper.ArticleMapper;
import com.hinstein.blog.mapper.RecycleBinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.service
 * @Author: Hinstein
 * @CreateTime: 2018-12-18 10:30
 * @Description:
 */

@Service
public class RecycleBinService {

    @Autowired
    private RecycleBinMapper recycleBinMapper;

    public List<RecycleBin> recycleBinFindAll() {
        return recycleBinMapper.recycleBinFindAll();
    }

    public RecycleBin recycleBinFindById(int id) {
        return recycleBinMapper.recycleBinFindById(id);
    }

    public int recycleBinDelete(int id) {
        return recycleBinMapper.recycleBinDelete(id);
    }

    public int recycleBinInsert(RecycleBin recycleBin) {
        return recycleBinMapper.recycleBinInsert(recycleBin);
    }
}
