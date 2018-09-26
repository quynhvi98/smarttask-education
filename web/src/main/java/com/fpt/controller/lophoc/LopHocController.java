package com.fpt.controller.lophoc;

import com.fpt.controller.UserInfoController;
import com.fpt.entity.GiaoVien;
import com.fpt.entity.LopHoc;
import com.fpt.entity.SinhVien;
import com.fpt.entity.User;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.lophoc.LopHocService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.thongbao.ThongBaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Posted from Sep 23, 2018, 12:44 AM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Controller
public class LopHocController {
    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private LopHocService lopHocService;
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private ThongBaoService thongBaoService;
    @RequestMapping("/giangviendaylop/{maLop}")
    public String userProfile(HttpSession session, @PathVariable("maLop") String maLop, HttpServletRequest request, Model model) {
        User userInfo = (User) session.getAttribute("userInfo");
        GiaoVien giaoVien = giangVienService.findById(userInfo.getGiaoVien().getMaGiaoVien());
        LopHoc lopHoc = lopHocService.findById(maLop);
        List<SinhVien> sinhViens = sinhVienService.getListSinhVienbyLopHocId(maLop);
        String[] ngayHoc = lopHoc.getNgayHoc().split(",");
        String[] caHoc = lopHoc.getCaHoc().split(",");
        model.addAttribute("user", userInfo);
        model.addAttribute("giaoVien", giaoVien);
        model.addAttribute("lopHoc", lopHoc);
        model.addAttribute("sinhvien", sinhViens);
        model.addAttribute("ngayHoc", ngayHoc);
        model.addAttribute("caHoc", caHoc);
        if(userInfo.getSinhVien()!= null) {
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatSV(userInfo.getSinhVien().getMaSinhVien()));
        }
        if(userInfo.getGiaoVien()!= null) {
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatGV(userInfo.getGiaoVien().getMaGiaoVien()));
        }
        return "giaovien_giangday/lopgiangday";
    }
}
