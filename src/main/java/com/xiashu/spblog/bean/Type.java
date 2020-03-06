package com.xiashu.spblog.bean;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

public class Type {

    @Id
    private Long id;
    private String name;
    @Transient
    private List<Blog> blogs = new ArrayList<>();
    @Transient
    private int blogsCount;

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public int getBlogsCount() {
        return blogsCount;
    }

    public void setBlogsCount(int blogsCount) {
        this.blogsCount = blogsCount;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
