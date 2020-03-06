package com.xiashu.spblog.web.admin;

import com.xiashu.spblog.bean.Type;
import com.xiashu.spblog.service.TypeService;
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
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping("types")
    public String list(Model model){
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        return "admin/types";
    }

    @GetMapping("types/input")
    public String input(Model model)  {
        Type type = new Type();
        model.addAttribute("type",type);
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(Type type){
        typeService.saveType(type);
        if (type.getId() != null){
            //
        }else{
            // return "";
        }
        return "redirect:/admin/types";
    }

    @GetMapping("types/{id}/input")
    public String updata(Model model, @PathVariable("id") Long id)  {
        Type type = new Type();
        type.setId(id);
        model.addAttribute("type",type);
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String post(Type type,@PathVariable("id") Long id) throws NotFoundException {
        type.setId(id);
        typeService.updateType(type);
        if (type.getId() != null){
            //
        }else{
            // return "";
        }
        return "redirect:/admin/types";
    }

    @GetMapping("types/{id}/delete")
    public String post(@PathVariable("id") Long id){
        typeService.deleteType(id);
//        if (type.getId() != null){
//            //
//        }else{
//            // return "";
//        }
        return "redirect:/admin/types";
    }


}
