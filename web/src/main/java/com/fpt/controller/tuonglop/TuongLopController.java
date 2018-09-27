package com.fpt.controller.tuonglop;

import com.fpt.entity.*;
import com.fpt.services.baidang.BaiDangService;
import com.fpt.services.binhluan.BinhLuanService;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.lophoc.LopHocService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.thongbao.ThongBaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Posted from Sep 13, 2018, 3:53 PM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Controller
public class TuongLopController {
    @Autowired
    private LopHocService lopHocService;
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private ThongBaoService thongBaoService;
    @Autowired
    private BaiDangService baiDangService;
    @Autowired
    private BinhLuanService binhLuanService;

    private final Logger logger = LoggerFactory.getLogger(TuongLopController.class);

    private String maLop = null;

    @RequestMapping("/tuonglop/{maLop}")
    public String tuongLop(@PathVariable("maLop") String maLop, HttpSession session, Model model) {
        this.maLop = maLop;
        User user = (User) session.getAttribute("userInfo");
        List<BaiDang> lstBaiDang = baiDangService.findByMaLop(maLop);
        model.addAttribute("lstBaiDang", lstBaiDang);
        model.addAttribute("user", user);

        if (user.getGiaoVien() != null) {
            GiaoVien giaoVien = giangVienService.findById(user.getGiaoVien().getMaGiaoVien());
            model.addAttribute("soLuongTBChuaXem", thongBaoService.soLuongTbChuaXemGV(user.getGiaoVien().getMaGiaoVien()));
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatGV(user.getGiaoVien().getMaGiaoVien()));
            model.addAttribute("giaoVien", giaoVien);
//            model.addAttribute("user", user);
            return "tuonglop/tuonglopgv";
        }
        if (user.getSinhVien() != null) {
            SinhVien sinhVien = sinhVienService.findById(user.getSinhVien().getMaSinhVien());
            model.addAttribute("sinhVien", sinhVien);
            model.addAttribute("soLuongTBChuaXem", thongBaoService.soLuongTbChuaXemSV(user.getSinhVien().getMaSinhVien()));
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatSV(user.getSinhVien().getMaSinhVien()));
//            model.addAttribute("user", user);
            model.addAttribute("listLopHoc", lopHocService.listLopHocSinhVien(user.getSinhVien().getMaSinhVien()));
            return "tuonglop/tuonglop";
        }
        return null;
    }

    @PostMapping("/tuonglop/addpost")
    public String addPost(@RequestParam("postContent") String postContent, HttpSession session,
                          @RequestParam("upFile") MultipartFile file) throws IOException {
        BaiDang baiDang = new BaiDang();
        baiDang.setContent(postContent);

        User user = (User) session.getAttribute("userInfo");
        baiDang.setUser(user);

        LopHoc lopHoc = lopHocService.findById(maLop);
        baiDang.setLopHoc(lopHoc);

        String suffix = null;

        if (file != null) {
            suffix = file.getOriginalFilename().split("\\.")[1];
            String fileName = System.currentTimeMillis() + "." + suffix;
            upFile(file, fileName);
            baiDang.setImage(fileName);
        }
        baiDangService.save(baiDang);
        return "redirect:/tuonglop/" + maLop;
    }

    @PostMapping("/tuonglop/addcomment")
    public String addComment(@RequestParam("postId") String postId,
                             @RequestParam("content") String content, HttpSession session) {
        BinhLuan binhLuan = new BinhLuan();
        binhLuan.setContent(content);

        User user = (User) session.getAttribute("userInfo");
        binhLuan.setUser(user);

        BaiDang baiDang = baiDangService.findById(Integer.parseInt(postId));
        binhLuan.setBaiDang(baiDang);

        binhLuanService.save(binhLuan);
        return "redirect:/tuonglop/" + maLop;
    }

    public void upFile(MultipartFile file, String fileName) throws IOException {
        String path = null;
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            path = ResourceUtils.getFile("classpath:files/baidang").getPath();
            BufferedOutputStream bout = new BufferedOutputStream(
                    new FileOutputStream(path + "/" + fileName));
            bout.write(bytes);
            bout.flush();
            bout.close();

        } else {
            System.out.println("File is empty!");
        }
    }
}
