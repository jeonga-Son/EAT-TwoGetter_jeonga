package com.ll.example.getTwoGetter.report;

import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.login.model.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
@NoArgsConstructor
public class reportController {
        private UserService userService;

    @GetMapping("/userReport")
    public String userReport(@AuthenticationPrincipal UserDetails userDetails, Model model){
        if(userDetails != null){
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user",user);
        }
        return "report/userReport";
    }
}
