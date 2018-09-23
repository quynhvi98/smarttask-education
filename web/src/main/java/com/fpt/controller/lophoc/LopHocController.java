package com.fpt.controller.lophoc;

import com.fpt.controller.UserInfoController;
import com.fpt.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Posted from Sep 23, 2018, 12:44 AM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Controller
public class LopHocController {
    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @RequestMapping("/giangviendaylop")
    public String userProfile(HttpSession session, HttpServletRequest request, Model model) {
        User userInfo = (User) session.getAttribute("userInfo");

        model.addAttribute("user", userInfo);
        return "giaovien_giangday/lopgiangday";
    }
}
