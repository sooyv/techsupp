package techsuppDev.techsupp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String main() {
        return "/main";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/signUp")
    public String Signup() {
        return "/signUp";
    }
}
