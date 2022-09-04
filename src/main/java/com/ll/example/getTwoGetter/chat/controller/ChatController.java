package com.ll.example.getTwoGetter.chat.controller;

import com.ll.example.getTwoGetter.chat.model.ChatInfo;
import com.ll.example.getTwoGetter.chat.model.ChatMessage;
import com.ll.example.getTwoGetter.chat.repository.ChatInfoRepository;
import com.ll.example.getTwoGetter.chat.service.ChatInfoService;
import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.login.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final UserService userService;

    private final ChatInfoService chatInfoService;

    @GetMapping("/list")
    public String chatList(@AuthenticationPrincipal UserDetails userDetails, Model model){
        if(userDetails != null){
            String username = userDetails.getUsername();
            User user = userService.findByUsername(username);;
            List<ChatInfo> chatInfos_user =chatInfoService.findByUsername(user.getNickname());
            List<ChatInfo> chatInfos_partner = chatInfoService.findByPartner(user.getNickname());

            List<ChatInfo> joined = new ArrayList<>();

            //joined 리스트에 채팅방에 user 객체와 상대방 partner 객체를 둘 다 넣고 폼에 전달한다.
            joined.addAll(chatInfos_user);
            joined.addAll(chatInfos_partner);
            model.addAttribute("chatInfos", joined);
            model.addAttribute("user",user);
            return "chat/chatList";
        }
        else{
            return "redirect:/account/login";
        }

    }

}
