package com.fpt.controller.mail;

import com.fpt.controller.UserInfoController;
import com.fpt.entity.GiaoVien;
import com.fpt.entity.ThongBao;
import com.fpt.entity.User;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.monhoc.MonHocService;
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

    @RequestMapping("/mailbox")
    public String mail(HttpServletRequest request, Model model ,HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        model.addAttribute("user",user);
        model.addAttribute("listThongBao", thongBaoService.listThongBaoGv(user.getUserName()) );
        model.addAttribute("moiNhat",thongBaoService.thongBaoMoiNhat(user.getGiaoVien().getMaGiaoVien()));
        GiaoVien giaoVien = giangVienService.findById(user.getGiaoVien().getMaGiaoVien());
        model.addAttribute("giaoVien", giaoVien);
        return "mail/mailbox";
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
        ThongBao thongBao=thongBaoService.findById(id);
        thongBao.setStatus("true");
        thongBaoService.themThongBao(thongBao);
        GiaoVien giaoVien = giangVienService.findById(user.getGiaoVien().getMaGiaoVien());
        model.addAttribute("giaoVien", giaoVien);
        return "mail/read-mail";
    }
}
