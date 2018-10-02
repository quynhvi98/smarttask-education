package com.fpt.controller;

import com.fpt.entity.TinTuc;
import com.fpt.services.tintuc.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TinTucController {
    @Autowired
    private TinTucService tinTucService;


    @GetMapping("/tintuc")
    public String base(Model model){
        List<TinTuc> lstTinTuc = tinTucService.findAllAvailable();
        model.addAttribute("lstTinTuc", lstTinTuc);
        return "tintuc/tintuc";
    }

    @PostMapping("/tintuc/add-news")
    public String addNews(TinTuc tinTuc){
        tinTuc.setStatus(1);
        tinTucService.save(tinTuc);
        return "redirect:/tintuc";
    }

    @GetMapping("/tintuc/find/{id}")
    public @ResponseBody
    TinTuc findById(@PathVariable("id") Integer id){
        TinTuc tinTuc = tinTucService.findById(id);
        return tinTuc;
    }
}
