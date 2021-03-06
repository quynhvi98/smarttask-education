package com.fpt.controller;

import com.fpt.entity.TinTuc;
import com.fpt.services.tintuc.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public String addNews(HttpServletRequest request){
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        TinTuc tinTuc = new TinTuc();
        tinTuc.setTitle(title);
        tinTuc.setContent(content);
        tinTuc.setStatus(1);
        tinTucService.save(tinTuc);
        return "redirect:/tintuc";
    }

    @PostMapping("/tintuc/update-news")
    public String editNews(HttpServletRequest request){
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        TinTuc tinTucUpdate = tinTucService.findById(Integer.parseInt(id));
        tinTucUpdate.setTitle(title);
        tinTucUpdate.setContent(content);
        tinTucService.save(tinTucUpdate);
        return "redirect:/tintuc";
    }

    @GetMapping("/tintuc/find/{id}/{type}")
    public String findById(@PathVariable("id") Integer id,@PathVariable("type") String type, Model model){
        TinTuc tinTuc = tinTucService.findById(id);
        model.addAttribute("type", type);
        model.addAttribute("tinTuc", tinTuc);
        return "tintuc/vieworupdate";
    }

    @GetMapping("/tintuc/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeNews(@PathVariable("id") Integer id){
        tinTucService.delete(id);
    }

}
