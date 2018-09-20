package com.fpt.controller.tuonglop;

import com.fpt.entity.LopHoc;
import com.fpt.entity.SinhVien;
import com.fpt.entity.User;
import com.fpt.services.lophoc.LopHocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Posted from Sep 13, 2018, 3:53 PM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Controller
public class TuongLopController {
    @Autowired
    private LopHocService lopHocService;
    private final Logger logger = LoggerFactory.getLogger(TuongLopController.class);

    @RequestMapping("/tuonglop")
    public String tuongLop(HttpServletRequest request, HttpSession session, Model model) {
        User user= (User) session.getAttribute("userInfo");
        model.addAttribute("listLopHoc", lopHocService.listLopHocSinhVien(user.getSinhVien().getMaSinhVien()));
        return "tuonglop/tuonglop";
    }
}
