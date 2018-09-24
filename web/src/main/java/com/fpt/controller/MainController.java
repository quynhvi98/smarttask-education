package com.fpt.controller;

import com.fpt.entity.GiaoVien;
import com.fpt.entity.User;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.thongbao.ThongBaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    private ThongBaoService thongBaoService;
    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @RequestMapping("/")
    public String index(HttpSession session, Model model) {
        User userInfo = (User) session.getAttribute("userInfo");
        model.addAttribute("user", userInfo);
        if(userInfo.getSinhVien()!= null){
            return "trangchusv";
        }
        if(userInfo.getGiaoVien() != null){
            model.addAttribute("moiNhat",thongBaoService.thongBaoMoiNhat(userInfo.getGiaoVien().getMaGiaoVien()));
            GiaoVien giaoVien = giangVienService.findById(userInfo.getGiaoVien().getMaGiaoVien());
            model.addAttribute("giaoVien", giaoVien);
            return "trangchu";

        }
        return null;
    }


    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/register")
    public String getRegistration() {
        return "registration";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping ("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }


}
