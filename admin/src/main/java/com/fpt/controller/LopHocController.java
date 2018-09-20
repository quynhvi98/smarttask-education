package com.fpt.controller;

import com.fpt.entity.*;
import com.fpt.services.bomon.BoMonService;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.lophoc.LopHocService;
import com.fpt.services.monhoc.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class LopHocController {
    @Autowired
    private MonHocService monHocService;
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    private LopHocService lopHocService;

    @GetMapping("/lophoc")
    public String base(Model model){
        List<MonHoc> lstMonHoc = monHocService.findAll();
        List<GiaoVien> lstGiaoVien = giangVienService.findAll();
        List<LopHoc> lstLopHoc = lopHocService.findAll();
        model.addAttribute("lstMonHoc", lstMonHoc);
        model.addAttribute("lstGiaoVien",lstGiaoVien);
        model.addAttribute("lstLopHoc", lstLopHoc);
        return "lophoc/lophoc";
    }

    @PostMapping("/lophoc/them-moi")
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String maLop = request.getParameter("maLop");
            String monHocId = request.getParameter("monHoc");
            String giangVienId = request.getParameter("giangVien");
            String phongHoc = request.getParameter("phongHoc");
            String moTa = request.getParameter("moTa");
            String ngayBatDauTemp = request.getParameter("ngayBatDau");
            String ngayKetThucTemp = request.getParameter("ngayKetThuc");

            MonHoc monHoc = monHocService.findById(monHocId != null ? monHocId : "");
            GiaoVien giaoVien = giangVienService.findById(giangVienId != null ? giangVienId : "");

            Date ngayBatDau =new SimpleDateFormat("yyyy-MM-dd").parse(ngayBatDauTemp);
            Date ngayKetThuc=new SimpleDateFormat("yyyy-MM-dd").parse(ngayKetThucTemp);

            LopHoc lopHoc = new LopHoc();
            lopHoc.setMaLop(maLop);
            lopHoc.setMonHoc(monHoc);
            lopHoc.setGiaoVien(giaoVien);
            lopHoc.setPhongHoc(phongHoc);
            lopHoc.setMoTa(moTa);
            lopHoc.setNgayBatDau(ngayBatDau);
            lopHoc.setNgayKetThuc(ngayKetThuc);

            lopHocService.taoLopHoc(lopHoc);
            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("failed");
        }
    }
}
