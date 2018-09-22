package com.fpt.controller;

import com.fpt.entity.User;
import com.fpt.repositories.user.UserDao;
import com.fpt.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Posted from Sep 12, 2018, 10:21 PM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Controller
public class UserInfoController {
    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;


    @RequestMapping("/info")
    public String userProfile(HttpSession session, HttpServletRequest request, Model model) {
        User userInfo = (User) session.getAttribute("userInfo");
        if(userInfo.getSinhVien()!= null){
            model.addAttribute("sinhvien", userInfo);
            model.addAttribute("user", userInfo);
            return "info/sinhvien";
        }
        if(userInfo.getGiaoVien() != null){
            model.addAttribute("giangvien",userInfo);
            model.addAttribute("user", userInfo);
            return "info/giangvien";
        }
        return null;
    }


}
