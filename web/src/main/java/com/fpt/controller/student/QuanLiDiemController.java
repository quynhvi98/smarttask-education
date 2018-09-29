package com.fpt.controller.student;

import com.fpt.controller.UserInfoController;
import com.fpt.entity.Diem;
import com.fpt.entity.SinhVien;
import com.fpt.entity.User;
import com.fpt.services.diem.DiemService;
import com.fpt.services.sinhvien.SinhVienService;
import org.joda.time.DateMidnight;
import org.joda.time.Months;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Posted from Sep 27, 2018, 3:25 PM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Controller
public class QuanLiDiemController {
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private DiemService diemService;
    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Component
    public class ThymeMath {
        public int ceil(int a, int b) {
            return a+b;
        }
    }
    @RequestMapping("/thongtindiemvakihoc")
    public String userProfile(HttpSession session, HttpServletRequest request, Model model) {
        User userInfo = (User) session.getAttribute("userInfo");
        SinhVien sinhVien = sinhVienService.findById(userInfo.getSinhVien().getMaSinhVien());
        model.addAttribute("sinhVien", sinhVien);
        model.addAttribute("user", userInfo);
        model.addAttribute("diem", diemService.listDiemKi(userInfo.getSinhVien().getMaSinhVien(),"1"));
        Diem diem=new Diem();
        List<String> ki=new LinkedList<>();
        for (int i=1;i<=getKiHoc(userInfo.getSinhVien().getNgayNhapHoc());i++){
            ki.add(String.valueOf(i));
        }
        model.addAttribute("ki", ki);

        return "student/thongtindiemvakihoc";
    }



    @RequestMapping("/thongtinki")
    public String ttki(HttpSession session, HttpServletRequest request, Model model) {
        String kihoc = request.getParameter("ki");
        System.out.println("kì "+kihoc);
        User userInfo = (User) session.getAttribute("userInfo");
        SinhVien sinhVien = sinhVienService.findById(userInfo.getSinhVien().getMaSinhVien());
        model.addAttribute("sinhVien", sinhVien);
        model.addAttribute("user", userInfo);
        model.addAttribute("diem", diemService.listDiemKi(userInfo.getSinhVien().getMaSinhVien(),kihoc));

        Diem diem=new Diem();
        List<String> ki=new LinkedList<>();
        for (int i=1;i<=getKiHoc(userInfo.getSinhVien().getNgayNhapHoc());i++){
            ki.add(String.valueOf(i));
        }
        model.addAttribute("ki", ki);
        return "student/thongtindiemvakihoc";
    }




    private int getKiHoc(Date ngaynhaphoc) {
        DateMidnight start = new DateMidnight(ngaynhaphoc);
        DateMidnight end = new DateMidnight(new Date());
        int months = Months.monthsBetween(start,end).getMonths();
        int sothang = months / 6;
        return sothang+1;
    }
}
