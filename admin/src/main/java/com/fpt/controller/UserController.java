package com.fpt.controller;

import com.fpt.entity.*;
import com.fpt.services.bomon.BoMonService;
import com.fpt.services.role.RoleService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private BoMonService boMonService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SinhVienService sinhVienService;

    @GetMapping("/user")
    public String base(Model model) {
        List<BoMon> lstBoMon = boMonService.findAll();
        List<User> lstUser = userService.findAll();
        model.addAttribute("lstBoMon", lstBoMon);
        model.addAttribute("lstUser", lstUser);
        return "/user/user";
    }

    @PostMapping("/user/them-moi")
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String memberId = request.getParameter("memberId");
        String userName = request.getParameter("userName");
        String userPassWord = request.getParameter("userPassWord");
        String userPhone = request.getParameter("userPhone");
        String userEmail = request.getParameter("userEmail");
        String fullName = request.getParameter("fullName");
        String userGender = request.getParameter("userGender");
        String userDOB = request.getParameter("userDOB");
        String userAddress = request.getParameter("userAddress");

        String memberType = request.getParameter("memberType");
        String boMon = request.getParameter("boMon");
        String hocHam = request.getParameter("hocHam");
        String kinhNghiem = request.getParameter("kinhNghiem");

        User user = new User(userName, passwordEncoder.encode(userPassWord), fullName, userEmail, userPhone, userAddress, userGender, userDOB);
        Role role = roleService.findById(memberType);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        try {
            if (memberType.equals("sv01")) {
                SinhVien sinhVien = new SinhVien();
                sinhVien.setMaSinhVien(memberId);
                userService.createStudentAccount(user, sinhVien);
            } else if (memberType.equals("gv01")) {
                GiaoVien giaoVien = new GiaoVien();
                giaoVien.setBoMon(boMonService.findById(boMon));
                giaoVien.setHocHam(hocHam);
                giaoVien.setMaGiaoVien(memberId);
                giaoVien.setMoTa(kinhNghiem);

                userService.createTeacherAccount(user, giaoVien);
            } else if (memberType.equals("ad01")) {
                userService.createAdminAccount(user);
            }

            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("fail");
        }
    }

    @PostMapping("/user/reset-password")
    public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userService.findUserByUserName(request.getParameter("userName"));
        user.setUserPassWord(passwordEncoder.encode("123456"));
        try {
            userService.update(user);
            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("failed");
        }
    }
}
