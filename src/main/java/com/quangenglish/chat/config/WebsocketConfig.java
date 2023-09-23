package com.quangenglish.chat.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker  //kích hoạt việc sử dụng websocket
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    // phương thức registerStompEndpoints được sử dụng để đăng ký các điểm cuối Stomp trong ứng dụng của bạn,
    // cho phép máy khách thiết lập kết nối WebSocket và gửi/nhận tin nhắn Stomp thông qua các điểm cuối này.
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); //Ví dụ, nếu máy khách gửi tin nhắn đến "/app/hello", nó sẽ được định tuyến đến ứng dụng của bạn để xử lý.
        registry.enableSimpleBroker("/topic"); // Ví dụ, nếu máy chủ gửi tin nhắn tới kênh "/topic/news", tất cả các máy khách đã đăng ký với kênh đó sẽ nhận được tin nhắn.
    }

}
