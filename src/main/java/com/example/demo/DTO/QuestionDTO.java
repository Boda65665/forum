package com.example.demo.DTO;

import com.example.demo.Entiti.Answer;
import com.example.demo.Entiti.Users;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionDTO {

    int id;
    @Size(min = 10,max = 500,message = "symbols in body for 10 to 500")
    String body;
    @Size(min = 5,max = 25,message = "symbols in subject for 5 to 25")
    String subject;

    Boolean status = Boolean.FALSE;
    UserDTO user;

    public QuestionDTO(String body, String subject, Boolean status) {
        this.body = body;
        this.subject = subject;
        this.status = status;
    }
    List<AnswerDTO> Answers = new ArrayList<AnswerDTO>();

//    @Override
//    public String toString() {
//        return "QuestionDTO{" +
//                "id=" + id +
//                ", body='" + body + '\'' +
//                ", subject='" + subject + '\'' +
//                ", status=" + status +
//                ", user=" + user +
//                '}';
//    }

    public QuestionDTO() {
    }
}
