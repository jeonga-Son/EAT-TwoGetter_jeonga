package com.ll.example.getTwoGetter.chat.service;

import com.ll.example.getTwoGetter.chat.model.ChatInfo;
import com.ll.example.getTwoGetter.chat.repository.ChatInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatInfoService {
    private final ChatInfoRepository chatInfoRepository;
    public List<ChatInfo> findByUsername(String nickname) {
        List<ChatInfo> chatInfoList = chatInfoRepository.findByUsername(nickname);
        if(chatInfoList.size()==0){
            return null;
        }
        return chatInfoList;
    }
}
