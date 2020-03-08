package com.xiashu.spblog.service;

import com.github.pagehelper.PageInfo;
import com.xiashu.spblog.bean.Blog;

import java.util.List;
import java.util.Map;


public interface BlogService {

    Blog saveBlog(Blog blog);

    PageInfo<Blog> listBlog(int pageSize) ;

    Blog getBlogById(Long id);

    Blog getBlogAndConvert(Long id);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlogById(Long id);

    PageInfo<Blog> SearchListBlog(String title, Long typeId, Boolean recommend, int pageSize);

    List<Blog> listRecommendBlogs();

    Map<String, List<Blog>> geArchives();

    List<Blog> listBlogByTypeId(Long typeId);

    List<Blog> listBlogByTagsId(Long tagsId);

    int getTotalBlog();
}
