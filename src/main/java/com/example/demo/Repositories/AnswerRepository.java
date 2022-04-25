package com.example.demo.Repositories;

import com.example.demo.Entiti.Answer;
import com.example.demo.Entiti.Question;
import com.example.demo.Entiti.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Integer> {
    Answer findById(int id);
    List<Answer> findAll();
}
