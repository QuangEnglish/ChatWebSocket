package com.quangenglish.chat.config;

import com.quangenglish.chat.chat.ChatMessage;
import com.quangenglish.chat.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor //tạo hàm khỏi tạo có tham số
@Slf4j //Lombok sẽ tự động tạo ra một biến static final Logger với tên "log" trong lớp MyClass(ví dụ). Bạn có thể sử dụng biến log này
// để ghi các thông điệp log trong phương thức myMethod() và các phương thức khác trong lớp.
public class WebsocketEventListen {
    private final SimpMessageSendingOperations messageTemplate;
    @EventListener
    //trình xử lý khi ngắt kết nối
    public void handleWebsocketDisconnectListener(SessionDisconnectEvent event){ //tham số lắng nghe sự kiện
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null){
            log.info("User disconnected: {}", username);
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            messageTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
