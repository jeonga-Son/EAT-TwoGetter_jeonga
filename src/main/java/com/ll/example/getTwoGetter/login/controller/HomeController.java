package com.ll.example.getTwoGetter.login.controller;

import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import com.ll.example.getTwoGetter.Board.domain.repository.BoardRepository;
import com.ll.example.getTwoGetter.Board.service.BoardService;
import com.ll.example.getTwoGetter.login.Repository.UserRepository;
import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.login.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final BoardService boardService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model, HttpSession session){
        //로그인 상태일 경우 user의 정보를 model에 담아서 같이 전달
        if(userDetails != null){
            String username = userDetails.getUsername();
            User user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        //모든 게시글의 정보를 index에 전달
        List<Board> boards = boardService.findAll();
        model.addAttribute("board", boards);
        if(session.getAttribute("message")!=null){
            //session에 저장된 메시지값을 index에 전달
            String message = (String) session.getAttribute("message");
            model.addAttribute("message", message);
        }
        return "index";
    }


}
