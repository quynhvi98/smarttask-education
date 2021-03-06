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
        List<KhoaVien> lstKhoaVien = khoaVienService.findAll();
        model.addAttribute("lstKhoaVien", lstKhoaVien);
        return "khoavien/khoavien";
    }

    @PostMapping("/khoavien/them-moi")
    public void addKhoaVien(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String maVien = request.getParameter("maVien");
        String tenVien = request.getParameter("tenVien");
        try {
            KhoaVien khoaVien = new KhoaVien();
            khoaVien.setMaVien(maVien);
            khoaVien.setTenVien(tenVien);
            khoaVienService.create(khoaVien);
            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("failed");
        }
    }

    @PostMapping("/khoavien/cap-nhat")
    public void editKhoaVien(HttpServletRequest request, HttpServletResponse response) throws IOException {
        KhoaVien persist = null;
        String maVien = request.getParameter("maVien");
        String tenVien = request.getParameter("tenVien");
        try {

            persist = khoaVienService.findById(maVien);
            persist.setTenVien(tenVien);
            khoaVienService.update(persist);
            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("failed");
        }
    }

    @PostMapping("/khoavien/xoa")
    public void delete(@RequestParam("id") String id, HttpServletResponse response) throws IOException {
        try {
            KhoaVien persist = khoaVienService.findById(id);
            persist.setTrangThai(String.valueOf(0));
            khoaVienService.update(persist);
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
        khoaVien.setLstBoMon(null);
        khoaVien.setLstSinhVien(null);
        return khoaVien;
    }




    @PostMapping("/khoavien/tim-kiem")
    public String searchKhoaVien(HttpServletRequest request, Model model) throws IOException {
        String tenKhoaVien = request.getParameter("tenKhoaVien");
        String maKhoaVien = request.getParameter("maKhoaVien");
        model.addAttribute("maKhoaVien", maKhoaVien);
        model.addAttribute("tenKhoaVien", tenKhoaVien);
        List<KhoaVien> lstKhoaVien = khoaVienService.search(tenKhoaVien,maKhoaVien);
        model.addAttribute("lstKhoaVien", lstKhoaVien);
        return "khoavien/khoavien";
    }
}
