package com.ll.example.getTwoGetter.login.controller;

import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import com.ll.example.getTwoGetter.Board.service.BoardService;
import com.ll.example.getTwoGetter.chat.model.ChatInfo;
import com.ll.example.getTwoGetter.chat.service.ChatInfoService;
import com.ll.example.getTwoGetter.login.Service.KakaoService;
import com.ll.example.getTwoGetter.login.Service.MailService;
import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.Util;
import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private ChatInfoService chatInfoService;

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
    @GetMapping("/remove/{username}")
    public String remove(@PathVariable String username){
        User user = userService.findByUsername(username);
        userService.delete(user);

        List<Board> boards = boardService.findByUsername(user.getNickname());
        if(boards!=null){
            boardService.delete(boards);
        }
        List<ChatInfo> chatInfos = chatInfoService.findByUsername(user.getNickname());
        List<ChatInfo> chatInfos_=chatInfoService.findByPartner(user.getNickname());
        if(chatInfos!=null){
            chatInfoService.delete(chatInfos);
        }
        if(chatInfos_!=null){
            chatInfoService.delete(chatInfos_);
        }
        return "redirect:/logout";
    }
    @GetMapping("/modify")
    public String modify(@RequestParam String username, @RequestParam String nickname, @RequestParam String password, HttpSession session, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes rttr){
        if(userDetails != null){
            if(nickname.length()==0|| password.length()==0){
                if(password.length()==0 && nickname.length()!=0){
//                    session.setAttribute("message" ,"비밀번호를 입력해주세요.");
                    rttr.addFlashAttribute("modifyTry", "false");
                    rttr.addFlashAttribute("message", "비밀번호를 입력해주세요");
                    return "redirect:/";
                }else{
//                    session.setAttribute("message" ,"닉네임을 입력해주세요.");
                    rttr.addFlashAttribute("modifyTry", "false");
                    rttr.addFlashAttribute("message", "닉네임을 입력해주세요");
                    return "redirect:/";
                }
            }
            if(userService.findByNickname(nickname)!=null){
                User user2 = userService.findByNickname(nickname);
                User user1 = userService.findByUsername(userDetails.getUsername());
                if(!(user2.getNickname().equals(user1.getNickname()))){
//                    session.setAttribute("message" ,"중복되는 닉네임이 존재합니다.");
                    rttr.addFlashAttribute("modifyTry", "false");
                    rttr.addFlashAttribute("message", "중복되는 닉네임이 존재합니다.");
                    return "redirect:/";
                }
            }
            //현재 사용중인 아이디(변경 전)
            User user_=userService.findByUsername(userDetails.getUsername());
            //게시판 작성자 닉네임 변경 => 게시판 변경 시 채팅방 변경 => 채팅방 변경 시 채팅 내용 변경
            boardService.modify(user_.getNickname(),nickname);

            //계정 변경
            User user = userService.findByUsername(username);
            user.setNickname(nickname);
            user.setPassword(password);
            user.setUsername(username);
            userService.save(user);

            rttr.addFlashAttribute("message" ,"수정이 완료되었습니다.");
            rttr.addFlashAttribute("modifyTry", "true");
            return "redirect:/";
        }
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
        if(userService.findByUsername(username)!=null){
            User user = userService.findByUsername(username);

            String randomPassword = Util.randomPassword();
            user.setPassword(randomPassword);
            userService.save(user);
            mailService.sendMail(username, randomPassword);

            model.addAttribute("message", "메일이 발송되었습니다.");
        }else{
            model.addAttribute("message", "존재하지 않는 이메일입니다.");
        }
        return "account/find";
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
