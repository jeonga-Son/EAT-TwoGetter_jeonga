package com.ll.example.getTwoGetter.middleMap.Controller;

import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/middlePlaceMap")
public class MiddlePlaceMapController {


    @Autowired
    UserService userService;

    @GetMapping("/middleMap")
    public String showMap(@AuthenticationPrincipal UserDetails userDetails, Model model, HttpSession session, @RequestParam String lat, @RequestParam String lng) {

        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user); // user라는 key값에 login된 사용자 user의 정보를 넘긴다.
            model.addAttribute("lat", lat);
            model.addAttribute("lng", lng);
        }
        if (session.getAttribute("message") != null) {
            String message = (String) session.getAttribute("message");
            model.addAttribute("message", message);
        }
        return "middlePlaceMap/middleMap";
    }

}
