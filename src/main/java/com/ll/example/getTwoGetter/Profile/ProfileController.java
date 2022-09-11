package com.ll.example.getTwoGetter.Profile;

import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {
    @Autowired
    UserService userService;
    @GetMapping("/showProfile")
    public String showProfile(@AuthenticationPrincipal UserDetails userDetails, Model model){
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user); // user라는 key값에 login된 사용자 user의 정보를 넘긴다.
        }
        return "profile/myProfile";
    }
    @GetMapping("/modifyPassword")
    public String modifyPassword(@RequestParam String currentPassword, @RequestParam String newPassword, @RequestParam String newPasswordCheck){
        System.out.println(currentPassword);
        System.out.println(newPassword);
        System.out.println(newPasswordCheck);
        return "redirect:/showProfile";
    }
    @GetMapping("/modifyNickname")
    public String modifyNickname(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String newNickname, RedirectAttributes rttr){
        
        System.out.println(newNickname);
        return "redirect:/showProfile";
    }
}
