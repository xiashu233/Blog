package com.xiashu.spblog;

import com.xiashu.spblog.bean.BlogTags;
import com.xiashu.spblog.mapper.BlogTagsMapper;
import com.xiashu.spblog.service.BlogTagsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestbdApplicationTests {

    @Autowired
    BlogTagsMapper blogTagsMapper;
    @Autowired
    BlogTagsService blogTagsService;

    @Test
    public void contextLoads() {
        List<BlogTags> blogTags = blogTagsService.getAllTags();
        System.out.println(blogTags.size());
    }

}
