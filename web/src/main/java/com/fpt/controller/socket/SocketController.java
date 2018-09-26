package com.fpt.controller.socket;

import com.fpt.entity.LopHoc;
import com.fpt.entity.SinhVien;
import com.fpt.entity.ThongBao;
import com.fpt.entity.ThongBaoSocket;
import com.fpt.services.lophoc.LopHocService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.thongbao.ThongBaoService;
import com.fpt.services.giangvien.GiangVienService;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@EnableScheduling
@Controller
public class SocketController {
    @Autowired
    private ThongBaoService thongBaoService;
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private LopHocService lopHocService;
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ThongBaoSocket message) {
        List<SinhVien>sinhViens=sinhVienService.listSVki(message.getKi());
        System.out.println("soki "+sinhViens.size());
        ThongBao thongBaoGV=themThongBaoGV(message);
        message.setId(String.valueOf(thongBaoGV.getId()));
        this.simpMessagingTemplate.convertAndSend("/topic/public-"+message.getReceiver() ,  message);

        for (SinhVien sinhVien:sinhViens) {
            message.setTitle("Thông báo lớp mới");
            message.setContent("Lớp: "+message.getMaLop()+" - trong kì: "+message.getKi()+" đã được tạo");
            message.setReceiver(sinhVien.getMaSinhVien());
            ThongBao thongBaoSV=themThongBaoSV(message);
            message.setId(String.valueOf(thongBaoSV.getId()));
            this.simpMessagingTemplate.convertAndSend("/topic/public-"+message.getReceiver() ,  message);
        }
    }



    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public-name")
    public ThongBaoSocket addUser(@Payload ThongBaoSocket message, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }


    @Scheduled(cron = "0 0 6 * * *")
// @Scheduled(fixedRate = 100000)
    public void greeting() throws InterruptedException {
        //Thread.sleep(1000);
        String day1=lopHocService.tinhNgayHetHan(10);
        String day2=lopHocService.tinhNgayHetHan(11);
        List<LopHoc> lopHocs=lopHocService.listLopToiHan(day1,day2);
        System.out.println("so luong "+ lopHocs.size());
        for (LopHoc lopHoc:lopHocs) {
            System.out.println("lop hoc"+lopHoc.getSinhViens().size());
            if(lopHoc.getSinhViens().size()>0) {
                String name=lopHoc.getMaLop();
                String time=lopHoc.getNgayHoc();
                String mon=lopHoc.getMonHoc().getTenMonHoc();
                String phong=lopHoc.getPhongHoc();
                String gv=lopHoc.getGiaoVien().getUser().getFullName();
                String content="Lớp của bạn đã đủ điều kiện khai giảng: "+name+" - môn: "+mon+" - thời gian: "+time+" - giảng viên: "+gv+" - tại phòng học: "+phong;
                ThongBaoSocket message=new ThongBaoSocket();
                message.setContent(content);
                message.setSender("Hệ thống");
                message.setId("11");
                message.setTitle("Thông báo khai giảng");
                message.setTime(lopHocService.tinhNgayHetHan(0));
                message.setReceiver(lopHoc.getGiaoVien().getMaGiaoVien());
                themThongBaoGV(message);
                this.simpMessagingTemplate.convertAndSend("/topic/public-"+message.getReceiver(), message);
                for (SinhVien sinhVien : lopHoc.getSinhViens()) {
                    content="Lớp: "+name+" được khai giảng vào ngày : "+time+" - giảng viên: "+gv+" - tại phòng học: "+phong;
                    message.setContent(content);
                    message.setReceiver(sinhVien.getMaSinhVien());
                    themThongBaoSV(message);
                    this.simpMessagingTemplate.convertAndSend("/topic/public-" + message.getReceiver(), message);
                }
            }

            else {
                String name=lopHoc.getMaLop();
                String time=lopHoc.getNgayHoc();
                String mon=lopHoc.getMonHoc().getTenMonHoc();
                String phong=lopHoc.getPhongHoc();
                String gv=lopHoc.getGiaoVien().getUser().getFullName();
                String content="Lóp: "+name+" đã bị hủy do số lượng sinh viên đăng kí không đạt yêu cầu";
                ThongBaoSocket message=new ThongBaoSocket();
                message.setContent(content);
                message.setSender("Hệ thống");
                message.setId("11");
                message.setTitle("Thông báo hủy lớp");
                message.setTime(lopHocService.tinhNgayHetHan(0));
                message.setReceiver(lopHoc.getGiaoVien().getMaGiaoVien());
                this.simpMessagingTemplate.convertAndSend("/topic/public-"+message.getReceiver(), message);
                for (SinhVien sinhVien : lopHoc.getSinhViens()) {
                    message.setReceiver(sinhVien.getMaSinhVien());
                    this.simpMessagingTemplate.convertAndSend("/topic/public-" + message.getReceiver(), message);
                }
            }
        }
    }
    public ThongBao themThongBaoGV(ThongBaoSocket message){
        ThongBao thongBao=new ThongBao();
        thongBao.setGiaoVien(giangVienService.findById(message.getReceiver()));
        thongBao.setContent(message.getContent());
        thongBao.setTime(message.getTime());
        thongBao.setStatus("false");
        thongBao.setTitle(message.getTitle());
        thongBao.setSender(message.getSender());
        ThongBao thongBao1=  thongBaoService.themThongBao(thongBao);
        return thongBao1;
    }


    public ThongBao themThongBaoSV(ThongBaoSocket message){
        ThongBao thongBao=new ThongBao();
        thongBao.setSinhVien(sinhVienService.getSinhVienId(message.getReceiver()));
        thongBao.setContent(message.getContent());
        thongBao.setTime(message.getTime());
        thongBao.setStatus("false");
        thongBao.setTitle(message.getTitle());
        thongBao.setSender(message.getSender());
        ThongBao thongBao1=  thongBaoService.themThongBao(thongBao);
        return thongBao1;
    }

}