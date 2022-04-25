package com.example.demo.Converters;

import com.example.demo.DTO.AnswerDTO;
import com.example.demo.Entiti.Answer;

public class AnswerConwert {
    public AnswerDTO AnswerInAnswerDTO(Answer answer){
        AnswerDTO answere = new AnswerDTO();
        answere.setDate(answer.getDate());
        answere.setBody(answer.getBody());
        return answere;
    }
    public Answer AnswerDTOInAnswer(AnswerDTO answer){
        Answer answere = new Answer();
        answere.setDate(answer.getDate());
        answere.setBody(answer.getBody());
        return answere;
    }

}
