package com.fpt.controller.StudentController;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public class StudentController {

    @GetMapping("/register-class")
    public String registerclass() {
        return "student/register_class";
    }

    @GetMapping("/register-member")
    public String registermember() {
        return "student/register-member";
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
