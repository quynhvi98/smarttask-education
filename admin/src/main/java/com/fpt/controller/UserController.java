package com.fpt.controller;

import com.fpt.entity.Role;
import com.fpt.entity.User;
import com.fpt.services.role.RoleService;
import com.fpt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @PostMapping("/sign-in")
    public String signIn(User user) {
        user.setUserPassWord(passwordEncoder.encode(user.getUserPassWord()));
        Set<Role> roles = new HashSet<>();
        Role role = roleService.findById("sv01");
        roles.add(role);
        user.setRoles(roles);
        User result = userService.createAccount(user);
        return "redirect:/login";
    }
}
