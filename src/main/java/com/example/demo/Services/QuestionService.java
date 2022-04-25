package com.example.demo.Services;

import com.example.demo.DTO.QuestionDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Question;

import java.util.List;

public interface QuestionService {
    void saveAll(QuestionDTO question);
    QuestionDTO findById(int id);
    List<QuestionDTO> findByAll();
    void delete(int id);
    List<QuestionDTO> findByStatus(Boolean status,UserDTO userDTO);
}