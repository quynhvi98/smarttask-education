package com.fpt.controller;

import com.fpt.entity.SystemLog;
import com.fpt.services.systemlog.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoggerController {
    @Autowired
    private SystemLogService systemLogService;

    @GetMapping("/loggersystem")
    public String base(Model model){
        List<SystemLog> lstSystemLog = systemLogService.findAll();
        model.addAttribute("lstSystemLog", lstSystemLog);
        return "systemlog/systemlog";
    }
}
