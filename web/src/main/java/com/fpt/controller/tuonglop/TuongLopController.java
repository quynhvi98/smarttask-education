package com.fpt.controller.tuonglop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Posted from Sep 13, 2018, 3:53 PM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Controller
public class TuongLopController {
    private final Logger logger = LoggerFactory.getLogger(TuongLopController.class);

    @RequestMapping("/tuonglop")
    public String tuongLop(HttpServletRequest request, Model model) {
        return "tuonglop/tuonglop";
    }
}
