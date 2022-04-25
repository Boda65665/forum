package com.example.demo.DTO;

import lombok.Data;

@Data
public class AnswerDTO {
    private String body;
    private String date;
    QuestionDTO questionDTO;
    UserDTO userDTO;

    public AnswerDTO(String body, String date, QuestionDTO questionDTO, UserDTO userDTO) {
        this.body = body;
        this.date = date;
        this.questionDTO = questionDTO;
        this.userDTO = userDTO;
    }

    public AnswerDTO() {
    }
}
