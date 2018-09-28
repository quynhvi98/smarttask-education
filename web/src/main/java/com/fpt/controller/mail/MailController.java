package com.fpt.controller.mail;

import com.fpt.controller.UserInfoController;
import com.fpt.entity.GiaoVien;
import com.fpt.entity.SinhVien;
import com.fpt.entity.ThongBao;
import com.fpt.entity.User;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.monhoc.MonHocService;
import com.fpt.services.sinhvien.SinhVienService;
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
 * Posted from Sep 13, 2018, 11:33 AM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Controller
public class MailController {
    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private ThongBaoService thongBaoService;
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    private SinhVienService sinhVienService;

    @RequestMapping("/mailbox")
    public String mail(HttpServletRequest request, Model model ,HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        model.addAttribute("user",user);

        if(user.getSinhVien()!= null){
            model.addAttribute("listThongBao", thongBaoService.listThongBaoSV(user.getUserName()) );
            model.addAttribute("moiNhat",thongBaoService.thongBaoMoiNhatSV(user.getSinhVien().getMaSinhVien()));
            model.addAttribute("soLuongTBChuaXem",thongBaoService.soLuongTbChuaXemSV(user.getSinhVien().getMaSinhVien()));
            SinhVien sinhVien = sinhVienService.getSinhVienId(user.getSinhVien().getMaSinhVien());
            model.addAttribute("sinhVien", sinhVien);
            return "mail/mailbox";
        }
        if(user.getGiaoVien()!= null){
            model.addAttribute("listThongBao", thongBaoService.listThongBaoGv(user.getUserName()) );
            model.addAttribute("moiNhat",thongBaoService.thongBaoMoiNhatGV(user.getGiaoVien().getMaGiaoVien()));
            model.addAttribute("soLuongTBChuaXem",thongBaoService.soLuongTbChuaXemGV(user.getGiaoVien().getMaGiaoVien()));
            GiaoVien giaoVien = giangVienService.findById(user.getGiaoVien().getMaGiaoVien());
            model.addAttribute("giaoVien", giaoVien);
            return "mail/mailbox";
        }

        return null;

    }

    @RequestMapping("/mailbox/compose")
    public String compose(HttpServletRequest request, Model model) {
        return "mail/compose";
    }

    @RequestMapping("/mailbox/read-mail")
    public String readMail(HttpServletRequest request, Model model,HttpSession session) {
        String id = request.getParameter("id");
        User user = (User) session.getAttribute("userInfo");
        model.addAttribute("user",user);
        model.addAttribute("mail",thongBaoService.findById(id));
        if(user.getSinhVien()!= null) {
            SinhVien sinhVien = sinhVienService.getSinhVienId(user.getSinhVien().getMaSinhVien());
            model.addAttribute("sinhVien", sinhVien);
            model.addAttribute("soLuongTBChuaXem",thongBaoService.soLuongTbChuaXemSV(user.getSinhVien().getMaSinhVien()));
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatSV(user.getSinhVien().getMaSinhVien()));
        }
        if(user.getGiaoVien()!= null) {
            GiaoVien giaoVien = giangVienService.findById(user.getGiaoVien().getMaGiaoVien());
            model.addAttribute("giaoVien", giaoVien);
            model.addAttribute("soLuongTBChuaXem",thongBaoService.soLuongTbChuaXemGV(user.getGiaoVien().getMaGiaoVien()));
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatGV(user.getGiaoVien().getMaGiaoVien()));
        }
        ThongBao thongBao=thongBaoService.findById(id);
        thongBao.setStatus("true");
        thongBaoService.themThongBao(thongBao);
        return "mail/read-mail";
    }
}
