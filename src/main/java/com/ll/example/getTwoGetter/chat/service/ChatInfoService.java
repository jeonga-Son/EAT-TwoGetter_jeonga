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
        if(chatInfoList==null){
            return null;
        }
        return chatInfoList;
    }

    public List<ChatInfo> findByPartner(String nickname) {
        List<ChatInfo> chatInfoList1 = chatInfoRepository.findByPartner(nickname);
        if(chatInfoList1==null){
            return null;
        }
        return chatInfoList1;
    }

    public ChatInfo findById(long id) {
        ChatInfo chatInfo = chatInfoRepository.findById(id).orElse(null);
        return chatInfo;
    }


    public void save(ChatInfo chatInfo) {
        chatInfoRepository.save(chatInfo);
    }
}
