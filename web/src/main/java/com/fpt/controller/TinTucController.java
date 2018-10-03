package com.fpt.controller;

import com.fpt.entity.GiaoVien;
import com.fpt.entity.SinhVien;
import com.fpt.entity.TinTuc;
import com.fpt.entity.User;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.lophoc.LopHocService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.thongbao.ThongBaoService;
import com.fpt.services.tintuc.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TinTucController {

    @Autowired
    private TinTucService tinTucService;
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private ThongBaoService thongBaoService;
    @Autowired
    private LopHocService lopHocService;

    @GetMapping("/tintuc")
    public String index(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        List<TinTuc> lstTinTuc = tinTucService.findAllAvailable();
        model.addAttribute("lstTinTuc", lstTinTuc);
        if (user.getGiaoVien() != null) {
            GiaoVien giaoVien = giangVienService.findById(user.getGiaoVien().getMaGiaoVien());
            model.addAttribute("soLuongTBChuaXem", thongBaoService.soLuongTbChuaXemGV(user.getGiaoVien().getMaGiaoVien()));
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatGV(user.getGiaoVien().getMaGiaoVien()));
            model.addAttribute("giaoVien", giaoVien);
            model.addAttribute("user", user);
        }
        if (user.getSinhVien() != null) {
            SinhVien sinhVien = sinhVienService.findById(user.getSinhVien().getMaSinhVien());
            model.addAttribute("sinhVien", sinhVien);
            model.addAttribute("soLuongTBChuaXem", thongBaoService.soLuongTbChuaXemSV(user.getSinhVien().getMaSinhVien()));
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatSV(user.getSinhVien().getMaSinhVien()));
            model.addAttribute("user", user);
            model.addAttribute("listLopHoc", lopHocService.listLopHocSinhVien(user.getSinhVien().getMaSinhVien()));
        }
        //

        return "tintuc/tintuc";
    }

    @GetMapping("/tintuc/find/{id}")
    public String findById(@PathVariable("id") Integer id, Model model, HttpSession session){
        TinTuc tinTuc = tinTucService.findById(id);
        model.addAttribute("tinTuc", tinTuc);
        User user = (User) session.getAttribute("userInfo");
        if (user.getGiaoVien() != null) {
            GiaoVien giaoVien = giangVienService.findById(user.getGiaoVien().getMaGiaoVien());
            model.addAttribute("soLuongTBChuaXem", thongBaoService.soLuongTbChuaXemGV(user.getGiaoVien().getMaGiaoVien()));
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatGV(user.getGiaoVien().getMaGiaoVien()));
            model.addAttribute("giaoVien", giaoVien);
            model.addAttribute("user", user);
        }
        if (user.getSinhVien() != null) {
            SinhVien sinhVien = sinhVienService.findById(user.getSinhVien().getMaSinhVien());
            model.addAttribute("sinhVien", sinhVien);
            model.addAttribute("soLuongTBChuaXem", thongBaoService.soLuongTbChuaXemSV(user.getSinhVien().getMaSinhVien()));
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatSV(user.getSinhVien().getMaSinhVien()));
            model.addAttribute("user", user);
            model.addAttribute("listLopHoc", lopHocService.listLopHocSinhVien(user.getSinhVien().getMaSinhVien()));
        }
        return "tintuc/view";
    }

}
