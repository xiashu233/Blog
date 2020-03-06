package com.xiashu.spblog.mapper;


import com.xiashu.spblog.bean.Blog;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BlogMapper extends Mapper<Blog> {
    List<Blog> SearchListBlog(String title, Long typeId, int recommend);

    void InsertBlog(Blog blog);

    Blog selectBlogById(Long id);

    List<Blog> selectAllBlog();

    List<Blog> selectBlogAndYear();
}
