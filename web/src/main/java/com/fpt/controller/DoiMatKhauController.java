package com.fpt.controller;

import com.fpt.entity.GiaoVien;
import com.fpt.entity.SinhVien;
import com.fpt.entity.User;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.thongbao.ThongBaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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

    @RequestMapping("/doimatkhau")
    public String index(HttpSession session, Model model) {
        User userInfo = (User) session.getAttribute("userInfo");
        model.addAttribute("user", userInfo);
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
}
