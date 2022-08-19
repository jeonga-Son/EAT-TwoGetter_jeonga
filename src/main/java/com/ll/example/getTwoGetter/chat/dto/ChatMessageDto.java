package com.ll.example.getTwoGetter.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {
    private String nickname;
    private String chatId;
    private String chatContent;
}
