package com.example.demo.Controllers;

import com.example.demo.DTO.QuestionDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Question;
import com.example.demo.JWT.GetDataJWT;
import com.example.demo.JWT.JWT;
import com.example.demo.Services.QuestionServiseImp;
import com.example.demo.Services.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    JWT jwt = new JWT();
    GetDataJWT get_data = new GetDataJWT();
    @Autowired
    UserServiceImplement userService;
    @Autowired
    QuestionServiseImp questionServise;
    @GetMapping("/")
    public String home(){
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(Model model,HttpServletRequest request, HttpServletResponse  response){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user_dto",userDTO);
        String email = get_data.getEmail(request,response);
        return (email.equals("None")) ? "main/login" : "redirect:/home";
    }
    @PostMapping("/login")
    public String login_post(@ModelAttribute("user_dto") UserDTO userDTO,Model model,HttpServletResponse response){
        try {
            UserDTO user = userService.findByEmail(userDTO.getEmail());
            System.out.println(user);
            if (user.getPassword().equals(userDTO.getPassword())){
                Cookie cookie = new Cookie("JWT", jwt.jwt_create(user.getEmail()));
                cookie.setMaxAge(3600);
                response.addCookie(cookie);
                return "redirect:/q";
            }
            else{
                model.addAttribute("not_user","Неверный логин или пароль");
                return "main/login";
            }
        }catch (java.lang.NullPointerException error){
            model.addAttribute("not_user","Неверный логин или пароль");
            return "main/login";
        }
    }
    @PostMapping("/reg")
    public String reg_post(@ModelAttribute("user_dto") @Valid UserDTO userDTO, BindingResult bindingResult,Model model){
        System.out.println("hello");
        if(bindingResult.hasErrors()){

            return "main/reg";
        }
        try {
            UserDTO user = userService.findByEmail(userDTO.getEmail());
            model.addAttribute("user","Данный пользователь уже зарегестрирован!");
            return "main/reg";
        }catch (java.lang.NullPointerException error){
            userService.saveUser(userDTO);
            return "redirect:/login";
        }



    }
    @GetMapping("/reg")
    public String reg_get(HttpServletRequest request, HttpServletResponse  response,Model model){
        String email = get_data.getEmail(request,response);
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user_dto",userDTO);
        System.out.println(email);
        return (email.equals("None")) ? "main/reg" : "redirect:/home";
    }
}
