package com.example.demo.Services;

import com.example.demo.DTO.AnswerDTO;
import com.example.demo.DTO.QuestionDTO;
import com.example.demo.Entiti.Answer;

import java.util.List;

public interface AnswerService
{
    void save(AnswerDTO answerDTO);
    List<AnswerDTO> findByAll();
    AnswerDTO findByID(int id);
    void delete(int id);
}
