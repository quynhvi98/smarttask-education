package com.fpt.controller.tuonglop;

import com.fpt.entity.*;
import com.fpt.services.baidang.BaiDangService;
import com.fpt.services.baitap.BaiTapService;
import com.fpt.services.baitaplon.BaiTapLonService;
import com.fpt.services.binhluan.BinhLuanService;
import com.fpt.services.config.ConfigService;
import com.fpt.services.diem.DiemService;
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
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private BaiTapLonService baiTapLonService;
    @Autowired
    private BaiTapService baiTapService;
    @Autowired
    private ConfigService configService;

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
        model.addAttribute("maLop", maLop);
        if (user.getGiaoVien() != null) {
            List<BaiTap> baiTaps=baiTapService.listBtAndLop(maLop);
            List<SinhVien> sinhViens=sinhVienService.getListSinhVienbyLopHocId(maLop);
            String soLuongSVNopBT=baiTaps.size()+"/"+sinhViens.size();
            List<BaiTap> baiTaps1=new LinkedList<>();
            baiTaps1.addAll(baiTaps);
            for (SinhVien sinhVien:sinhViens) {
                if (baiTaps.size() == 0) {
                    BaiTap baiTap1 = new BaiTap();
                    baiTap1.setSinhVien(sinhVien);
                    baiTaps1.add(baiTap1);
                } else
                    for (BaiTap baiTap : baiTaps) {
                        if (!baiTap.getSinhVien().equals(sinhVien)) {
                            BaiTap baiTap1 = new BaiTap();
                            baiTap1.setSinhVien(sinhVien);
                            baiTaps1.add(baiTap1);
                        }
                    }

            }
            model.addAttribute("baiTap", baiTaps1);
            model.addAttribute("soLuongSVNopBT", soLuongSVNopBT);
            model.addAttribute("baiTapLon", baiTapLonService.findByMaLop(maLop));
            GiaoVien giaoVien = giangVienService.findById(user.getGiaoVien().getMaGiaoVien());
            model.addAttribute("soLuongTBChuaXem", thongBaoService.soLuongTbChuaXemGV(user.getGiaoVien().getMaGiaoVien()));
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatGV(user.getGiaoVien().getMaGiaoVien()));
            model.addAttribute("giaoVien", giaoVien);
            return "tuonglop/tuonglopgv";
        }
        if (user.getSinhVien() != null) {
            SinhVien sinhVien = sinhVienService.findById(user.getSinhVien().getMaSinhVien());
            model.addAttribute("sinhVien", sinhVien);
          BaiTap baiTap=  baiTapService.findBySVAndLop(user.getSinhVien().getMaSinhVien(),maLop);
            BaiTapLon baiTapLon=baiTapLonService.findByMaLop(maLop);
            if(baiTapLon!=null){
                model.addAttribute("timeEnd", baiTapLon.getNgayKetThuc());
                try {
                    model.addAttribute("checkHan",checkHanBaiTap(baiTapLon.getNgayKetThuc()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if(baiTap!=null){
                model.addAttribute("baiTap", baiTap);
            }
            model.addAttribute("soLuongTBChuaXem", thongBaoService.soLuongTbChuaXemSV(user.getSinhVien().getMaSinhVien()));
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatSV(user.getSinhVien().getMaSinhVien()));
            model.addAttribute("baiTap", baiTapService.findBySVAndLop(user.getSinhVien().getMaSinhVien(),maLop));
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

    @GetMapping("/tuonglop/findpost/{postid}")
    public @ResponseBody
    BaiDang findPost(@PathVariable("postid") Integer postId) {
        BaiDang baiDang = baiDangService.findById(postId);
        baiDang.setLopHoc(null);
        baiDang.setLstComment(null);
        baiDang.setLstLike(null);
        baiDang.setUser(null);
        return baiDang;
    }

    @PostMapping("/tuonglop/post/update")
    public String updatePost(BaiDang baiDang) {
        BaiDang baiDangUpdate = baiDangService.findById(baiDang.getPostId());
        baiDangUpdate.setContent(baiDang.getContent());
        baiDangService.save(baiDangUpdate);
        return "redirect:/tuonglop/" + maLop;
    }

    public void upFile(MultipartFile file, String fileName, String location) throws IOException {
        String path = null;
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            path = ResourceUtils.getFile("classpath:" + location).getPath();
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path + "/" + fileName));
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

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @PostMapping("/api/taobt")
    public void getSearchResultViaAjax(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String baiTap = request.getParameter("baiTap");
        String ngayBatDau = request.getParameter("ngayBatDau");
        String hanNop = request.getParameter("hanNop");
        String maLop = request.getParameter("maLop");
        String timeStart = request.getParameter("timeStart");
        String timeEnd = request.getParameter("timeEnd");
        User user = (User) session.getAttribute("userInfo");
        response.getWriter().println("success");
        ThongBaoSocket message = new ThongBaoSocket();
        List<SinhVien> sinhViens = sinhVienService.getListSinhVienbyLopHocId(maLop);
        BaiTapLon baiTap1 = baiTapLonService.findByMaLop(maLop);
        if(baiTap1==null) {
            baiTap1=new BaiTapLon();
            baiTap1.setGiaoVien(user.getGiaoVien());
            baiTap1.setLopHoc(lopHocService.findById(maLop));
            baiTap1.setNgayBatDau(getDate(ngayBatDau, timeStart));
            baiTap1.setNgayKetThuc(getDate(hanNop, timeEnd));
            baiTap1.setNoiDung(baiTap);
            baiTapLonService.create(baiTap1);
            for (SinhVien sinhVien : sinhViens) {
                message.setTitle(setTitle("titleBaiTap"));
                message.setContent(setContent("contentBaiTap",maLop,baiTap,"",  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(baiTap1.getNgayBatDau()),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(baiTap1.getNgayKetThuc())));
                message.setSender("Giáo viên: "+user.getGiaoVien().getUser().getFullName());
                String newdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                message.setTime(newdate);
                message.setReceiver(sinhVien.getMaSinhVien());
                ThongBao thongBaoSV = themThongBaoSV(message);
                message.setId(String.valueOf(thongBaoSV.getId()));
                this.simpMessagingTemplate.convertAndSend("/topic/public-" + message.getReceiver(), message);
            }
        }else {
            String btcu=baiTap1.getNoiDung();
            baiTap1.setNgayBatDau(getDate(ngayBatDau, timeStart));
            baiTap1.setNgayKetThuc(getDate(hanNop, timeEnd));
            baiTap1.setNoiDung(baiTap);
            baiTapLonService.create(baiTap1);
            for (SinhVien sinhVien : sinhViens) {
                message.setTitle(setTitle("titleSuaBaiTap"));
                message.setContent(setContent("contentSuaBaiTap",maLop,btcu,baiTap,  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(baiTap1.getNgayBatDau()),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(baiTap1.getNgayKetThuc())));
                message.setSender("Giáo viên: "+user.getGiaoVien().getUser().getFullName());
                String newdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                message.setTime(newdate);
                message.setReceiver(sinhVien.getMaSinhVien());
                ThongBao thongBaoSV = themThongBaoSV(message);
                message.setId(String.valueOf(thongBaoSV.getId()));
                this.simpMessagingTemplate.convertAndSend("/topic/public-" + message.getReceiver(), message);
            }
        }
    }

    public ThongBao themThongBaoSV(ThongBaoSocket message) throws ParseException {
        ThongBao thongBao = new ThongBao();
        thongBao.setSinhVien(sinhVienService.getSinhVienId(message.getReceiver()));
        thongBao.setContent(message.getContent());
        thongBao.setTime(convertStringToDate(message.getTime()));
        thongBao.setStatus("false");
        thongBao.setTitle(message.getTitle());
        thongBao.setSender(message.getSender());
        ThongBao thongBao1 = thongBaoService.themThongBao(thongBao);
        return thongBao1;
    }


    public Date getDate(String date, String time) throws ParseException {
        date = date.replace('/', '-');
        Date date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date + " " + time + ":00");
        return date1;
    }


private boolean checkHanBaiTap(Date day) throws ParseException {
    String dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
    Date d1 = format.parse(dateStart);
    Date d2 = day;
    boolean check=false;
    try {
        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        int[] a=new int[4];
        a[0]= (int) diffDays;
        a[1]= (int) diffHours;
        a[2]= (int) diffMinutes;
        a[3]= (int) diffSeconds;
      if(a[0]<=0&&a[1]<=0&&a[2]<=0&&a[3]<=0){
          check=false;
      }else {
          check=true;
      }
        return check;
    } catch (Exception e) {
        e.printStackTrace();
        return check;
    }
}

    @RequestMapping(method = RequestMethod.POST, value = "/sinhvien/nopbai")
    @ResponseBody
    public void taoDiem(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException {
        String suffix = null;
        User user = (User) session.getAttribute("userInfo");
        String timeStart = request.getParameter("maLop");
        int id= -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        }catch (Exception e){
        }
        if (!file.isEmpty()) {
            if(id==-1) {
                suffix = file.getOriginalFilename().split("\\.")[1];
                String fileName = System.currentTimeMillis() + "." + suffix;
                upFile(file, fileName, "baitap");
                BaiTap baiTap = new BaiTap();
                baiTap.setLopHoc(lopHocService.findById(maLop));
                baiTap.setSinhVien(user.getSinhVien());
                baiTap.setNgayTao(new Date());
                baiTap.setFileName(file.getOriginalFilename());
                baiTap.setFileRealName(fileName);
                baiTapService.create(baiTap);
            }else {
                suffix = file.getOriginalFilename().split("\\.")[1];
                String fileName = System.currentTimeMillis() + "." + suffix;
                upFile(file, fileName, "baitap");
                BaiTap baiTap = baiTapService.findById(id);
                baiTap.setNgayTao(new Date());
                baiTap.setFileName(file.getOriginalFilename());
                baiTap.setFileRealName(fileName);
                baiTapService.create(baiTap);
            }
        }
    }

    @RequestMapping("/tuonglop/download-bai-tap/{Id}")
    public void downloadDocFile(HttpServletRequest request,HttpServletResponse response,@PathVariable("Id") Integer Id) throws IOException {
         BaiTap baiTap = baiTapService.findById(Id);
        downloadFile(response, baiTap.getFileName(), baiTap.getFileRealName(),"baitap");
    }


    private Date convertStringToDate(String day) throws ParseException {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(day);
    }

    public String getConfig(String name){
        return configService.findByName(name).getConfigType();
    }
    public String setTitle(String name){
        return  getConfig(name);
    }
    public String setContent(String name,String tenLop,String baiTap,String baiTapMoi,String ngayBatDau,String hanNop){

        String text=getConfig(name);
        text = text.replace("[tenlop]", tenLop);
        text = text.replace("[baitap]", baiTap);
        text = text.replace("[ngaybatdau]", ngayBatDau);
        text = text.replace("[hannop]", hanNop);
        text = text.replace("[baitapmoi]", baiTapMoi);
        return text;
    }

}
