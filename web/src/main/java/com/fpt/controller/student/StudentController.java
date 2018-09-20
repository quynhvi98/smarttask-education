package com.fpt.controller.student;

import com.fpt.entity.*;
import com.fpt.services.bomon.BoMonService;
import com.fpt.services.lophoc.LopHocService;
import com.fpt.services.monhoc.MonHocService;
import com.fpt.services.pheduyetlop.PheDuyetLopService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        model.addAttribute("lopHoc", new LopHoc());
        model.addAttribute("listLopHoc", lopHocService.listLopHoc());
        model.addAttribute("listMonHoc",monHocService.listMonHoc());
        User user= (User) session.getAttribute("userInfo");
        PheDuyet pheDuyet=new PheDuyet();
        model.addAttribute("listPheDuyetSV",pheDuyetLopService.listPheDuyetTheoSV(user.getUserName()));
        return "student/register_class";
    }

    @GetMapping("/register-member")
    public String registermember() {
        return "student/register-member";
    }

    @PostMapping("/student/search")
    public String search(HttpServletRequest request, HttpSession session, HttpServletResponse response,Model model) {
        String input = request.getParameter("input");
        String loai=request.getParameter("loai");
        String bomon=request.getParameter("bomon");
        model.addAttribute("listMonHoc",monHocService.listMonHoc());
        model.addAttribute("lopHoc", new LopHoc());
        if(loai.equals("giaovien")) {
            model.addAttribute("listLopHoc", lopHocService.searchGiaoVien(input, bomon));
        }else {
            model.addAttribute("listLopHoc", lopHocService.searchLop(input, bomon));
        }
        return "student/register_class";
    }

    @PostMapping("/api/dangkylop")
    public ResponseEntity<?> dangKyLop(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String id = request.getParameter("id");
        LopHoc hoc=lopHocService.findById(id);
        User user= (User) session.getAttribute("userInfo");
        PheDuyet pheDuyet=new PheDuyet();
        pheDuyet.setGiaoVien(hoc.getGiaoVien());
        pheDuyet.setLopHoc(hoc);
        pheDuyet.setStatus("false");
        pheDuyet.setSinhVien(user.getSinhVien());
        pheDuyetLopService.createPheDuyet(pheDuyet);
//        LopHoc hoc1=lopHocService.findById(id);
//        Set<SinhVien> sinhVienSet=hoc1.getSinhViens();
//        sinhVienSet.add(user.getSinhVien());
//        hoc1.setMaLop(id);
//        hoc1.setSinhViens(sinhVienSet);
//        lopHocService.createlopSV(hoc1);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/api/member")
    public ResponseEntity<?> getSearchResultViaAjax(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String input = request.getParameter("search");
        List<SinhVien> s=new LinkedList<>();
        if(session.getAttribute("listmember")!= null){
            s= (List<SinhVien>) session.getAttribute("listmember");
            SinhVien sinhVien=sinhVienService.getSinhVienId(input);
            if(s.contains(sinhVien)){
                s.remove(sinhVien);
            }else {
                s.add(sinhVien);
            }
            session.removeAttribute("listmember");
            session.setAttribute("listmember",s);
        }else {
            SinhVien sinhVien=sinhVienService.getSinhVienId(input);
            s.add(sinhVien);
            session.setAttribute("listmember",s);
        }
        return ResponseEntity.ok(s);
    }

    @GetMapping("/lop")
    public String getMember(Model model,HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String id = request.getParameter("malop");
        LopHoc hoc=lopHocService.findById(id);
        Set<SinhVien>sinhViens =hoc.getSinhViens();
        List<SinhVien> lsv = new LinkedList(sinhViens);
        model.addAttribute("listSinhVien", lsv);
        User user= (User) session.getAttribute("userInfo");
        model.addAttribute("listLopHoc", lopHocService.listLopHocSinhVien(user.getSinhVien().getMaSinhVien()));
        session.removeAttribute("listmember");
        return "tuonglop/tuonglop";
    }
}
