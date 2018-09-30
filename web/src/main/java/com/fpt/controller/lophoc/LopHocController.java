package com.fpt.controller.lophoc;

import com.fpt.controller.UserInfoController;
import com.fpt.controller.lophoc.ExcelView;
import com.fpt.entity.*;
import com.fpt.services.diem.DiemService;
import com.fpt.services.giangvien.GiangVienService;
import com.fpt.services.lophoc.LopHocService;
import com.fpt.services.monhoc.MonHocService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.thongbao.ThongBaoService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Posted from Sep 23, 2018, 12:44 AM
 *
 * @author Vi Quynh (vi.quynh.31598@gmail.com)
 */
@Controller
public class LopHocController {
    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private LopHocService lopHocService;
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private ThongBaoService thongBaoService;
    @Autowired
    private DiemService diemService;
    @Autowired
    private MonHocService monHocService;

    @RequestMapping("/giangviendaylop/{maLop}")
    public String userProfile(HttpSession session, @PathVariable("maLop") String maLop, HttpServletRequest request, Model model) {
        User userInfo = (User) session.getAttribute("userInfo");
        GiaoVien giaoVien = giangVienService.findById(userInfo.getGiaoVien().getMaGiaoVien());
        LopHoc lopHoc = lopHocService.findById(maLop);
        List<SinhVien> sinhViens = sinhVienService.getListSinhVienbyLopHocId(maLop);
        String[] ngayHoc = lopHoc.getNgayHoc().split(",");
        String[] caHoc = lopHoc.getCaHoc().split(",");
        model.addAttribute("user", userInfo);
        model.addAttribute("giaoVien", giaoVien);
        model.addAttribute("lopHoc", lopHoc);
        model.addAttribute("SV", sinhViens);
        model.addAttribute("ngayHoc", ngayHoc);
        model.addAttribute("caHoc", caHoc);
        if(userInfo.getSinhVien()!= null) {
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatSV(userInfo.getSinhVien().getMaSinhVien()));
        }
        if(userInfo.getGiaoVien()!= null) {
            model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatGV(userInfo.getGiaoVien().getMaGiaoVien()));
        }
        return "giaovien_giangday/lopgiangday";
    }


    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView downloadExcel(Model model, HttpServletRequest request, HttpServletResponse responsel) {
        String maLop = request.getParameter("maLop");
        List<SinhVien> sinhViens=sinhVienService.getListSinhVienbyLopHocId(maLop);
        model.addAttribute("maLop",maLop);
        model.addAttribute("sinhViens",sinhViens);
        return new ModelAndView(new ExcelView());
    }


    @RequestMapping(method = RequestMethod.POST, value = "/upfile")
    @ResponseBody
    public void taoDiem(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response, @RequestParam("file") MultipartFile excelfile) {
        String maMonHoc = request.getParameter("maMonHoc");
        String maLopHoc = request.getParameter("maLopHoc");
        System.out.println(maMonHoc);
        User user = (User) session.getAttribute("userInfo");
        if(lopHocService.findById(maLopHoc).getLstDiem().size()==0) {
            try {
                List<Diem> diems = new ArrayList<>();
                int i = 0;
                XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
                XSSFSheet worksheet = workbook.getSheetAt(0);
                while (i < worksheet.getLastRowNum()) {
                    i++;
                    Diem diem = new Diem();
                    XSSFRow row = worksheet.getRow(i);
                    diem.setDiemLyThuyet((row.getCell(5).getNumericCellValue()));
                    diem.setDiemThucHanh((row.getCell(4).getNumericCellValue()));
                    diem.setDiemCuoiKi((row.getCell(6).getNumericCellValue()));
                    String msv = row.getCell(1).getStringCellValue();
                    diem.setSinhVien(sinhVienService.getSinhVienId(msv));
                    diem.setGiaoVien(user.getGiaoVien());
                    diem.setMonHoc(monHocService.findById(maMonHoc));
                    diem.setLopHoc(lopHocService.findById(maLopHoc));
                    diems.add(diem);
                }
                workbook.close();
                diemService.careate(diems);
                SinhVien sinhVien = sinhVienService.getSinhVienId("D001");
                for (Diem diem : sinhVien.getLstDiem()) {
                    System.out.println("diem: " + diem.getDiemLyThuyet());
                }
                response.getWriter().println("success");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/giangviendaylop/suadiem")
    public void suaDiem(HttpServletRequest request, HttpSession session, HttpServletResponse response, Model model) {
        try {
            String maSV = request.getParameter("maSV");
            Double diemLyThuyet = Double.valueOf(request.getParameter("diemLyThuyet"));
            String maDiem = request.getParameter("maDiem");
            System.out.println();
            Double diemThucHanh = Double.valueOf(request.getParameter("diemThucHanh"));
            Double diemCuoiKi = Double.valueOf((request.getParameter("diemCuoiKi")));
            String maMonHoc = request.getParameter("maMonHoc");
            String maLopHoc = request.getParameter("maLopHoc");
            if((diemCuoiKi <=10&& diemCuoiKi>=0)&&(diemLyThuyet <=10&& diemLyThuyet>=0)&&(diemThucHanh <=10&& diemThucHanh>=0)) {
                System.out.println(maMonHoc + " " + diemLyThuyet + " " + diemThucHanh + " " + diemCuoiKi);
                User user = (User) session.getAttribute("userInfo");
                Diem diem = diemService.findById(maDiem);
                diem.setDiemThucHanh(diemThucHanh);
                diem.setDiemLyThuyet(diemLyThuyet);
                diem.setDiemCuoiKi(diemCuoiKi);
                diemService.save(diem);
                response.getWriter().println("success");
            }else {
                response.getWriter().println("saidiem");
            }
        }catch (Exception e){
            try {
                response.getWriter().println("error");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }



}
