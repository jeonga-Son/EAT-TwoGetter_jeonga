package com.ll.example.getTwoGetter.login.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    //메일 보내기
    public void sendMail(String username, String content){
        ArrayList<String> toUserList  = new ArrayList<>();
        toUserList.add(username);
        int toUserSize = toUserList.size();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));
        simpleMailMessage.setSubject("임시 비밀번호");
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }
}
