package com.quangenglish.chat.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    //triển khai gửi tin nhắn khi người dùng gửi tin nhắn
    @MessageMapping("/chat.sendMessage")  //URL mà tôi muốn sử dụng để gọi phương thức gửi tin nhắn
    @SendTo("/topic/public")      //chú thích gửi tin nhắn này ở đâu
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        //@Payload được sử dụng để đánh dấu tham số phương thức trong một phương thức xử lý tin nhắn WebSocket
        // để nhận dữ liệu payload của nội dung tin nhắn từ client.
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        //@Payload để trích xuất đối tượng ChatMessage chứa thông tin về người gửi và nội dung tin nhắn
        //SimpMessageHeaderAccessor headerAccessor: để thiết lập tên người dùng trong phiên websocket
        //thêm tên người dùng trong phiên websocket
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        //thêm thuộc tính username vào phiên websocket và gán giá trị là tên người gửi từ đối tg chatmessage
        return chatMessage;
    }
}
