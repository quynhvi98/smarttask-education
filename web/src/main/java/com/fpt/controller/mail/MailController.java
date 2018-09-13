package com.fpt.controller.mail;

import com.fpt.controller.UserInfoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Posted from Sep 13, 2018, 11:33 AM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Controller
public class MailController {
    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @RequestMapping("/mailbox")
    public String mail(HttpServletRequest request, Model model) {
        return "mail/mailbox";
    }

    @RequestMapping("/mailbox/compose")
    public String compose(HttpServletRequest request, Model model) {
        return "mail/compose";
    }

    @RequestMapping("/mailbox/read-mail")
    public String readMail(HttpServletRequest request, Model model) {
        return "mail/read-mail";
    }
}
