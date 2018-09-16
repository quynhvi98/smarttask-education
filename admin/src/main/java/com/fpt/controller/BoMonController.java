package com.fpt.controller;

import com.fpt.entity.BoMon;
import com.fpt.entity.KhoaVien;
import com.fpt.services.bomon.BoMonService;
import com.fpt.services.khoavien.KhoaVienService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class BoMonController {
    @Autowired
    private KhoaVienService khoaVienService;
    @Autowired
    private BoMonService boMonService;

    @GetMapping("/bomon")
    public String base(Model model){
        List<BoMon> lstBoMon = boMonService.findAll();
        List<KhoaVien> lstKhoaVien = khoaVienService.findAll();
        model.addAttribute("lstBoMon", lstBoMon);
        model.addAttribute("lstKhoaVien",lstKhoaVien);
        return "bomon/bomon";
    }

    @PostMapping("/bomon/them-moi")
    public void add(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            String maBoMon = request.getParameter("maNganh");
            String tenBoMon = request.getParameter("tenNganh");
            String strKhoaVien = request.getParameter("khoaVien");
            KhoaVien khoaVien = khoaVienService.findById(strKhoaVien);

            BoMon boMon = new BoMon();
            boMon.setKhoaVien(khoaVien);
            boMon.setMaNganh(maBoMon);
            boMon.setTenNganh(tenBoMon);
            boMon.setTrangThai(String.valueOf(1));

            boMonService.taoBoMon(boMon);
            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("failed");
        }
    }
}
