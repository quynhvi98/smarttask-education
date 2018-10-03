package com.fpt.controller.tuonglop;

import com.fpt.entity.*;
import com.fpt.services.baidang.BaiDangService;
import com.fpt.services.binhluan.BinhLuanService;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.like.LikeService;
import com.fpt.services.lophoc.LopHocService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.tailieu.TaiLieuService;
import com.fpt.services.thongbao.ThongBaoService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private LikeService likeService;
    @Autowired
    private TaiLieuService taiLieuService;

    private final Logger logger = LoggerFactory.getLogger(TuongLopController.class);

    private String maLop = null;

    @RequestMapping("/tuonglop/{maLop}")
    public String tuongLop(@PathVariable("maLop") String maLop, HttpSession session, Model model) {
        this.maLop = maLop;
        User user = (User) session.getAttribute("userInfo");
        List<BaiDang> lstBaiDang = baiDangService.findByMaLop(maLop);
        List<Integer> lstPostId = new ArrayList<>();
        List<Like> lstLike = likeService.getLikeByUser(user.getUserName());
        for (Like like : lstLike) {
            lstPostId.add(like.getBaiDang().getPostId());
        }
        List<TaiLieu> lstTaiLieu = taiLieuService.findAllByMaLop(maLop);
        LopHoc lopHoc = lopHocService.findById(maLop);
        String[] ngayHoc = lopHoc.getNgayHoc().split(",");
        String[] caHoc = lopHoc.getCaHoc().split(",");

        model.addAttribute("lstPostId", lstPostId);
        model.addAttribute("lstBaiDang", lstBaiDang);
        model.addAttribute("user", user);
        model.addAttribute("lstTaiLieu", lstTaiLieu);
        model.addAttribute("lopHoc", lopHoc);
        model.addAttribute("ngayHoc", ngayHoc);
        model.addAttribute("caHoc", caHoc);
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

        if (!file.isEmpty()) {
            suffix = file.getOriginalFilename().split("\\.")[1];
            String fileName = System.currentTimeMillis() + "." + suffix;
            upFile(file, fileName, "post");
            baiDang.setFileName(file.getOriginalFilename());
            baiDang.setFileRealName(fileName);
        }
        baiDang.setStatus(1);
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

    @PostMapping("/tuonglop/add-document")
    public String addDoc(@RequestParam("docFile") MultipartFile file) throws IOException {
        String suffix = null;
        if (!file.isEmpty()) {
            suffix = file.getOriginalFilename().split("\\.")[1];
            String fileName = System.currentTimeMillis() + "." + suffix;
            upFile(file, fileName, "doc");
            TaiLieu taiLieu = new TaiLieu();
            taiLieu.setFileName(file.getOriginalFilename());
            taiLieu.setFileRealName(fileName);
//            taiLieu.setDescription();
            LopHoc lopHoc = lopHocService.findById(maLop);
            taiLieu.setLopHoc(lopHoc);
            taiLieu.setStatus(1);
            taiLieuService.save(taiLieu);
        }
        return "redirect:/tuonglop/" + maLop;
    }


    @RequestMapping("/tuonglop/download-post-file/{postId}")
    public void downloadPostFile(HttpServletResponse response, @PathVariable("postId") Integer postId) throws IOException {
        BaiDang baiDang = baiDangService.findById(postId);
        downloadFile(response, baiDang.getFileName(), baiDang.getFileRealName(), "post");
    }

    @RequestMapping("/tuonglop/download-doc-file/{docId}")
    public void downloadDocFile(HttpServletResponse response, @PathVariable("docId") Integer docId) throws IOException {
        TaiLieu taiLieu = taiLieuService.findById(docId);
        downloadFile(response, taiLieu.getFileName(), taiLieu.getFileRealName(), "doc");
    }

    @RequestMapping("/tuonglop/like/{postid}")
    public void doLike(@PathVariable("postid") Integer postId, HttpSession session, HttpServletResponse response) throws IOException {
        User user = (User) session.getAttribute("userInfo");
        String result = likeService.doLike(user.getUserName(), postId);
        response.getWriter().println(result);
    }

    @GetMapping("/tuonglop/deletepost/{postid}")
    public String deletePost(@PathVariable("postid") Integer postId) {
        baiDangService.delete(postId);
        return "redirect:/tuonglop/" + maLop;
    }

    public void upFile(MultipartFile file, String fileName, String location) throws IOException {
        String path = null;
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            path = ResourceUtils.getFile("classpath:" + location).getPath();
            BufferedOutputStream bout = new BufferedOutputStream(
                    new FileOutputStream(path + "/" + fileName));
            bout.write(bytes);
            bout.flush();
            bout.close();

        } else {
            System.out.println("File is empty!");
        }
    }

    public void downloadFile(HttpServletResponse response, String fileName, String fileRealName, String location) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + location + "/" + fileRealName).getAbsoluteFile();
        byte[] fileContent = Files.readAllBytes(file.toPath());
        Path newFile = null;
        try {
            Path newPath = Paths.get("./../" + fileName);
            if (!Files.exists(newPath)) {
                newFile = Files.createFile(newPath);
            } else {
                newFile = newPath;
            }
            if (!Files.exists(newFile)) {
                String errorMessage = "Sorry. The file you are looking for does not exist";
                System.out.println(errorMessage);
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
                outputStream.close();
                return;
            }
            Files.write(newFile, fileContent, StandardOpenOption.APPEND);
            InputStream inputStream = Files.newInputStream(newFile);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + newFile.getFileName() + "\"");
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            String errorMessage = "Error!";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }
    }
}
