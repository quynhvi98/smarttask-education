package com.fpt.controller.socket;




import com.fpt.entity.ThongBao;
import com.fpt.entity.ThongBaoSocket;
import com.fpt.services.thongbao.ThongBaoService;
import com.fpt.services.giangvien.GiangVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @Autowired
    private ThongBaoService thongBaoService;
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ThongBaoSocket message) {
        ThongBao thongBao=new ThongBao();
        thongBao.setGiaoVien(giangVienService.findById(message.getReceiver()));
        thongBao.setContent(message.getContent());
        thongBao.setTime(message.getTime());
        thongBao.setStatus("false");
        thongBao.setTitle(message.getTitle());
        thongBao.setSender(message.getSender());
        ThongBao thongBao1=  thongBaoService.themThongBao(thongBao);
        message.setId(thongBao1.getId()+"");
        this.simpMessagingTemplate.convertAndSend("/topic/public-"+message.getReceiver() ,  message);
    }


    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public-name")
    public ThongBaoSocket addUser(@Payload ThongBaoSocket message, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }

}