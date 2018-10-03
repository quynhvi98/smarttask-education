package com.fpt.controller;

import com.fpt.entity.BoMon;
import com.fpt.entity.KiHoc;
import com.fpt.entity.MonHoc;
import com.fpt.services.bomon.BoMonService;
import com.fpt.services.kihoc.KiHocService;
import com.fpt.services.monhoc.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MonHocController {

    @Autowired
    private BoMonService boMonService;
    @Autowired
    private MonHocService monHocService;
    @Autowired
    private KiHocService kiHocService;

    @GetMapping("/monhoc")
    public String base(Model model){
        List<BoMon> lstBoMon = boMonService.findAll();
        List<MonHoc> lstMonHoc = monHocService.findAll();
        Long totalRecord = monHocService.count();
        model.addAttribute("lstBoMon", lstBoMon);
        model.addAttribute("lstMonHoc", lstMonHoc);
        model.addAttribute("totalRecord", totalRecord);
        return "monhoc/monhoc";
    }

    @PostMapping("/monhoc/them-moi")
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String maMonHoc = request.getParameter("maMonHoc");
            String tenMonHoc = request.getParameter("tenMonHoc");
            String boMonId = request.getParameter("boMon");
            String tinChi = request.getParameter("tinChi");
            String kiHoc = request.getParameter("kiHoc");

            MonHoc monHoc = new MonHoc();
            BoMon boMon = boMonService.findById(boMonId != null ? boMonId : "");
            monHoc.setMaMonHoc(maMonHoc);
            monHoc.setTenMonHoc(tenMonHoc);
            monHoc.setTrangThai(String.valueOf(1));
            monHoc.setBoMon(boMon);
            monHoc.setTinChi(Integer.parseInt(tinChi));
            KiHoc kiHocE = kiHocService.findById(Integer.parseInt(kiHoc));
            monHoc.setKiHoc(kiHocE);

            monHocService.taoMonHoc(monHoc);
            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("failed");
        }
    }

    @GetMapping(value = "/monhoc/timkiem/{id}")
    public @ResponseBody
    MonHoc search(@PathVariable("id") String id) {
        MonHoc monHoc = monHocService.findById(id);
        monHoc.setLstLopHoc(null);
        monHoc.getBoMon().setLstGiaoVien(null);
        monHoc.getBoMon().setLstMonHoc(null);
        monHoc.getBoMon().setKhoaVien(null);
        monHoc.getKiHoc().setLstMonHoc(null);
        monHoc.getBoMon().setLstSinhVien(null);
        return monHoc;
}

    @PostMapping("/monhoc/cap-nhat")
    public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MonHoc persist = null;
        BoMon boMon = null;
        KiHoc kiHoc = null;
        try {
            persist = monHocService.findById(request.getParameter("maMonHoc"));
            boMon = boMonService.findById(request.getParameter("boMon"));
            kiHoc = kiHocService.findById(Integer.parseInt(request.getParameter("kiHoc")));
            persist.setBoMon(boMon);
            persist.setKiHoc(kiHoc);
            persist.setTenMonHoc(request.getParameter("tenMonHoc"));
            persist.setTinChi(Integer.parseInt(request.getParameter("tinChi")));
            monHocService.update(persist);
            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("failed");
        }
    }
}
