package com.example.demo.Converters;

import com.example.demo.DTO.QuestionDTO;
import com.example.demo.Entiti.Question;

public class QuestionConverts {
    UserConverters converters = new UserConverters();

    public QuestionDTO QuestionInQuestionDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setBody(question.getBody());
        questionDTO.setStatus(question.getStatus());
        questionDTO.setId(question.getId());
        questionDTO.setSubject(question.getSubject());
        return questionDTO;
    }

    public Question QuestionDTOInQuestion(QuestionDTO question) {
        Question questionDTO = new Question();
        questionDTO.setBody(question.getBody());
        questionDTO.setStatus(question.getStatus());
        questionDTO.setId(question.getId());
        questionDTO.setSubject(question.getSubject());
        return questionDTO;
    }
}
