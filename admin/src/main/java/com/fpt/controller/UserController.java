package com.fpt.controller;

import com.fpt.entity.*;
import com.fpt.services.bomon.BoMonService;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.khoavien.KhoaVienService;
import com.fpt.services.role.RoleService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.user.UserService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private BoMonService boMonService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private KhoaVienService khoaVienService;
    @Autowired
    private GiangVienService giangVienService;

    @GetMapping("/user")
    public String base(Model model) {
        List<KhoaVien> lstKhoaVien = khoaVienService.findAll();
        List<User> lstUser = userService.findAll();
        Long totalRecord = giangVienService.count();
        model.addAttribute("lstKhoaVien", lstKhoaVien);
        model.addAttribute("lstUser", lstUser);
        model.addAttribute("totalRecord", totalRecord);
        return "/user/user";
    }

    @PostMapping("/user/them-moi")
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String memberId = request.getParameter("memberId");
        String userName = request.getParameter("userName");
        String userPassWord = request.getParameter("userPassWord");
        String userPhone = request.getParameter("userPhone");
        String userEmail = request.getParameter("userEmail");
        String fullName = request.getParameter("fullName");
        String userGender = request.getParameter("userGender");
        String userDOBStr = request.getParameter("userDOB");

        Date userDOB = new SimpleDateFormat("yyyy-MM-dd").parse(userDOBStr);

        String userAddress = request.getParameter("userAddress");

        String boMon = request.getParameter("boMon");
        String hocHam = request.getParameter("hocHam");
        String kinhNghiem = request.getParameter("kinhNghiem");

        User user = new User(userName, passwordEncoder.encode(userPassWord), fullName, userEmail, userPhone, userAddress, userGender, userDOB);
        Role role = roleService.findById("gv01");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        try {

            GiaoVien giaoVien = new GiaoVien();
            giaoVien.setBoMon(boMonService.findById(boMon));
            giaoVien.setHocHam(hocHam);
            giaoVien.setMaGiaoVien(memberId);
            giaoVien.setMoTa(kinhNghiem);
            userService.createTeacherAccount(user, giaoVien);
            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("fail");
        }
    }

    @PostMapping("/user/reset-password")
    public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userService.findUserByUserName(request.getParameter("userName"));
        user.setUserPassWord(passwordEncoder.encode("123456"));
        try {
            userService.update(user);
            response.getWriter().println("success");
        } catch (Exception e) {
            response.getWriter().println("failed");
        }
    }

    @GetMapping("/user/getValueForBoMon")
    public @ResponseBody
    List<BoMon> getValueForBoMonAndGiangVien(HttpServletRequest request) {
        String khoaVien = request.getParameter("khoaVien");
        List<BoMon> lstBoMon = boMonService.getLstBoMonByMaVien(khoaVien);
        for (BoMon bm : lstBoMon) {
            bm.setKhoaVien(null);
            bm.setLstMonHoc(null);
            bm.setLstGiaoVien(null);
            bm.setLstSinhVien(null);
        }
        return lstBoMon;
    }

    @RequestMapping(value = "/user/themsv", method = RequestMethod.POST)
    public String themSV(Model model, @RequestParam("excelfile") MultipartFile excelfile) {
        try {
            List<SinhVien> lstSinhVien = new ArrayList<>();
            int i = 0;
            // Creates a workbook object from the uploaded excelfile
            XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
            // Creates a worksheet object representing the first sheet
            XSSFSheet worksheet = workbook.getSheet("Sheet1");
            // Reads the data in excel file until last row is encountered
            while (i < worksheet.getLastRowNum()) {
                i++;
                // Creates an object for the UserInfo Model
                User user = new User();
                // Creates an object representing a single row in excel
                XSSFRow row = worksheet.getRow(i);
                // Sets the Read data to the model class
                //user.setId((int) row.getCell(0).getNumericCellValue());
                user.setUserName("SV" + row.getCell(1).getStringCellValue());
                user.setUserPassWord(passwordEncoder.encode("123456"));
                user.setFullName(row.getCell(2).getStringCellValue());
                user.setUserGender(row.getCell(3).getStringCellValue());
                String ngaySinhStr = row.getCell(4).getStringCellValue();
                user.setUserPhone(row.getCell(5).getStringCellValue());
                user.setUserEmail(row.getCell(6).getStringCellValue());
                user.setUserAddress(row.getCell(7).getStringCellValue());

                Date ngaySinh = new SimpleDateFormat("dd/MM/yyyy").parse(ngaySinhStr);

                String nnhStr = row.getCell(10).getStringCellValue();
                Date nnh = new SimpleDateFormat("dd/MM/yyyy").parse(nnhStr);

                user.setUserDOB(ngaySinh);


                Role role = roleService.findById("sv01");
                Set roles = new HashSet();
                roles.add(role);
                user.setRoles(roles);

                SinhVien sinhVien = new SinhVien();
                sinhVien.setMaSinhVien(row.getCell(1).getStringCellValue());
                sinhVien.setNgayNhapHoc(nnh);

                String khoaVienId = row.getCell(8).getStringCellValue();

                KhoaVien khoaVien = khoaVienService.findById(khoaVienId);
                sinhVien.setKhoaVien(khoaVien);

                String maNganh = row.getCell(9).getStringCellValue();
                BoMon boMon = boMonService.findById(maNganh);
                sinhVien.setBoMon(boMon);

                sinhVien.setUser(user);
                // persist data into database in here
                lstSinhVien.add(sinhVien);

            }
            workbook.close();
            sinhVienService.save(lstSinhVien);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user";
    }

    @PostMapping("/user/tim-kiem")
    public String searchUser(HttpServletRequest request, Model model) throws IOException, ParseException {
        String userName = request.getParameter("userName");
        String maNguoiDung = request.getParameter("maNguoiDung");
        String fullName = request.getParameter("fullName");
        model.addAttribute("maNguoiDung", maNguoiDung);
        model.addAttribute("userName", userName);
        model.addAttribute("fullName", fullName);
        List<KhoaVien> lstKhoaVien = khoaVienService.findAll();
        List<User> lstUser = userService.search(userName,maNguoiDung,fullName);
        Long totalRecord = giangVienService.count();
        model.addAttribute("lstKhoaVien", lstKhoaVien);
        model.addAttribute("lstUser", lstUser);
        model.addAttribute("totalRecord", totalRecord);
        return "/user/user";
    }
    @GetMapping(value = "/user/timkiem/{id}")
    public @ResponseBody
    User search(@PathVariable("id") String id) {
        User user = userService.findUserByUserName(id);
        user.setLstBinhLuan(null);
        user.setLstLike(null);
        user.setLstBaiDang(null);
        user.setRoles(null);
        if(user.getSinhVien()!=null){
            user.getSinhVien().setLstDiem(null);
            user.getSinhVien().setUser(null);
            user.getSinhVien().setKhoaVien(null);
            user.getSinhVien().setNhoms(null);
            user.getSinhVien().setLstThongBao(null);
            user.getSinhVien().setLstPheDuyet(null);
            user.getSinhVien().setLstBaiTap(null);
            user.getSinhVien().setLopHocs(null);
            user.getSinhVien().getBoMon().setKhoaVien(null);
            user.getSinhVien().getBoMon().setLstMonHoc(null);
            user.getSinhVien().getBoMon().setLstGiaoVien(null);
            user.getSinhVien().getBoMon().setLstSinhVien(null);
        }else if(user.getGiaoVien()!=null){
            user.getGiaoVien().setLstLopHoc(null);
            user.getGiaoVien().setLstPheDuyet(null);
            user.getGiaoVien().setLstThongBao(null);
            user.getGiaoVien().setUser(null);
            user.getGiaoVien().getBoMon().setLstSinhVien(null);
            user.getGiaoVien().getBoMon().setLstGiaoVien(null);
            user.getGiaoVien().getBoMon().setLstMonHoc(null);
            user.getGiaoVien().getBoMon().setKhoaVien(null);
        }

        return user;
    }
}
