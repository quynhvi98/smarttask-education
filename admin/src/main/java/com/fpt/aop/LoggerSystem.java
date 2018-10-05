package com.fpt.aop;

import com.fpt.entity.SystemLog;
import com.fpt.entity.User;
import com.fpt.services.systemlog.SystemLogService;
import com.fpt.services.user.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
public class LoggerSystem {

    @Autowired
    SystemLogService systemLogService;

    @After("execution(* com.fpt.controller.KhoaVienController.addKhoaVien(..))"
            + "|| execution(* com.fpt.controller.KhoaVienController.editKhoaVien(..))"
            + "|| execution(* com.fpt.controller.BoMonController.addBoMon(..))"
            + "|| execution(* com.fpt.controller.BoMonController.editBoMon(..))"
            + "|| execution(* com.fpt.controller.LopHocController.addLopHoc(..))"
            + "|| execution(* com.fpt.controller.MonHocController.addMonHoc(..))"
            + "|| execution(* com.fpt.controller.MonHocController.editMonHoc(..))"
            + "|| execution(* com.fpt.controller.TinTucController.addNews(..))"
            + "|| execution(* com.fpt.controller.TinTucController.editNews(..))")
    public void doLogkhoaVien(JoinPoint point) {
        try {
            String methodName = point.getSignature().getName();
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String logContent = null;
            switch (methodName) {
                case "addKhoaVien":
                    String tenVien = ((HttpServletRequest) point.getArgs()[0]).getParameter("maVien");
                    logContent = "Thêm mới viện '"+tenVien+"'";
                    break;
                case "editKhoaVien":
                    String tenVienUpdate = ((HttpServletRequest) point.getArgs()[0]).getParameter("maVien");
                    logContent = "Cập nhật viện '"+tenVienUpdate+"'";
                    break;
                case "addBoMon":
                    String boMon = ((HttpServletRequest) point.getArgs()[0]).getParameter("tenNganh");
                    logContent = "Thêm mới ngành '"+boMon+"'";
                    break;
                case "editBoMon":
                    String boMonUpdate = ((HttpServletRequest) point.getArgs()[0]).getParameter("tenNganh");
                    logContent = "Cập nhật ngành '"+boMonUpdate+"'";
                    break;
                case "addLopHoc":
                    String maLop = ((HttpServletRequest) point.getArgs()[0]).getParameter("maLop");
                    logContent = "Thêm mới lớp học '"+maLop+"'";
                    break;
                case "addMonHoc":
                    String monHoc = ((HttpServletRequest) point.getArgs()[0]).getParameter("tenMonHoc");
                    logContent = "Thêm mới môn học '"+monHoc+"'";
                    break;
                case "editMonHoc":
                    String monHocUpdate = ((HttpServletRequest) point.getArgs()[0]).getParameter("tenNganh");
                    logContent = "Cập nhật môn học '"+monHocUpdate+"'";
                    break;
                case "addNews":
                    String tinTuc = ((HttpServletRequest) point.getArgs()[0]).getParameter("title");
                    logContent = "Thêm mới tin tức '"+tinTuc+"'";
                    break;
                case "editNews":
                    String tinTucUpdate = ((HttpServletRequest) point.getArgs()[0]).getParameter("title");
                    logContent = "Cập nhật tin tức '"+tinTucUpdate+"'";
                    break;
                default:
                    break;
            }

            SystemLog systemLog = new SystemLog();
            systemLog.setContent(logContent);
//            systemLog.setUser(userInfo);
            systemLogService.save(systemLog);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
