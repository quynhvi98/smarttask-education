package com.fpt.controller;

import com.fpt.entity.Role;
import com.fpt.entity.User;
import com.fpt.services.role.RoleService;
import com.fpt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {


    @GetMapping("/user")
    public String userManagement(){
        return "/user/user";
    }


}
