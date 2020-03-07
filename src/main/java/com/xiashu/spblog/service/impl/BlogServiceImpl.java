package com.xiashu.spblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xiashu.spblog.bean.Blog;
import com.xiashu.spblog.bean.BlogTags;
import com.xiashu.spblog.bean.Tag;
import com.xiashu.spblog.bean.Type;
import com.xiashu.spblog.mapper.BlogMapper;
import com.xiashu.spblog.mapper.BlogTagsMapper;
import com.xiashu.spblog.mapper.TagMapper;
import com.xiashu.spblog.mapper.TypeMapper;
import com.xiashu.spblog.service.BlogService;
import com.xiashu.spblog.utils.MarkDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    BlogTagsMapper blogTagsMapper;
    @Autowired
    TagMapper tagMapper;

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
       try{
           blogMapper.insert(blog);
       }catch (Exception e){
           e.printStackTrace();
           throw new RuntimeException();
       }
        return blog;
    }

    @Override
    public PageInfo<Blog> listBlog(int pageSize) {
        //设置分页器
        int pageNum = 1;
        List<Blog> list = blogMapper.selectAllBlog();
        initType(list);
        pageNum = list.size()%5==0?list.size()/5:list.size()/5+1;
        PageHelper.startPage(pageNum,pageSize);

        PageInfo<Blog> page = new PageInfo<Blog>(list);

        return page;


    }

    @Override
    public Blog getBlogById(Long id) {
        Blog blog = new Blog();
        blog.setId(id);
        Blog blog1 = blogMapper.selectOne(blog);
        return blog1;
    }

    @Override
    public Blog getBlogAndConvert(Long id) {
        Blog blog = new Blog();
        blog.setId(id);
        Blog blog1 = blogMapper.selectOne(blog);
        String html = MarkDownUtils.markdownToHtmlExtensions(blog1.getContent());
        blog1.setContent(html);
        return blog1;
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Example example = new Example(Blog.class);
        example.createCriteria().andEqualTo("id",id);
        blogMapper.updateByExample(blog,example);
        blog.setId(id);
        return blog;
    }

    @Override
    public void deleteBlogById(Long id) {
        blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Blog> SearchListBlog(String title, Long typeId, Boolean recommend, int pageSize) {
        int rm = recommend?1:0;
        List<Blog> searchBlogs = blogMapper.SearchListBlog(title,typeId,rm);
        int pageNum = searchBlogs.size()%5==0?searchBlogs.size()/5:searchBlogs.size()/5+1;
        PageHelper.startPage(pageNum,pageSize);
        initType(searchBlogs);
        PageInfo<Blog> page = new PageInfo<Blog>(searchBlogs);

        return page;
    }

    @Override
    public List<Blog> listRecommendBlogs() {
        Blog blog = new Blog();
        blog.setRecommend("1");
        blog.setPublished("1");
        List<Blog> blogList = blogMapper.select(blog);
        for (Blog blogItem : blogList) {
            if (blogItem.getContent().length() > 40){
                blogItem.setContentDes(blogItem.getContent().substring(0,40));
            }else{
                blogItem.setContentDes(blogItem.getContent());
            }

            BlogTags blogTags = new BlogTags();
            blogTags.setBlogsId(blogItem.getId());
            List<BlogTags> blogTagsList = blogTagsMapper.select(blogTags);
            List<Tag> tagsList = new ArrayList<>();
            for (BlogTags tags : blogTagsList) {
                Tag tag = tagMapper.selectByPrimaryKey(tags.getTagsId());
                tagsList.add(tag);
            }
            blogItem.setTags(tagsList);

        }
        return blogList;
    }

    @Override
    public Map<String, List<Blog>> geArchives() {
        Map<String, List<Blog>> archives = new HashMap();
        List<Blog> blogs = blogMapper.selectBlogAndYear();
        for (Blog blog : blogs) {
            String year = blog.getYear();
            if (archives.get(year) != null){
                archives.get(year).add(blog);
            }else{
                List<Blog> blogY = new ArrayList<>();
                blogY.add(blog);
                archives.put(year,blogY);
            }
        }

        return archives;
    }

    @Override
    public List<Blog> listBlogByTypeId(Long typeId) {
        Blog blog= new Blog();
        blog.setTypeId(typeId);
        List<Blog> blogList = blogMapper.select(blog);
        return blogList;
    }

    @Override
    public List<Blog> listBlogByTagsId(Long tagsId) {
        List<Blog> blogList = new ArrayList<>();
        BlogTags blogTags = new BlogTags();
        blogTags.setTagsId(tagsId);
        List<BlogTags> blogTagsList = blogTagsMapper.select(blogTags);
        for (BlogTags tags : blogTagsList) {
            Long blogsId = tags.getBlogsId();
            Blog blog = blogMapper.selectByPrimaryKey(blogsId);
            Type type = typeMapper.selectByPrimaryKey(blog.getTypeId());
            blog.setType(type);
            blogList.add(blog);
        }

        return blogList;
    }

    private List<Blog> initType(List<Blog> list){

        Type sqlType = new Type();
        for (Blog blog : list) {
            Long typeId = blog.getTypeId();
            sqlType.setId(typeId);
            Type type = typeMapper.selectOne(sqlType);
            blog.setType(type);
        }
        return list;
    }


}
