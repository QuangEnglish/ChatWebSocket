package com.quangenglish.chat.chat;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String content; //nội dung
    private String sender; //người gửi
    private MessageType type; //trạng thái chat, tham gia, rời khỏi

}
