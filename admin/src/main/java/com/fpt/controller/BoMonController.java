package com.fpt.controller;

import com.fpt.entity.BoMon;
import com.fpt.entity.KhoaVien;
import com.fpt.services.bomon.BoMonService;
import com.fpt.services.khoavien.KhoaVienService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
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
    public void addBoMon(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            String maBoMon = request.getParameter("maNganh");
            String tenBoMon = request.getParameter("tenNganh");
            String strKhoaVien = request.getParameter("khoaVien");
            KhoaVien khoaVien = khoaVienService.findById(strKhoaVien != null ? strKhoaVien : "");

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

    @GetMapping(value = "/bomon/timkiem/{id}")
    public @ResponseBody
    BoMon search(@PathVariable("id") String id) {
        BoMon boMon = boMonService.findById(id);
        boMon.setLstMonHoc(null);
        boMon.setLstGiaoVien(null);
        boMon.getKhoaVien().setLstBoMon(null);
        boMon.getKhoaVien().setLstSinhVien(null);
        return boMon;
    }

    @PostMapping("/bomon/timkiem")
    public String searchBoMon(HttpServletRequest request, Model model) throws IOException, ParseException {
        String tenBoMon = request.getParameter("tenBoMon");
        String maBoMon = request.getParameter("maBoMon");
        System.out.println(tenBoMon+"  "+maBoMon);
        List<BoMon> lstBoMon = boMonService.searchBoMon(tenBoMon,maBoMon);
        List<KhoaVien> lstKhoaVien = khoaVienService.findAll();
        model.addAttribute("lstBoMon", lstBoMon);
        model.addAttribute("tenBoMon", tenBoMon);
        model.addAttribute("maBoMon", maBoMon);
        model.addAttribute("lstKhoaVien",lstKhoaVien);
        return "bomon/bomon";
    }

    @PostMapping("/bomon/cap-nhat")
    public void editBoMon(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BoMon persist = null;
        KhoaVien khoaVien = null;
        try {
            persist = boMonService.findById(request.getParameter("maNganh"));
            khoaVien = khoaVienService.findById(request.getParameter("khoaVien"));
            persist.setKhoaVien(khoaVien);
            persist.setTenNganh(request.getParameter("tenNganh"));
            boMonService.update(persist);
            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("failed");
        }
    }
}
