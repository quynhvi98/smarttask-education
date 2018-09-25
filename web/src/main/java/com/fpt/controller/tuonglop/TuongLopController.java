package com.fpt.controller.tuonglop;

import com.fpt.entity.GiaoVien;
import com.fpt.entity.LopHoc;
import com.fpt.entity.SinhVien;
import com.fpt.entity.User;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.lophoc.LopHocService;
import com.fpt.services.thongbao.ThongBaoService;
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
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    private ThongBaoService thongBaoService;
    private final Logger logger = LoggerFactory.getLogger(TuongLopController.class);

    @RequestMapping("/tuonglop")
    public String tuongLop(HttpServletRequest request, HttpSession session, Model model) {
        User user = (User) session.getAttribute("userInfo");
        if(user.getGiaoVien() != null){
            GiaoVien giaoVien = giangVienService.findById(user.getGiaoVien().getMaGiaoVien());
            model.addAttribute("moiNhat",thongBaoService.thongBaoMoiNhat(user.getGiaoVien().getMaGiaoVien()));
            model.addAttribute("giaoVien", giaoVien);
            return "tuonglop/tuonglopgv";
        }
        if(user.getSinhVien()!= null){
            model.addAttribute("listLopHoc", lopHocService.listLopHocSinhVien(user.getSinhVien().getMaSinhVien()));
            return "tuonglop/tuonglop";
        }
        return null;
    }
}
