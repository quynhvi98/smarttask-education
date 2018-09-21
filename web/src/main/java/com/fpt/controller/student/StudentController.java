package com.fpt.controller.student;

import com.fpt.entity.*;
import com.fpt.services.bomon.BoMonService;
import com.fpt.services.lophoc.LopHocService;
import com.fpt.services.monhoc.MonHocService;
import com.fpt.services.pheduyetlop.PheDuyetLopService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.user.UserService;
import org.joda.time.DateMidnight;
import org.joda.time.Days;
import org.joda.time.Months;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class StudentController {
    @Autowired
    private LopHocService lopHocService;

    @Autowired
    private SinhVienService sinhVienService;

    @Autowired
    private PheDuyetLopService pheDuyetLopService;

    @Autowired
    private MonHocService monHocService;

    @GetMapping("/register-class")
    public String registerclass(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        int hocky = getKyHoc(user.getSinhVien().getNgayNhapHoc());
        model.addAttribute("listMonHoc", monHocService.listMonHocKy(hocky + ""));
        model.addAttribute("hocky", "Danh sách các môn học trong kỳ: " + hocky);
//        model.addAttribute("listPheDuyetSV",pheDuyetLopService.listPheDuyetTheoSV(user.getUserName()));
        return "student/register_class";
    }

    @GetMapping("/register-member")
    public String registermember() {
        return "student/register-member";
    }

    @PostMapping("/student/search")
    public String search(HttpServletRequest request, HttpSession session, HttpServletResponse response, Model model) {
        String input = request.getParameter("input");
        String loai = request.getParameter("loai");
        String bomon = request.getParameter("bomon");
        model.addAttribute("listMonHoc", monHocService.listMonHoc());
        model.addAttribute("lopHoc", new LopHoc());
        if (loai.equals("giaovien")) {
            model.addAttribute("listLopHoc", lopHocService.searchGiaoVien(input, bomon));
        } else {
            model.addAttribute("listLopHoc", lopHocService.searchLop(input, bomon));
        }
        return "student/register_class";
    }

    @PostMapping("/api/dangkylop")
    public void dangKyLop(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        LopHoc hoc = lopHocService.findById(id);
        User user = (User) session.getAttribute("userInfo");
        LopHoc lh01 = lopHocService.findById(id);
        //kiểm tra thời hạn lớp
        int thoihan=checkThoiHan(lh01.getNgayBatDau().toString());
        if (thoihan > -10) {
            response.getWriter().println("quahan");
        } else {
            //Kiểm tra sv đã đăng ký lớp khác cùng môn chưa
            String bomon = lh01.getMonHoc().getMaMonHoc();
            LopHoc lopHoc = lopHocService.getLopHocSvBm(user.getSinhVien().getMaSinhVien(), bomon);
            if (lopHoc != null) {
                response.getWriter().println("trungmon");
            } else {
                //kiểm tra lớp đã đủ sv chưa
                if(lh01.getSinhViens().size()>=50){
                    response.getWriter().println("maxsv");
                }else {
                    //Kiểm tra sv đã tồn tại trong lớp học chưa
                    LopHoc lopHoc01 = lopHocService.getLopHocSV(id, user.getSinhVien().getMaSinhVien());
                    if (lopHoc01 == null) {
                        LopHoc hoc1 = lopHocService.findById(id);
                        Set<SinhVien> sinhVienSet = hoc1.getSinhViens();
                        sinhVienSet.add(user.getSinhVien());
                        hoc1.setMaLop(id);
                        hoc1.setSinhViens(sinhVienSet);
                        lopHocService.createlopSV(hoc1);
                        response.getWriter().println("success");
                    } else {
                        response.getWriter().println("tontai");
                    }
                }
            }
        }
    }

    @PostMapping("/api/member")
    public ResponseEntity<?> getSearchResultViaAjax(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String input = request.getParameter("search");
        List<SinhVien> s = new LinkedList<>();
        if (session.getAttribute("listmember") != null) {
            s = (List<SinhVien>) session.getAttribute("listmember");
            SinhVien sinhVien = sinhVienService.getSinhVienId(input);
            if (s.contains(sinhVien)) {
                s.remove(sinhVien);
            } else {
                s.add(sinhVien);
            }
            session.removeAttribute("listmember");
            session.setAttribute("listmember", s);
        } else {
            SinhVien sinhVien = sinhVienService.getSinhVienId(input);
            s.add(sinhVien);
            session.setAttribute("listmember", s);
        }
        return ResponseEntity.ok(s);
    }

    @GetMapping("/lop")
    public String getMember(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String id = request.getParameter("malop");
        LopHoc hoc = lopHocService.findById(id);
        Set<SinhVien> sinhViens = hoc.getSinhViens();
        List<SinhVien> lsv = new LinkedList(sinhViens);
        model.addAttribute("listSinhVien", lsv);
        User user = (User) session.getAttribute("userInfo");
        model.addAttribute("listLopHoc", lopHocService.listLopHocSinhVien(user.getSinhVien().getMaSinhVien()));
        session.removeAttribute("listmember");
        return "tuonglop/tuonglop";
    }


    private int getKyHoc(String ngaynhaphoc) {
        DateMidnight start = new DateMidnight(ngaynhaphoc);
        DateMidnight end = new DateMidnight(new Date());
        int months = Months.monthsBetween(start,end).getMonths();
        int sothang = months / 6;
        return sothang+1;
    }


    private int checkThoiHan(String ngaybatdau) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(ngaybatdau);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newdate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        DateMidnight start = new DateMidnight(newdate);
        DateMidnight end = new DateMidnight(new Date());
        int days = Days.daysBetween(start, end).getDays();
        System.out.println("số ngày"+days);
        return days;
    }


}
