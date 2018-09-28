package com.fpt.controller.student;

import com.fpt.controller.UserInfoController;
import com.fpt.entity.SinhVien;
import com.fpt.entity.User;
import com.fpt.services.sinhvien.SinhVienService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Posted from Sep 27, 2018, 3:25 PM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Controller
public class QuanLiDiemController {
    @Autowired
    private SinhVienService sinhVienService;
    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @RequestMapping("/thongtindiemvakihoc")
    public String userProfile(HttpSession session, HttpServletRequest request, Model model) {
        User userInfo = (User) session.getAttribute("userInfo");
        SinhVien sinhVien = sinhVienService.findById(userInfo.getSinhVien().getMaSinhVien());
        model.addAttribute("sinhVien", sinhVien);
        model.addAttribute("user", userInfo);
        return "student/thongtindiemvakihoc";
    }
}
