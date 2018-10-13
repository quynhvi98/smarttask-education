package com.fpt.controller.socket;

import com.fpt.entity.*;
import com.fpt.services.config.ConfigService;
import com.fpt.services.lophoc.LopHocService;
import com.fpt.services.sinhvien.SinhVienService;
import com.fpt.services.thongbao.ThongBaoService;
import com.fpt.services.giangvien.GiangVienService;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
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
@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"com.fpt.controller.socket" })
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
    private ConfigService configService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ThongBaoSocket message) throws ParseException {
        List<SinhVien>sinhViens=sinhVienService.listSVki(message.getKi());
        ThongBao thongBaoGV=themThongBaoGV(message);
        message.setId(String.valueOf(thongBaoGV.getId()));
        this.simpMessagingTemplate.convertAndSend("/topic/public-"+message.getReceiver() ,  message);
        for (SinhVien sinhVien:sinhViens) {
            message.setTitle(setTitle("titleTaoLop"));
            message.setContent(setContent("contentTaoLop",message.getMaLop(),"","","", String.valueOf(message.getKi())));
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

    @Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean
    public String getCronValue()
    {
        String time=null;
        Config config = configService.findByName("crontime");
        if(config!=null) {
            String[] arr = config.getConfigType().split(":");
             time = "0 " + arr[1] + " " + arr[0] + " * * *";
        }else {
            time="0 0 0 * * *";
        }
        return time;
    }

    @Scheduled(cron  = "#{@getCronValue}")
    public void greeting() throws InterruptedException, ParseException {
        String day=lopHocService.tinhNgayHetHan(1);
        String day1=lopHocService.tinhNgayHetHan(10);
        String day2=lopHocService.tinhNgayHetHan(11);
        List<LopHoc>dongLop=lopHocService.listDongLop(day);
        for (LopHoc lopHoc:dongLop) {
            lopHoc.setTrangThai("2");
            lopHocService.capNhat(lopHoc);
        }
        List<LopHoc> lopHocs=lopHocService.listLopToiHan(day1,day2);
        for (LopHoc lopHoc:lopHocs) {
            lopHoc.setTrangThai("1");
            lopHocService.capNhat(lopHoc);
            if(lopHoc.getSinhViens().size()>Integer.parseInt(getSlSv("soLuongSV")[0])) {
                String name=lopHoc.getMaLop();
                String time= String.valueOf(lopHoc.getNgayBatDau());
                String mon=lopHoc.getMonHoc().getTenMonHoc();
                PhongHoc phong = lopHoc.getPhongHoc();
                String gv=lopHoc.getGiaoVien().getUser().getFullName();
                String content=setContent("contentKhaiGiang",name,mon,time,phong.getTenPhong(),"");
                ThongBaoSocket message=new ThongBaoSocket();
                message.setContent(content);
                message.setSender("Hệ thống");
                message.setTitle(setTitle("titleKhaiGiang"));
                message.setTime(lopHocService.tinhNgayHetHan(0));
                message.setReceiver(lopHoc.getGiaoVien().getMaGiaoVien());
                ThongBao thongBaoGV=   themThongBaoGV(message);
                message.setId(String.valueOf(thongBaoGV.getId()));
                this.simpMessagingTemplate.convertAndSend("/topic/public-"+message.getReceiver(), message);
                for (SinhVien sinhVien : lopHoc.getSinhViens()) {
                    content=setContent("contentKhaiGiang",name,mon,time,phong.getTenPhong(),"");
                    message.setContent(content);
                    message.setReceiver(sinhVien.getMaSinhVien());
                    ThongBao thongBao= themThongBaoSV(message);
                    message.setId(String.valueOf(thongBao.getId()));
                    this.simpMessagingTemplate.convertAndSend("/topic/public-" + message.getReceiver(), message);
                }
            }
            else {
                lopHoc.setTrangThai("3");
                lopHocService.capNhat(lopHoc);
                String name=lopHoc.getMaLop();
                String time= String.valueOf(lopHoc.getNgayBatDau());
                String mon=lopHoc.getMonHoc().getTenMonHoc();
                PhongHoc phong=lopHoc.getPhongHoc();
                String gv=lopHoc.getGiaoVien().getUser().getFullName();
                String content=setContent("contentHuyLop",name,mon,time,phong.getTenPhong(),"");
                ThongBaoSocket message=new ThongBaoSocket();
                message.setContent(content);
                message.setSender("Hệ thống");
                message.setTitle(setTitle("titleHuyLop"));
                message.setTime(lopHocService.tinhNgayHetHan(0));
                message.setReceiver(lopHoc.getGiaoVien().getMaGiaoVien());
                ThongBao thongBaoGV=   themThongBaoGV(message);
                message.setId(String.valueOf(thongBaoGV.getId()));
                this.simpMessagingTemplate.convertAndSend("/topic/public-"+message.getReceiver(), message);
                for (SinhVien sinhVien : lopHoc.getSinhViens()) {
                    message.setReceiver(sinhVien.getMaSinhVien());
                    ThongBao thongBaoSV=   themThongBaoSV(message);
                    message.setId(String.valueOf(thongBaoSV.getId()));
                    this.simpMessagingTemplate.convertAndSend("/topic/public-" + message.getReceiver(), message);
                }
            }
        }
    }
    public ThongBao themThongBaoGV(ThongBaoSocket message) throws ParseException {
        ThongBao thongBao=new ThongBao();
        thongBao.setGiaoVien(giangVienService.findById(message.getReceiver()));
        thongBao.setContent(message.getContent());
         thongBao.setTime(new Date());
        thongBao.setStatus("false");
        thongBao.setTitle(message.getTitle());
        thongBao.setSender(message.getSender());
        ThongBao thongBao1=  thongBaoService.themThongBao(thongBao);
        return thongBao1;
    }


    public ThongBao themThongBaoSV(ThongBaoSocket message) throws ParseException {
        ThongBao thongBao=new ThongBao();
        thongBao.setSinhVien(sinhVienService.getSinhVienId(message.getReceiver()));
        thongBao.setContent(message.getContent());
        thongBao.setTime(new Date());
        thongBao.setStatus("false");
        thongBao.setTitle(message.getTitle());
        thongBao.setSender(message.getSender());
        ThongBao thongBao1=  thongBaoService.themThongBao(thongBao);
        return thongBao1;
    }



    public String getConfig(String name){
        return configService.findByName(name).getConfigType();
    }
    public String setTitle(String name){
        return  getConfig(name);
    }
    public String setContent(String name,String tenLop,String mon,String thoiGian,String phong,String kihoc){
        String text=getConfig(name);
       text = text.replace("[tenlop]", tenLop);
       text = text.replace("[mon]", mon);
       text = text.replace("[thoigian]", thoiGian);
       text = text.replace("[phong]", phong);
       text = text.replace("[kihoc]", kihoc);
       return text;
    }
    private Date convertStringToDate(String day) throws ParseException {
        return (Date) new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(day);
    }

    public String[] getSlSv(String name){
        return  getConfig(name).split(",");
    }
}