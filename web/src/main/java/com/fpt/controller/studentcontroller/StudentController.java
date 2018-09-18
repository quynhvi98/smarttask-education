package com.fpt.controller.studentcontroller;

import com.fpt.entity.LopHoc;
import com.fpt.entity.PheDuyet;
import com.fpt.entity.User;
import com.fpt.services.lophoc.LopHocService;
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
import java.util.LinkedList;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private LopHocService lopHocService;
    @GetMapping("/register-class")
    public String registerclass(Model model) {
        model.addAttribute("lopHoc", new LopHoc());
        model.addAttribute("listLopHoc", lopHocService.listLopHoc());
        model.addAttribute("listMonHoc",lopHocService.listMonHoc());
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
        model.addAttribute("listMonHoc",lopHocService.listMonHoc());
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
        lopHocService.createPheDuyet(pheDuyet);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/api/member")
    public ResponseEntity<?> getSearchResultViaAjax(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String input = request.getParameter("search");
        List<Member> s=new LinkedList<>();
        if(session.getAttribute("listmember")!= null){
            s= (List<Member>) session.getAttribute("listmember");
            System.out.println(s.size());
            Member member=new Member();
            member.setName(input);
            if(s.contains(member)){
                s.remove(member);
            }else {
                s.add(member);
            }
            session.removeAttribute("listmember");
            session.setAttribute("listmember",s);
        }else {
            Member member=new Member();
            member.setName(input);
            s.add(member);
            session.setAttribute("listmember",s);
        }
        return ResponseEntity.ok(s);
    }
}
