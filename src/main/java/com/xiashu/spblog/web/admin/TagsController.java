package com.xiashu.spblog.web.admin;

import com.xiashu.spblog.bean.Tag;
import com.xiashu.spblog.service.TagsService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagsController {

    @Autowired
    TagsService tagsService;


    @GetMapping("tags")
    public String list(Model model){
        List<Tag> tags = tagsService.listTag();
        model.addAttribute("tags",tags);
        return "admin/tags";
    }

    @GetMapping("tags/input")
    public String input(Model model)  {
        Tag tag = new Tag();
        model.addAttribute("tag",tag);
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String post(Tag tag){
        tagsService.saveTag(tag);
        if (tag.getId() != null){
            //
        }else{
            // return "";
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("tags/{id}/input")
    public String updata(Model model, @PathVariable("id") Long id)  {
        Tag tag = new Tag();
        tag.setId(id);
        model.addAttribute("tag",tag);
        return "admin/tags-input";
    }

    @PostMapping("/tags/{id}")
    public String post(Tag tag,@PathVariable("id") Long id) throws NotFoundException {
        tag.setId(id);
        tagsService.updateTag(tag);
        if (tag.getId() != null){
            //
        }else{
            // return "";
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("tags/{id}/delete")
    public String post(@PathVariable("id") Long id){
        tagsService.deleteTag(id);
//        if (type.getId() != null){
//            //
//        }else{
//            // return "";
//        }
        return "redirect:/admin/tags";
    }

}
