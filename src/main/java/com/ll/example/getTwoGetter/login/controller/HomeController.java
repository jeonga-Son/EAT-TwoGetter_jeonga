package com.ll.example.getTwoGetter.login.controller;

import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import com.ll.example.getTwoGetter.Board.service.BoardService;
import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.login.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final BoardService boardService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model, HttpSession session,String modifyTry, String message){
        if(userDetails != null){
            String username = userDetails.getUsername();
            User user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        List<Board> boards = boardService.findAll();
        model.addAttribute("board", boards);

        if(modifyTry == null)
            return "index";
        if(modifyTry.equals("false")){
            model.addAttribute("modifyTry", "false");
            model.addAttribute("message",message);
        }else{
            model.addAttribute("modifyTry", "true");
            model.addAttribute("message",message);
        }

        return "index";
    }

    @GetMapping("/about_us")
    public String aboutUs(){
        return "about/about_us";
    }


}
