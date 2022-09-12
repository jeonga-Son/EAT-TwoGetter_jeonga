package com.ll.example.getTwoGetter.Profile;

import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import com.ll.example.getTwoGetter.Board.service.BoardService;
import com.ll.example.getTwoGetter.Util;
import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    UserService userService;

    @Autowired
    BoardService boardService;

    @GetMapping("/showProfile")
    public String showProfile(@AuthenticationPrincipal UserDetails userDetails, Model model){
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user); // user라는 key값에 login된 사용자 user의 정보를 넘긴다.

            List<Board> boards = boardService.findByUsername(user.getNickname());
            model.addAttribute("boards", boards);
        }
        return "profile/myProfile";
    }
    
    @GetMapping("/modifyPassword")
    public String modifyPassword(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String currentPassword, @RequestParam String newPassword, @RequestParam String newPasswordCheck, RedirectAttributes rttr){
        User currentUser = userService.findByUsername(userDetails.getUsername()); //현재 로그인된 사용자

        if(!(Util.checkPassword(currentPassword, currentUser.getPassword()))){
            rttr.addFlashAttribute("modifyTry", "false");
            rttr.addFlashAttribute("message", "현재 비밀번호가 일치하지 않습니다.");
            return "redirect:/showProfile";
        }
        if(!newPassword.equals(newPasswordCheck)){
            rttr.addFlashAttribute("modifyTry", "false");
            rttr.addFlashAttribute("message", "비밀번호를 다시 확인해주세요.");
            return "redirect:/showProfile";
        }
        userService.modifyPassword(currentUser, newPassword);

        return "redirect:/showProfile";
    }

    @GetMapping("/modifyNickname")
    public String modifyNickname(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String newNickname, RedirectAttributes rttr){
        User currentUser = userService.findByUsername(userDetails.getUsername()); //현재 로그인된 사용자

        if(newNickname.length()==0 && newNickname.trim().equals("")){
            rttr.addFlashAttribute("modifyTry", "false");
            rttr.addFlashAttribute("message", "새 별명을 입력해주세요.");
            return "redirect:/showProfile";
        }
        if(userService.findByNickname(newNickname)!=null){
            User oldUser = userService.findByNickname(newNickname);
            if(oldUser.getNickname().equals(currentUser.getNickname())){
                rttr.addFlashAttribute("modifyTry", "false");
                rttr.addFlashAttribute("message", "기존 별명과 동일합니다.");
                return "redirect:/showProfile";
            }else{
                rttr.addFlashAttribute("modifyTry", "false");
                rttr.addFlashAttribute("message", "중복되는 별명이 존재합니다.");
                return "redirect:/showProfile";
            }
        }
        boardService.modify(currentUser.getNickname(),newNickname);
        userService.modifyNickname(currentUser,newNickname);
        rttr.addFlashAttribute("modifyTry", "true");
        rttr.addFlashAttribute("message", "정상적으로 변경되었습니다.");
        return "redirect:/showProfile";
    }
}
