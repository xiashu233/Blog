package com.xiashu.spblog.service;

import com.xiashu.spblog.bean.BlogTags;

import java.util.List;

public interface BlogTagsService {
    List<BlogTags> getAllTags();

    void addTagIds(String tagIds, Long id);

    void deleteBlogTags(Long id);
}
