package com.xiashu.spblog.web.admin;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "/admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_INPUT = "redirect:/admin/blogs-input";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;
    @Autowired
    TagsService tagsService;
    @Autowired
    BlogTagsService blogTagsService;

    @GetMapping("/blogs")
    public String blogs(Model model){

        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        PageInfo<Blog> blogPageInfo = blogService.listBlog(5);
        model.addAttribute("page",blogPageInfo);
        return "admin/blogs";
    }

    @GetMapping("blogs/input")
    public String input(Model model)  {
        Blog blog = new Blog();
        blog.setPublished("0");
        List<Tag> tags = tagsService.listTag();
        blog.setTags(tags);
        blog.setTagIds("");
        blog.setDescription("");
        model.addAttribute("blog",blog);
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        return INPUT;
    }

    @GetMapping("/blogs/{id}/input")
    public String input(@PathVariable("id") Long id, Model model)  {
        Blog blog = blogService.getBlogById(id);
        List<Tag> tags = tagsService.listTag();

        model.addAttribute("blog",blog);
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        return INPUT;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model)  {
        blogService.deleteBlogById(id);
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        PageInfo<Blog> blogPageInfo = blogService.listBlog(5);
        model.addAttribute("page",blogPageInfo);

        blogTagsService.deleteBlogTags(id);
        return LIST;
    }


    @PostMapping("/SaveBlog")
    public String saveBlog(Blog blog, @RequestParam("type.id") Long id, Model model){

        if (blog.getCreateTime() == null){
            blog.setCreateTime(new java.sql.Date(new Date().getTime()));
        }
        blog.setUpdateTime(new java.sql.Date(new Date().getTime()));
        blog.setTypeId(id);
        blog.setUserId(1L);
        if (blog.getViews()==null){
            blog.setViews(0);
        }

        Blog blog1 = blogService.saveBlog(blog);
        String tagIds = blog.getTagIds();

        blogTagsService.addTagIds(tagIds,blog1.getId());

        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        PageInfo<Blog> blogPageInfo = blogService.listBlog(5);
        model.addAttribute("page",blogPageInfo);

        return "redirect:http://localhost:8081/admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(String title, Long typeId, Boolean recommend, int page, Model model){
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);

        PageInfo<Blog> blogPageInfo = blogService.SearchListBlog(title,typeId,recommend,5);

        model.addAttribute("page",blogPageInfo);
        
        return "admin/blogs :: blogList";
    }





    


}
