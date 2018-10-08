package com.fpt.controller;

import com.fpt.entity.BoMon;
import com.fpt.entity.KiHoc;
import com.fpt.entity.ThongKe;
import com.fpt.services.bomon.BoMonService;
import com.fpt.services.kihoc.KiHocService;
import com.fpt.services.thongke.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ThongKeController {
    @Autowired
    ThongKeService thongKeService;

    @GetMapping("/thongke")
    public String base(Model model, HttpServletRequest request) {
        String kiHocStr = request.getParameter("kiHoc");
        List<ThongKe> lstThongKe = thongKeService.getThongKe(kiHocStr != null ? kiHocStr : "1");
        model.addAttribute("lstThongKe", lstThongKe);
        model.addAttribute("kiHoc", kiHocStr != null ? kiHocStr : "1");
        return "thongke/thongke";
    }
}
