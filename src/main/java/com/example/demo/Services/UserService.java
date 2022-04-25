package com.example.demo.Services;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Question;
import com.example.demo.Entiti.Users;

import java.util.List;

public interface UserService{
    void saveUser(UserDTO usersDto);
    UserDTO findByEmail(String email);

    void deleteUser(int id);

    List<UserDTO> findAll();

    UserDTO findById(int id);


}
