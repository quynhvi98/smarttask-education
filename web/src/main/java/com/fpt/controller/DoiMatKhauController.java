package com.fpt.controller;

import com.fpt.controller.UserInfoController;
import com.fpt.entity.GiaoVien;
import com.fpt.entity.SinhVien;
import com.fpt.entity.User;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.thongbao.ThongBaoService;
import com.fpt.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Posted from Sep 28, 2018, 10:32 AM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Controller
public class DoiMatKhauController {
    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    private ThongBaoService thongBaoService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @RequestMapping("/doimatkhau")
    public String index(HttpSession session, Model model) {
        User userInfo = (User) session.getAttribute("userInfo");
        model.addAttribute("user", userInfo);
        String passWord= userInfo.getUserPassWord();
        if(passwordEncoder.matches("123456",passWord)){
            System.out.println(true);
        }else {
            System.out.println(false);
        }
        if(userInfo.getSinhVien()!= null){
            model.addAttribute("soLuongTBChuaXem",thongBaoService.soLuongTbChuaXemSV(userInfo.getSinhVien().getMaSinhVien()));
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatSV(userInfo.getSinhVien().getMaSinhVien()));
            SinhVien sinhVien = sinhVienService.findById(userInfo.getSinhVien().getMaSinhVien());
            model.addAttribute("sinhVien", sinhVien);
        }
        if(userInfo.getGiaoVien() != null){
            model.addAttribute("soLuongTBChuaXem",thongBaoService.soLuongTbChuaXemGV(userInfo.getGiaoVien().getMaGiaoVien()));
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatGV(userInfo.getGiaoVien().getMaGiaoVien()));
            GiaoVien giaoVien = giangVienService.findById(userInfo.getGiaoVien().getMaGiaoVien());
            model.addAttribute("giaoVien", giaoVien);
        }
        return "info/doimatkhau";
    }


    @PostMapping("/api/doimatkhau")
    public void dmk(HttpServletRequest request, HttpSession session, HttpServletResponse response, Model model) throws IOException {
        User userInfo = (User) session.getAttribute("userInfo");



        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");

        String passwordRepeat = request.getParameter("passwordRepeat");

        if(passwordRepeat.equals(newPassword)&&passwordEncoder.matches(password,userInfo.getUserPassWord())){
            userInfo.setUserPassWord(passwordEncoder.encode(newPassword));
            userService.update(userInfo);
            response.getWriter().println("success");
        }else {
            response.getWriter().println("error");
        }

    }
}


