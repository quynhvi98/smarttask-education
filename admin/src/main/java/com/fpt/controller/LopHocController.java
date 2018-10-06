package com.fpt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.entity.*;
import com.fpt.services.bomon.BoMonService;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.khoavien.KhoaVienService;
import com.fpt.services.lophoc.LopHocService;
import com.fpt.services.monhoc.MonHocService;
import com.fpt.services.phonghoc.PhongHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class LopHocController {
    @Autowired
    private MonHocService monHocService;
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    private LopHocService lopHocService;
    @Autowired
    private KhoaVienService khoaVienService;
    @Autowired
    private BoMonService boMonService;
    @Autowired
    private PhongHocService phongHocService;

    @GetMapping("/lophoc")
    public String base(Model model) {
        List<KhoaVien> lstKhoaVien = khoaVienService.findAll();
        List<LopHoc> lstLopHoc = lopHocService.findAll();
        Long time = System.currentTimeMillis();
        model.addAttribute("lstKhoaVien", lstKhoaVien);
        model.addAttribute("lstLopHoc", lstLopHoc);
        model.addAttribute("time", time);
        return "lophoc/lophoc";
    }

    @PostMapping("/lophoc/them-moi")
    public void addLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String maLop = request.getParameter("maLop");
        String monHocId = request.getParameter("monHoc");
        String giangVienId = request.getParameter("giangVien");
        String phongHoc = request.getParameter("phongHoc");
        String moTa = request.getParameter("moTa");
        String ngayBatDauTemp = request.getParameter("ngayBatDau");
        String ngayKetThucTemp = request.getParameter("ngayKetThuc");
        String[] ngayHoc = request.getParameterValues("ngayHoc");
        String[] caHoc = request.getParameterValues("caHoc");

        List<String> lstNgayHoc = new ArrayList<>(Arrays.asList(ngayHoc));
        lstNgayHoc.removeIf(String::isEmpty);

        String ngayHocStr = "";
        for (int i = 0; i < lstNgayHoc.size(); i++) {
            ngayHocStr += lstNgayHoc.get(i) + ",";
        }

        String caHocStr = "";
        if (caHoc != null) {
            for (int i = 0; i < caHoc.length; i++) {
                caHocStr += caHoc[i] + ",";
            }
        }

        if (!ngayHocStr.equals("") && !caHocStr.equals("")) {

            MonHoc monHoc = monHocService.findById(monHocId != null ? monHocId : "");
            GiaoVien giaoVien = giangVienService.findById(giangVienId != null ? giangVienId : "");

            Date ngayBatDau = null;
            Date ngayKetThuc = null;
            try {
                ngayBatDau = new SimpleDateFormat("yyyy-MM-dd").parse(ngayBatDauTemp);
                ngayKetThuc = new SimpleDateFormat("yyyy-MM-dd").parse(ngayKetThucTemp);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            PhongHoc phongHocE = phongHocService.findById(phongHoc);

            LopHoc lopHoc = new LopHoc();
            lopHoc.setMaLop(maLop);
            lopHoc.setMonHoc(monHoc);
            lopHoc.setGiaoVien(giaoVien);
            lopHoc.setPhongHoc(phongHocE);
            lopHoc.setMoTa(moTa);
            lopHoc.setNgayBatDau(ngayBatDau);
            lopHoc.setNgayKetThuc(ngayKetThuc);
            lopHoc.setNgayHoc(ngayHocStr);
            lopHoc.setCaHoc(caHocStr);

            lopHocService.taoLopHoc(lopHoc);
        }else {
            throw new NullPointerException("Bạn phải chọn lịch học");
        }
    }

    @GetMapping("/lophoc/getValueForBoMon")
    public @ResponseBody
    List<BoMon> getValueForBoMonAndGiangVien(HttpServletRequest request) {
        String khoaVien = request.getParameter("khoaVien");
        List<BoMon> lstBoMon = boMonService.getLstBoMonByMaVien(khoaVien);
        for (BoMon bm : lstBoMon) {
            bm.setKhoaVien(null);
            bm.setLstMonHoc(null);
            bm.setLstGiaoVien(null);
            bm.setLstSinhVien(null);
        }
        return lstBoMon;
    }

    @GetMapping("/lophoc/getValueForMonHocAndGiangVien")
    public @ResponseBody
    ResponseEntity<Object[]> getValueForMonHoc(HttpServletRequest request) {
        String boMon = request.getParameter("boMon");
        String kiHoc = request.getParameter("kiHoc");
        List<MonHoc> lstMonHoc = monHocService.getLstMonHocByHocKiAndBoMon(boMon, Integer.parseInt(kiHoc));
        for (MonHoc mh : lstMonHoc) {
            mh.setBoMon(null);
            mh.setLstLopHoc(null);
            mh.setKiHoc(null);
        }
        List<GiaoVien> lstGiangVien = giangVienService.getLstGiangVienByMaNganh(boMon);
        for (GiaoVien gv : lstGiangVien) {
            gv.setBoMon(null);
            gv.getUser().setRoles(null);
            gv.getUser().setSinhVien(null);
            gv.getUser().setGiaoVien(null);
            gv.getUser().setLstBaiDang(null);
            gv.getUser().setLstBinhLuan(null);
            gv.getUser().setLstLike(null);
            gv.setLstLopHoc(null);
            gv.setLstPheDuyet(null);
            gv.setLstThongBao(null);
        }

        Object[] result = new Object[2];
        result[0] = lstMonHoc;
        result[1] = lstGiangVien;
        return ResponseEntity.ok(result);
    }

    @GetMapping("/lophoc/checkTimeExits")
    public @ResponseBody
    boolean checkTimeExits(HttpServletRequest request, HttpServletResponse response) {
        String ngayHoc = request.getParameter("ngayHoc");
        String caHoc = request.getParameter("caHoc");
        String maGiaoVien = request.getParameter("maGiaoVien");
        String kiHoc = request.getParameter("kiHoc");
        Boolean isExits = lopHocService.checkTimeExits(maGiaoVien, kiHoc, ngayHoc, caHoc);
        return isExits;
    }

    @GetMapping(value = "/lophoc/timkiem/{id}")
    public @ResponseBody
    LopHoc search(@PathVariable("id") String id) {
        LopHoc lopHoc = lopHocService.findById(id);
        lopHoc.getGiaoVien().getUser().setGiaoVien(null);
        lopHoc.getGiaoVien().getUser().setSinhVien(null);
        lopHoc.getGiaoVien().getUser().setRoles(null);
        lopHoc.getGiaoVien().getUser().setLstLike(null);
        lopHoc.getGiaoVien().getUser().setLstBinhLuan(null);
        lopHoc.getGiaoVien().getUser().setLstBaiDang(null);
        lopHoc.getGiaoVien().setLstThongBao(null);
        lopHoc.getGiaoVien().setLstPheDuyet(null);
        lopHoc.getGiaoVien().setLstLopHoc(null);
        lopHoc.getGiaoVien().setBoMon(null);
        lopHoc.getMonHoc().setKiHoc(null);
        lopHoc.getMonHoc().setLstLopHoc(null);
        lopHoc.getMonHoc().setBoMon(null);
        lopHoc.setLstBaiViet(null);
        lopHoc.setLstNhom(null);
        lopHoc.setLstPheDuyet(null);
        lopHoc.setLstThongBao(null);
        lopHoc.setLstTaiLieu(null);
        lopHoc.setLstDiem(null);
        lopHoc.setLstBaiDang(null);
        lopHoc.getPhongHoc().setLopHocs(null);


        Set<SinhVien> lstSinhVien = lopHoc.getSinhViens();
        for (SinhVien sv : lstSinhVien){
            sv.setLopHocs(null);
            sv.setLstBaiTap(null);
            sv.setLstPheDuyet(null);
            sv.setKhoaVien(null);
            sv.setLstThongBao(null);
            sv.setNhoms(null);
            sv.setLstDiem(null);
            sv.setBoMon(null);
            sv.getUser().setRoles(null);
            sv.getUser().setSinhVien(null);
            sv.getUser().setGiaoVien(null);
            sv.getUser().setLstLike(null);
            sv.getUser().setLstBinhLuan(null);
            sv.getUser().setLstBaiDang(null);
        }
        return lopHoc;
    }

    @GetMapping("/lophoc/getAvailableClass")
    public @ResponseBody
    List<PhongHoc> getAvailableClass(@RequestParam(value="ngayHoc[]") String[] ngayHoc,
                             @RequestParam(value="caHoc[]") String[] caHoc ){
        List<PhongHoc> lstPhongHoc = phongHocService.getAvailableClass(ngayHoc, caHoc);
        return lstPhongHoc;
    }

    @PostMapping("/lophoc/tim-kiem")
    public String searchLopHoc(HttpServletRequest request, Model model) throws IOException, ParseException {
        String fullName = request.getParameter("fullName");
        String tenMonHoc = request.getParameter("tenMonHoc");
        model.addAttribute("fullName", fullName);
        model.addAttribute("tenMonHoc", tenMonHoc);
        List<KhoaVien> lstKhoaVien = khoaVienService.findAll();
        List<LopHoc> lstLopHoc = lopHocService.search(fullName,tenMonHoc);
        Long time = System.currentTimeMillis();
        model.addAttribute("lstKhoaVien", lstKhoaVien);
        model.addAttribute("lstLopHoc", lstLopHoc);
        model.addAttribute("time", time);
        return "lophoc/lophoc";
    }
}
