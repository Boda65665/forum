package com.example.demo.Repositories;

import com.example.demo.Entiti.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositori extends CrudRepository<Users, Integer> {
    Users findByEmail(String email);
    Users findById(int id);
    List<Users> findAll();
}
