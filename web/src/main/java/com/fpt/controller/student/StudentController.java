
package com.fpt.controller.student;

        import com.fpt.entity.*;
        import com.fpt.services.bomon.BoMonService;
        import com.fpt.services.lophoc.LopHocService;
        import com.fpt.services.monhoc.MonHocService;
        import com.fpt.services.sinhvien.SinhVienService;
        import com.fpt.services.thongbao.ThongBaoService;
        import com.fpt.services.user.UserService;
        import org.joda.time.DateMidnight;
        import org.joda.time.Days;
        import org.joda.time.Months;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.*;
        import java.util.stream.Collectors;

@Controller
public class StudentController {
    @Autowired
    private LopHocService lopHocService;

    @Autowired
    private SinhVienService sinhVienService;


    @Autowired
    private MonHocService monHocService;
    @Autowired
    private ThongBaoService thongBaoService;

    @GetMapping("/register-class")
    public String registerclass(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        int hocki = getKiHoc(user.getSinhVien().getNgayNhapHoc());
        SinhVien sinhVien = sinhVienService.findById(user.getSinhVien().getMaSinhVien());
        model.addAttribute("sinhVien", sinhVien);
        model.addAttribute("listMonHoc", monHocService.listMonHocKy(hocki,user.getSinhVien().getKhoaVien().getMaVien()));
        model.addAttribute("user", user);
        List<LopHoc> lopHocs= lopHocService.listLopHocSinhVien(user.getSinhVien().getMaSinhVien());
        String[] lopHocSV=new String[lopHocs.size()];
        int i=0;
        for (LopHoc lopHoc:lopHocs) {
            lopHocSV[i]=lopHoc.getMaLop();
            i++;
        }
        model.addAttribute("lopHocSV", lopHocSV);
        model.addAttribute("hocKi", "Danh sách các môn học trong kì: " + hocki);
        model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatSV(user.getSinhVien().getMaSinhVien()));
        model.addAttribute("soLuongTBChuaXem",thongBaoService.soLuongTbChuaXemSV(user.getSinhVien().getMaSinhVien()));
        return "student/register_class";
    }

    @GetMapping("/register-member")
    public String registermember(Model model,HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        model.addAttribute("user", user);
        model.addAttribute("moiNhat", thongBaoService.thongBaoMoiNhatGV(user.getGiaoVien().getMaGiaoVien()));
        return "student/register-member";
    }

    @PostMapping("/student/search")
    public String search(HttpServletRequest request, HttpSession session, HttpServletResponse response, Model model) {
        String input = request.getParameter("input");
        String loai = request.getParameter("loai");
        String bomon = request.getParameter("bomon");
        model.addAttribute("listMonHoc", monHocService.listMonHoc());
        model.addAttribute("lopHoc", new LopHoc());
        if (loai.equals("giaovien")) {
            model.addAttribute("listLopHoc", lopHocService.searchGiaoVien(input, bomon));
        } else {
            model.addAttribute("listLopHoc", lopHocService.searchLop(input, bomon));
        }
        return "student/register_class";
    }

    @PostMapping("/api/dangkylop")
    public void dangKyLop(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        LopHoc hoc = lopHocService.findById(id);
        User user = (User) session.getAttribute("userInfo");
        List<LopHoc> lhs=lopHocService.listLopHocSinhVien(user.getSinhVien().getMaSinhVien());
       
        //kiểm tra xem lịch học có bị trùng không
        boolean checkLH=false;
        for (LopHoc lopHoc:lhs){
            if(checkLichHoc( lopHoc.getNgayHoc().split(","),hoc.getNgayHoc().split(",")))
            if(checkLichHoc(lopHoc.getCaHoc().split(","),hoc.getCaHoc().split(",")))
                checkLH=true;
          }

    if(!checkLH) {
    LopHoc lh01 = lopHocService.findById(id);
    //kiểm tra thời hạn lớp
    int thoihan = checkThoiHan(lh01.getNgayBatDau());
    if (thoihan > -10) {
        response.getWriter().println("quahan");
    } else {
        //Kiểm tra sv dã dang ký lớp khác cùng môn chưa
        String bomon = lh01.getMonHoc().getMaMonHoc();
        LopHoc lopHoc = lopHocService.getLopHocSvBm(user.getSinhVien().getMaSinhVien(), bomon);
        if (lopHoc != null) {
            response.getWriter().println("trungmon");
        } else {
            //kiểm tra lớp dã đủ sv chưa
            if (lh01.getSinhViens().size() >= 50) {
                response.getWriter().println("maxsv");
            } else {
                //Kiểm tra sv dã tồn tại trong lớp học chưa
                LopHoc lopHoc01 = lopHocService.getLopHocSV(id, user.getSinhVien().getMaSinhVien());
                if (lopHoc01 == null) {
                    LopHoc hoc1 = lopHocService.findById(id);
                    Set<SinhVien> sinhVienSet = hoc1.getSinhViens();
                    sinhVienSet.add(user.getSinhVien());
                    hoc1.setMaLop(id);
                    hoc1.setSinhViens(sinhVienSet);
                    lopHocService.createlopSV(hoc1);
                    response.getWriter().println("success");
                } else {
                    response.getWriter().println("tontai");
                }
            }
        }
    }
}else {
    response.getWriter().println("trunglich");
}
    }

    @PostMapping("/api/member")
    public ResponseEntity<?> getSearchResultViaAjax(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String input = request.getParameter("search");
        List<SinhVien> s = new LinkedList<>();
        if (session.getAttribute("listmember") != null) {
            s = (List<SinhVien>) session.getAttribute("listmember");
            SinhVien sinhVien = sinhVienService.getSinhVienId(input);
            if (s.contains(sinhVien)) {
                s.remove(sinhVien);
            } else {
                s.add(sinhVien);
            }
            session.removeAttribute("listmember");
            session.setAttribute("listmember", s);
        } else {
            SinhVien sinhVien = sinhVienService.getSinhVienId(input);
            s.add(sinhVien);
            session.setAttribute("listmember", s);
        }
        return ResponseEntity.ok(s);
    }

    @GetMapping("/lop")
    public String getMember(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String id = request.getParameter("malop");
        LopHoc hoc = lopHocService.findById(id);
        Set<SinhVien> sinhViens = hoc.getSinhViens();
        List<SinhVien> lsv = new LinkedList(sinhViens);
        model.addAttribute("listSinhVien", lsv);
        User user = (User) session.getAttribute("userInfo");
        model.addAttribute("listLopHoc", lopHocService.listLopHocSinhVien(user.getSinhVien().getMaSinhVien()));
        model.addAttribute("soLuongTBChuaXem",thongBaoService.soLuongTbChuaXemSV(user.getSinhVien().getMaSinhVien()));
        model.addAttribute("moiNhat",thongBaoService.thongBaoMoiNhatSV(user.getSinhVien().getMaSinhVien()));
        model.addAttribute("user", user);
        model.addAttribute("listLopHoc", lopHocService.listLopHocSinhVien(user.getSinhVien().getMaSinhVien()));
        session.removeAttribute("listmember");
        return "tuonglop/tuonglop";
    }


    private int getKiHoc(Date ngaynhaphoc) {
        DateMidnight start = new DateMidnight(ngaynhaphoc);
        DateMidnight end = new DateMidnight(new Date());
        int months = Months.monthsBetween(start,end).getMonths();
        int thangDu = months % 6;
        return (thangDu > 0) ? (months / 6 +1) : (months/6);
    }


    private int checkThoiHan(Date ngaybatdau) {
        Date date = ngaybatdau;
        String newdate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        DateMidnight start = new DateMidnight(newdate);
        DateMidnight end = new DateMidnight(new Date());
        int days = Days.daysBetween(start, end).getDays();
        return days;
    }

  private boolean checkLichHoc(String arr1[], String arr2[]){
        HashSet<String> hs = new HashSet<>();
        for (int i = 0; i < arr1.length; i++)hs.add(arr1[i]);
        for (int i = 0; i < arr2.length; i++)if (hs.contains(arr2[i]))
        return true;
        return false;
    }
}

