package com.ll.example.getTwoGetter.chat.service;

import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import com.ll.example.getTwoGetter.chat.model.ChatInfo;
import com.ll.example.getTwoGetter.chat.model.ChatMessage;
import com.ll.example.getTwoGetter.chat.repository.ChatInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatInfoService {
    private final ChatInfoRepository chatInfoRepository;

    private final ChatMessageService chatMessageService;
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

    public void delete(ChatInfo chatInfo) {
        chatInfoRepository.delete(chatInfo);
    }
    public void delete(List<ChatInfo> chatInfos){
        for(int i=0; i<chatInfos.size(); i++){
            chatInfoRepository.delete(chatInfos.get(i));
        }
    }

    public void modify(String beforeNickname, String afterNickname) {
        List<ChatInfo> chatInfos = chatInfoRepository.findAll();
        if(chatInfos ==null){
            return;
        }
        for(int i=0; i<chatInfos.size(); i++){
            ChatInfo chatInfo = chatInfos.get(i);
            if(chatInfo.getUsername().equals(beforeNickname)){
                chatInfo.setUsername(afterNickname);
                chatInfoRepository.save(chatInfo);
            }
            if(chatInfo.getPartner().equals(beforeNickname)){
                chatInfo.setPartner(afterNickname);
                chatInfoRepository.save(chatInfo);
            }
        }
        chatMessageService.modify(beforeNickname, afterNickname);

    }

    public void modifyLastTime(long id, String name) {
        ChatInfo chatInfo = chatInfoRepository.findById(id).orElse(null);
        if(chatInfo.getUsername().equals(name)){
            chatInfo.setUserLastReadTime(LocalDateTime.now());
            this.save(chatInfo);
            return;
        }
        if(chatInfo.getPartner().equals(name)){
            chatInfo.setPartnerLastReadTime(LocalDateTime.now());
            this.save(chatInfo);
            return;
        }
    }

    public List<ChatInfo> findAll() {
        List<ChatInfo> chatInfos = chatInfoRepository.findAll();
        return chatInfos;
    }
}
