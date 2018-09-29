package com.fpt.controller;

import com.fpt.entity.GiaoVien;
import com.fpt.entity.SinhVien;
import com.fpt.entity.User;
import com.fpt.repositories.user.UserDao;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
    private GiangVienService giangVienService;
    @Autowired
    private UserDao userDao;
    @Autowired
    ServletContext servletContext;
    @Autowired
    SinhVienService sinhVienService;

    @RequestMapping("/info")
    public String userProfile(HttpSession session, HttpServletRequest request, Model model) {
        User userInfo = (User) session.getAttribute("userInfo");
        User user = new User();
        if(userInfo.getUserName() != null) {
            user = userService.findUserByUserName(userInfo.getUserName());
        }

        if(user.getSinhVien()!= null){
            SinhVien sinhVien = sinhVienService.findById(userInfo.getSinhVien().getMaSinhVien());
            model.addAttribute("sinhVien", sinhVien);
            model.addAttribute("user", user);
            return "info/sinhvien";
        }
        if(userInfo.getGiaoVien() != null){
            GiaoVien giaoVien = giangVienService.findById(userInfo.getGiaoVien().getMaGiaoVien());
            model.addAttribute("giangvien",user);
            model.addAttribute("user", user);
            model.addAttribute("giaoVien", giaoVien);
            return "info/giangvien";
        }
        return null;
    }

    @PostMapping("/info/userUpdateImage")
    public void userUpdateImage( HttpSession session, MultipartFile avatarFile, HttpServletRequest request) throws IOException {
        User userInfo = (User) session.getAttribute("userInfo");
        User user = userService.findUserByUserName(userInfo.getUserName());
        //TODO: fix root path by environment
        try {
            String fileName =  UUID.randomUUID().toString().substring(0,8) + avatarFile.getOriginalFilename();
            File newFile = new File("C:\\temp\\" + fileName);
            if (newFile.createNewFile()) {
                FileCopyUtils.copy(avatarFile.getBytes(), newFile);
            }
            String avatar = "http://127.0.0.1:8887/" + fileName;
            user.setUserAvatar(avatar);
            userService.update(user);
//            String webappRoot = servletContext.getRealPath("/");
//            try {
//                if (!avatarFile.isEmpty()) {
//                    BufferedImage src = ImageIO.read(new ByteArrayInputStream(avatarFile.getBytes()));
//                    File destination = new File(webappRoot + "/resources/webapp/img/" + avatarFile.getOriginalFilename()); // something like C:/Users/tom/Documents/nameBasedOnSomeId.png
//                    ImageIO.write(src, "jpg", destination);
//                    String avatar = "/img/" + avatarFile.getOriginalFilename();
//                    ;
//                    user.setUserAvatar(avatar);
//                    userService.update(user);
//                }
            } catch (Exception e) {
                System.out.println("Exception occured" + e.getMessage());
            }
        }
    @PostMapping("/info/update")
    public void userUpdateInfo(User user,HttpServletResponse response, HttpServletRequest request) throws IOException {
        try {
            if (user.getUserName() != null) {
                User userUpdate = userService.findUserByUserName(user.getUserName());
                if (user.getFullName() != null) {
                    userUpdate.setFullName(user.getFullName());
                }
                if (user.getUserAddress() != null) {
                    userUpdate.setUserAddress(user.getUserAddress());
                }
                if (user.getUserDOB() != null) {
                    userUpdate.setUserDOB(user.getUserDOB());
                }
                if (user.getUserEmail() != null) {
                    userUpdate.setUserEmail(user.getUserEmail());
                }
                if (user.getUserPhone() != null) {
                    userUpdate.setUserPhone(user.getUserPhone());
                }
                if (user.getUserGender() != null) {
                    userUpdate.setUserGender(user.getUserGender());
                }
                //Update for Giang Vien
                if (user.getGiaoVien() != null && user.getGiaoVien().getHocHam() != null) {
                    userUpdate.getGiaoVien().setHocHam(user.getGiaoVien().getHocHam());
                }
                if (user.getGiaoVien() != null && user.getGiaoVien().getMoTa() != null) {
                    userUpdate.getGiaoVien().setMoTa(user.getGiaoVien().getMoTa());
                }

                //Update for Sinh Vien
                //Tam thoi chua co thong tin rieng

                userService.update(userUpdate);
            }
            response.getWriter().println("success");
        } catch (Exception e){
            System.out.println(e.getMessage());
            response.getWriter().println("failed");
        }
    }
}
