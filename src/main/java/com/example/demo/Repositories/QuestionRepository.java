package com.example.demo.Repositories;

import com.example.demo.Entiti.Question;
import com.example.demo.Entiti.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {
    Question findById(int id);
    List<Question> findAll();
    List<Question> findByStatusAndUser(Boolean status,Users users
    );

}
