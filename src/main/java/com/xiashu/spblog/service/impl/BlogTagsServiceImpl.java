package com.xiashu.spblog.service.impl;

import com.xiashu.spblog.bean.BlogTags;
import com.xiashu.spblog.mapper.BlogTagsMapper;
import com.xiashu.spblog.service.BlogTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class BlogTagsServiceImpl implements BlogTagsService {
    @Autowired
    BlogTagsMapper blogTagsMapper;

    @Override
    public List<BlogTags> getAllTags() {

        return blogTagsMapper.selectAll();
    }

    @Override
    public void addTagIds(String tagIds, Long id) {
        String[] split = tagIds.split(",");
        for (String tagId : split) {
            if (tagId != null && !tagId.equals("")){
                BlogTags blogTags = new BlogTags();
                blogTags.setBlogsId(id);
                blogTags.setTagsId(Long.parseLong(tagId));
                blogTagsMapper.insert(blogTags);
            }

        }
    }

    @Override
    public void deleteBlogTags(Long id) {
        BlogTags blogTags = new BlogTags();
        blogTags.setBlogsId(id);
        blogTagsMapper.delete(blogTags);
    }
}
