package com.xiashu.spblog.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiashu.spblog.bean.Blog;
import com.xiashu.spblog.bean.Tag;
import com.xiashu.spblog.bean.Type;
import com.xiashu.spblog.service.BlogService;
import com.xiashu.spblog.service.BlogTagsService;
import com.xiashu.spblog.service.TagsService;
import com.xiashu.spblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagsService tagsService;
    @Autowired
    BlogTagsService blogTagsService;

    @GetMapping("/index/{pageNum}")
    public String index(@PathVariable("pageNum") int pageNum,ModelMap modelMap){
        int totalPage = blogService.getTotalBlog();
        modelMap.put("totalNum",totalPage);
        int pageSize = totalPage - (pageNum - 1) * 5>5?5:totalPage - (pageNum-1) * 5;
        PageHelper.startPage(pageNum,pageSize);

        List<Blog> blogList = blogService.listRecommendBlogs();
        PageInfo pageInfo =new PageInfo<>(blogList);

        modelMap.put("blogList",pageInfo);

        List<Type> typeList = typeService.listTypeAndCount();
        modelMap.put("typeList",typeList);

        List<Tag> tagList = tagsService.listTagAndCount();
        modelMap.put("tagList",tagList);
        return "index";
    }

    @GetMapping("/blog/{blogId}")
    public String blogs(@PathVariable("blogId") Long blogId, ModelMap modelMap){
        Blog blog = blogService.getBlogAndConvert(blogId);


        Blog blogClone = null;
        try {
            blogClone = (Blog) blog.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        blogClone.setViewsCount((blogClone.getViewsCount()+1));
        blogService.changeViewCount(blogClone);
        // 分页
        modelMap.put("blog",blog);
        return "blog";
    }

    @GetMapping("/tags/{tagsId}")
    public String tags(@PathVariable("tagsId")Long tagsId,ModelMap modelMap){
        List<Tag> tagList = tagsService.listTagAndCount();
        modelMap.put("tagList",tagList);

        List<Blog> blogList = blogService.listBlogByTagsId(tagsId);
        modelMap.put("blogList",blogList);
        return "tags";
    }

    @GetMapping("/types/{typeId}")
    public String types(@PathVariable("typeId")Long typeId,ModelMap modelMap){
        List<Type> typeList = typeService.listTypeAndCount();
        modelMap.put("typeList",typeList);

        List<Blog> blogList = blogService.listBlogByTypeId(typeId);
        modelMap.put("blogList",blogList);
        return "types";
    }

    @GetMapping("/archives")
    public String archives(ModelMap modelMap){
        Map<String,List<Blog>> archivesDate = blogService.geArchives();
        modelMap.put("archivesDate",archivesDate);
        return "archives";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/")
    public String toIndex(ModelMap modelMap){
        return index(1,modelMap);
    }
}
