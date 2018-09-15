package com.fpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fpt.entity.KhoaVien;
import com.fpt.services.khoavien.KhoaVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;


@Controller
public class KhoaVienController {
    @Autowired
    private KhoaVienService khoaVienService;

    @GetMapping("/khoavien")
    public String base(Model model) {
        List<KhoaVien> lstKhoaVien = khoaVienService.getAll();
        model.addAttribute("lstKhoaVien", lstKhoaVien);
        return "khoavien/khoavien";
    }

    @PostMapping("/khoavien/them-moi")
    public void add(KhoaVien khoaVien, HttpServletResponse response) throws IOException {
        try {
            khoaVienService.create(khoaVien);
            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("failed");
        }
    }

    @PostMapping("/khoavien/cap-nhat")
    public void edit(KhoaVien khoaVien, HttpServletResponse response) throws IOException {
        KhoaVien persist = null;
        try {
            persist = khoaVienService.findById(khoaVien.getMaVien());
            persist.setTenVien(khoaVien.getTenVien());
            khoaVienService.update(persist);
            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("failed");
        }
    }

    @PostMapping("/khoavien/xoa")
    public void delete(@RequestParam("id") String id, HttpServletResponse response) throws IOException {
        try {
            khoaVienService.delete(id);
            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("failed");
        }
    }
    // cho mh qlkh
    @GetMapping(value = "/khoavien/timkiem/{id}")
    public @ResponseBody
    KhoaVien search(@PathVariable("id") String id) {
        KhoaVien khoaVien = khoaVienService.findById(id);
        return khoaVien;
    }
}
