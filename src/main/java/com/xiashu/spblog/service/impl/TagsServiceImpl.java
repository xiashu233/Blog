package com.xiashu.spblog.service.impl;


import com.xiashu.spblog.bean.BlogTags;
import com.xiashu.spblog.bean.Tag;
import com.xiashu.spblog.mapper.BlogTagsMapper;
import com.xiashu.spblog.mapper.TagMapper;
import com.xiashu.spblog.service.TagsService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    TagMapper tagMapper;
    @Autowired
    BlogTagsMapper blogTagsMapper;

    @Override
    public void saveTag(Tag tag) {
        tagMapper.insert(tag);
    }

    @Override
    public List<Tag> listTag() {
        List<Tag> tags = tagMapper.selectAll();
        return tags;
    }

    @Override
    public Tag updateTag(Tag tag) throws NotFoundException {
        if (tag != null){
            Example example = new Example(Tag.class);
            example.createCriteria().andEqualTo("id",tag.getId());
            tagMapper.updateByExample(tag,example);
            return tag;
        }
        throw new NotFoundException("不存在该资源");
    }

    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Tag> listTagAndCount() {
        List<Tag> tags = tagMapper.selectAll();
        for (Tag tag : tags) {
            BlogTags blogTags = new BlogTags();
            blogTags.setTagsId(tag.getId());
            int count = blogTagsMapper.selectCount(blogTags);
            tag.setBlogsCount(count);
        }
        return tags;
    }
}
