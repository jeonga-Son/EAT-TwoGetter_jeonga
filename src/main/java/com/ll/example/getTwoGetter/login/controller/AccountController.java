package com.ll.example.getTwoGetter.login.controller;

import com.ll.example.getTwoGetter.login.Repository.UserRepository;
import com.ll.example.getTwoGetter.login.Service.KakaoService;
import com.ll.example.getTwoGetter.login.Service.MailService;
import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.Util;
import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KakaoService kakaoService;

    @Autowired
    private MailService mailService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String login(){

        return "account/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("userForm", user);
        return "account/register";
    }
    @GetMapping("/modify")
    public String modify(@RequestParam String username, @RequestParam String nickname, @RequestParam String password, HttpSession session){
        if(username.trim().equals("") || nickname.trim().equals("") || password.trim().equals("")){
            if(username.trim().equals("")){
                session.setAttribute("message" ,"이메일을 입력해주세요.");
                return "redirect:/";
            }
            if(nickname.trim().equals("")){
                session.setAttribute("message" ,"닉네임을 입력해주세요.");
                return "redirect:/";
            }
            if(password.trim().equals("")){
                session.setAttribute("message" ,"비밀번호를 입력해주세요.");
                return "redirect:/";
            }
        }
        User user = userRepository.findByUsername(username);
        user.setNickname(nickname);
        user.setPassword(password);
        user.setUsername(username);
        userService.save(user);
        session.setAttribute("message" ,"수정이 완료되었습니다.");
        return "redirect:/";
    }
    @GetMapping("/find")
    public String find(){
        return "account/find";
    }
    @PostMapping("/find")
    public String findPw(@RequestBody String username, Model model){
        username = username.split("=")[1];
        username = username.replace("%40", "@");
        if(userService.findByUsename(username)!=null){
            User user = userService.findByUsename(username);

            String randomPassword = Util.randomPassword();
            user.setPassword(randomPassword);
            userService.save(user);
            mailService.sendMail(username, randomPassword);

            model.addAttribute("message", "메일이 발송되었습니다.");
            return "account/find";
        }else{
            model.addAttribute("message", "존재하지 않는 이메일입니다.");
            return "account/find";
        }
    }

    @PostMapping("/register")
    public String registerAccount(@RequestBody User user){
        userService.save(user);
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/kakao")
    public String kakaoCallback(@RequestParam String code) throws IOException {
        String access_Token = kakaoService.getKakaoAccessToken(code);
        System.out.println("controller access_token : " + access_Token);
        return "index";
    }
}
