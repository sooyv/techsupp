package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import techsuppDev.techsupp.controller.form.UserCreateFrom;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;


//@Controller
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 로그인 창
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("/login");
        return mav;
    }

    // 회원가입 창
    @GetMapping("/signUp")
    public ModelAndView Signup() {
        ModelAndView mav = new ModelAndView("/signUp");
        return mav;
    }

    @PostMapping("/signUp")
    public String signUpSave(UserCreateFrom userCreateFrom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("로그인 실패");
            return "/signUp";
        }
        if (!userCreateFrom.getPassword1().equals(userCreateFrom.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            System.out.println("패스워드 문제");
            return "/singUp";
        }
        userService.createUser(userCreateFrom.getUserName(), userCreateFrom.getEmail(),
                userCreateFrom.getPassword1(), userCreateFrom.getUserPhonenum());
        return "/main";
    }


    // 어드민 페이지
    @GetMapping("admin")
    public String admin() {
        return "/admin";
    }




    @GetMapping("test")
    public String test(HttpSession session, HttpServletResponse response) throws IOException {
        String clientId = "";//애플리케이션 클라이언트 아이디값";
        String redirectURI = URLEncoder.encode("http://localhost:8080/navercallback" , "UTF-8");
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
                + "&client_id=" + clientId
                + "&redirect_uri=" + redirectURI
                + "&state=" + state;
        session.setAttribute("state", state);


//        String html = "<html><body><a href='"+apiURL +"'>aaa</a></body></html>";

        return "redirect:"+apiURL;

    }

    // 네이버 로그인
    @GetMapping("navercallback")
    public void naverCallBack(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String clientId = "";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "9X_i0NyIfC";//애플리케이션 클라이언트 시크릿값";
        String error = request.getParameter("error");
        String error_desc = request.getParameter("error_description");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        System.out.println(code+state);
        String redirectURI = URLEncoder.encode("http://localhost:8080/navercallback", "UTF-8");
        String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&redirect_uri=" + redirectURI
                + "&code=" + code
                + "&state=" + state;
        String accessToken = "";
        String refresh_token = "";
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            PrintWriter out = response.getWriter();
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuilder res = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if (responseCode == 200) {
                out.println(res.toString());
            }
        } catch (Exception e) {
            // Exception 로깅
        }
    }
}
