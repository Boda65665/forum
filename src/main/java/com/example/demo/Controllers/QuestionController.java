package com.example.demo.Controllers;

import com.example.demo.DTO.AnswerDTO;
import com.example.demo.DTO.QuestionDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.JWT.GetDataJWT;
import com.example.demo.Repositories.AnswerRepository;
import com.example.demo.Services.AnswerServiceImp;
import com.example.demo.Services.QuestionServiseImp;
import com.example.demo.Services.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/q")

public class QuestionController {
    @Autowired
    AnswerServiceImp answerServiceImp;
    GetDataJWT getDataJWT = new GetDataJWT();
    @Autowired
    UserServiceImplement userService;
    @Autowired
    QuestionServiseImp questionServise;

    @GetMapping("/new")
    public String new_get(Model model, HttpServletRequest request, HttpServletResponse response) {
        QuestionDTO questionDTO = new QuestionDTO();
        model.addAttribute("question", questionDTO);
        String email = getDataJWT.getEmail(request, response);
        return (email.equals("None")) ? "redirect:/login" : "question/create";


    }

    @PostMapping("/new")
    public String new_post(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("question") @Valid QuestionDTO questionDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question/create";
        }
        String email = getDataJWT.getEmail(request, response);

        UserDTO user = userService.findByEmail(email);

        questionDTO.setUser(user);
        questionServise.saveAll(questionDTO);
        return "redirect:/q/my?status=false";
    }

    @GetMapping("/my")
    public String rmy_question_get(@RequestParam(value = "status", required = false) Boolean status, Map map, HttpServletRequest request, HttpServletResponse response, Model model) {

        String email = getDataJWT.getEmail(request, response);
        if (email.equals("None")) {
            return "redirect:/login";
        }
        if (status != Boolean.FALSE) {
            status = Boolean.TRUE;
        }
        UserDTO userDTO = userService.findByEmail(email);
        List<QuestionDTO> QuestionDTOs = questionServise.findByStatus(status, userDTO);
        if (QuestionDTOs.isEmpty()) {
            model.addAttribute("not_question", "Question is not");
        }
        System.out.println(QuestionDTOs);
        System.out.println(status);
        map.put("questions", QuestionDTOs);
        return "question/my";

    }

    @GetMapping("/{id}")
    public String question(@RequestParam(value = "status", required = false) Boolean status,@PathVariable("id") int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(status);
        String email = getDataJWT.getEmail(request, response);

        try {
            UserDTO userDTO = userService.findByEmail(email);
            System.out.println(userDTO.getAnswers());
            QuestionDTO questionDTO = questionServise.findById(id);
            if(status!=null){

                questionDTO.setStatus(Boolean.TRUE);
                questionServise.saveAll(questionDTO);
            }
            questionDTO.setBody(questionDTO.getBody().replaceAll("(\r\n|\n)", "<br />"));

            System.out.println(questionDTO.getBody());

            System.out.println("hello");
            List<AnswerDTO> answerDTOS = questionDTO.getAnswers();
            for (int i = 0; i < answerDTOS.size(); i++) {
                AnswerDTO answerDTO = answerDTOS.get(i);
                answerDTO.setBody(answerDTO.getBody().replaceAll("(\r\n|\n)", "<br />"));
            }
            model.addAttribute("question", questionDTO);
            model.addAttribute("answers", answerDTOS);
            if (userDTO.getId() == questionDTO.getUser().getId()) {
                return "question/question_owner";
            }

            return (email.equals("None")) ? "redirect:/login" : "question/question";
        } catch (NullPointerException err) {
            return (email.equals("None")) ? "redirect:/login" : "question/error/not_question";
        }

    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        System.out.println();
        try {
            UserDTO userDTO = userService.findByEmail(getDataJWT.getEmail(request, response));
            QuestionDTO questionDTO = questionServise.findById(id);
            System.out.println(questionDTO);

            if (questionDTO.getUser().getId() == userDTO.getId()) {
                model.addAttribute("question", questionDTO);
                return "question/edit";
            }
            return "question/error/not_owner";
        } catch (java.lang.NullPointerException error) {
            return "redirect:/login";
        }
    }

    @PostMapping("edit/{id}")
    public String edit_post(@RequestParam("body") String body, @RequestParam("subject") String subject, HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id) {
        System.out.println(body);
        try {
            QuestionDTO questionDTO1 = questionServise.findById(id);
            UserDTO userDTO = userService.findByEmail(getDataJWT.getEmail(request, response));
            if (userDTO.getId() == questionDTO1.getUser().getId()) {
                questionDTO1.setSubject(subject);
                questionDTO1.setBody(body);
                questionServise.saveAll(questionDTO1);
                return "redirect:/q/" + questionDTO1.getId();
            }
            return "question/error/not_owner";

        } catch (java.lang.NullPointerException error) {
            System.out.println(error);
            return "redirect:/login";

        }


    }

    @PostMapping("/{id}/answer")
    public String answer(@RequestParam("text") String body, @PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
        String email = getDataJWT.getEmail(request, response);
        UserDTO userDTO = userService.findByEmail(email);
        QuestionDTO questionDTO = questionServise.findById(id);
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println(formatter);
        String date = String.valueOf(dateTime.format(formatter));
        AnswerDTO answerDTO = new AnswerDTO(body, date, questionDTO, userDTO);
        answerServiceImp.save(answerDTO);
        return "redirect:/q/" + id;
    }

}
