package com.example.demo.Services;

import com.example.demo.Converters.AnswerConwert;
import com.example.demo.Converters.QuestionConverts;
import com.example.demo.Converters.UserConverters;
import com.example.demo.DTO.AnswerDTO;
import com.example.demo.DTO.QuestionDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Answer;
import com.example.demo.Entiti.Question;
import com.example.demo.Entiti.Users;
import com.example.demo.Repositories.UserRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplement implements UserService{
    @Autowired
    UserRepositori repositori;
    QuestionConverts questionConverts = new QuestionConverts();
    UserConverters userConverters = new UserConverters();
    AnswerConwert answerConwert = new AnswerConwert();
    @Override
    public void saveUser(UserDTO usersDto) {
        Users users = new Users();

        users = userConverters.FromUserDTOinUsers(usersDto);

        repositori.save(users);
    }

    @Override
    public UserDTO findByEmail(String email) {
        UserDTO userDTO = userConverters.FromUsersInUserDTO(repositori.findByEmail(email));
        Users users = repositori.findByEmail(email);
        List<Question> questionList = users.getQuestionList();

        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        List<Answer> answers = users.getAnswers();
        List<AnswerDTO> answerDTOS = new ArrayList<AnswerDTO>();
        if(!questionList.isEmpty()) {
            for (int i = 0; i < questionList.size(); i++) {
                questionDTOList.add(questionConverts.QuestionInQuestionDTO(questionList.get(i)));

            }
            userDTO.setQuestionList(questionDTOList);
        }
            if(!answers.isEmpty()){
                for(int i = 0;i<answers.size();i++){
                    AnswerDTO answerDTO = answerConwert.AnswerInAnswerDTO(answers.get(i));

                    QuestionDTO questionDTO = questionConverts.QuestionInQuestionDTO(answers.get(i).getQuestion());
                    answerDTO.setQuestionDTO(questionDTO);
                    answerDTO.setUserDTO(userConverters.FromUsersInUserDTO(answers.get(i).getUsers()));
                    answerDTOS.add(answerDTO);
                }
                userDTO.setAnswers(answerDTOS);
            }

        return userDTO;
    }

    @Override
    public void deleteUser(int id) {
        repositori.deleteById(id);
    }

    @Override
    public List<UserDTO> findAll() {

        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        List<Users> user_list = new ArrayList<Users>();
        user_list = repositori.findAll();

        for(int i=0;i<user_list.size();i++){
            Users user = user_list.get(i);
            UserDTO userDTO = userConverters.FromUsersInUserDTO(user_list.get(i));
            List<Question> questionList = user.getQuestionList();
            List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
            List<Answer> answers = user.getAnswers();
            List<AnswerDTO> answerDTOS = new ArrayList<AnswerDTO>();
            if(!questionList.isEmpty()) {
                for (int j = 0; j < questionList.size(); j++) {
                    questionDTOList.add(questionConverts.QuestionInQuestionDTO(questionList.get(j)));

                }
                userDTO.setQuestionList(questionDTOList);
            }
            if(!answers.isEmpty()){
                for(int g = 0;g<answers.size();g++){
                    AnswerDTO answerDTO = answerConwert.AnswerInAnswerDTO(answers.get(i));

                    QuestionDTO questionDTO = questionConverts.QuestionInQuestionDTO(answers.get(i).getQuestion());
                    answerDTO.setQuestionDTO(questionDTO);
                    answerDTO.setUserDTO(userConverters.FromUsersInUserDTO(answers.get(i).getUsers()));

                    answerDTOS.add(answerDTO);
                }
                userDTO.setAnswers(answerDTOS);
            }
            userDTOList.add(i,userDTO);

        }


        return userDTOList;
    }

    @Override
    public UserDTO findById(int id) {
        UserDTO userDTO = userConverters.FromUsersInUserDTO(repositori.findById(id));
        Users users = repositori.findById(id);
        List<Question> questionList = users.getQuestionList();
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        List<Answer> answers = users.getAnswers();
        List<AnswerDTO> answerDTOS = new ArrayList<AnswerDTO>();
        if(!questionList.isEmpty()) {
            for (int i = 0; i < questionList.size(); i++) {
                questionDTOList.add(questionConverts.QuestionInQuestionDTO(questionList.get(i)));
            }
            userDTO.setQuestionList(questionDTOList);
        }
        if(!answers.isEmpty()){
            for(int i = 0;i<answers.size();i++){
                AnswerDTO answerDTO = answerConwert.AnswerInAnswerDTO(answers.get(i));

                QuestionDTO questionDTO = questionConverts.QuestionInQuestionDTO(answers.get(i).getQuestion());
                answerDTO.setQuestionDTO(questionDTO);
                answerDTO.setUserDTO(userConverters.FromUsersInUserDTO(answers.get(i).getUsers()));

                answerDTOS.add(answerDTO);
            }
            userDTO.setAnswers(answerDTOS);
        }
        return userDTO;
    }
}
