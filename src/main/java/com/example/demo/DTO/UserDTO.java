package com.example.demo.DTO;

import com.example.demo.Converters.QuestionConverts;
import com.example.demo.Entiti.Answer;
import com.example.demo.Entiti.Question;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO{
    int id;

    @Size(min = 5,max = 15,message = "password for 5 to 15 symbols")
    String password;
    @Size(min = 1,message = "min 1 symbol")
    @Email(message = "No valid email")
    String email;
    @Size(min = 3,max = 15,message = "username for 5 to 15 symbols")
    String username;

    List<QuestionDTO> questionList = new ArrayList<QuestionDTO>();
    public UserDTO(String password, String email, String username) {
        this.password = password;
        this.email = email;
        this.username = username;
    }

    List<AnswerDTO> Answers = new ArrayList<AnswerDTO>();


    public UserDTO(){}
}
