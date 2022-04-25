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
import com.example.demo.Repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImp implements AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    QuestionConverts questionConverts = new QuestionConverts();
    UserConverters userConverters = new UserConverters();
    AnswerConwert answerConwert = new AnswerConwert();
    @Override
    public void save(AnswerDTO answerDTO) {
        Answer answer = answerConwert.AnswerDTOInAnswer(answerDTO);
        Question question = questionConverts.QuestionDTOInQuestion(answerDTO.getQuestionDTO());

        Users users = userConverters.FromUserDTOinUsers(answerDTO.getUserDTO());
        answer.setQuestion(question);
        answer.setUsers(users);
        answerRepository.save(answer);
    }

    @Override
    public List<AnswerDTO> findByAll() {
        List<Answer> answerList = answerRepository.findAll();
        List<AnswerDTO> answerDTOS = new ArrayList<AnswerDTO>();
        for(int i = 0;i<answerList.size();i++){
            Question question = answerList.get(i).getQuestion();
            Users users = answerList.get(i).getUsers();
            List<AnswerDTO> answers_question= new ArrayList<AnswerDTO>();

            QuestionDTO questionDTO = new QuestionDTO();
            UserDTO userDTO = new UserDTO();
            List<AnswerDTO> answers= new ArrayList<AnswerDTO>();
            Answer answer = answerList.get(i);
            for(int g = 0;g<answer.getQuestion().getAnswers().size();g++){
                AnswerDTO answerDTO1 = answerConwert.AnswerInAnswerDTO(answer.getQuestion().getAnswers().get(g));
                answerDTO1.setQuestionDTO(questionConverts.QuestionInQuestionDTO(answer.getQuestion()));
                answerDTO1.setUserDTO(userConverters.FromUsersInUserDTO(answer.getUsers()));
                questionDTO.getAnswers().add(answerDTO1);
            }
            for (int j = 0;j<answer.getUsers().getAnswers().size();j++){
                AnswerDTO answerDTO2 = answerConwert.AnswerInAnswerDTO(answer.getUsers().getAnswers().get(j));
                answerDTO2.setUserDTO(userConverters.FromUsersInUserDTO(answer.getUsers()));
                answerDTO2.setQuestionDTO(questionConverts.QuestionInQuestionDTO(answer.getQuestion()));
                userDTO.getAnswers().add(answerDTO2);


            }
            userDTO.setAnswers(answers);
            questionDTO.setAnswers(answers_question);
            AnswerDTO answerDTO = answerConwert.AnswerInAnswerDTO(answerList.get(i));
            answerDTO.setQuestionDTO(questionDTO);
            answerDTO.setUserDTO(userDTO);
            answerDTOS.add(answerDTO);


        }
        return answerDTOS;
    }

    @Override
    public AnswerDTO findByID(int id) {
        Answer answer = answerRepository.findById(id);
        AnswerDTO answerDTO = answerConwert.AnswerInAnswerDTO(answerRepository.findById(id));
        QuestionDTO questionDTO = questionConverts.QuestionInQuestionDTO(answer.getQuestion());
        UserDTO userDTO = userConverters.FromUsersInUserDTO(answer.getUsers());
        for(int i = 0;i<answer.getQuestion().getAnswers().size();i++){
            AnswerDTO answerDTO1 = answerConwert.AnswerInAnswerDTO(answer.getQuestion().getAnswers().get(i));
            answerDTO1.setQuestionDTO(questionConverts.QuestionInQuestionDTO(answer.getQuestion()));
            answerDTO1.setUserDTO(userConverters.FromUsersInUserDTO(answer.getUsers()));
            questionDTO.getAnswers().add(answerDTO1);
        }
        for (int j = 0;j<answer.getUsers().getAnswers().size();j++){
            AnswerDTO answerDTO2 = answerConwert.AnswerInAnswerDTO(answer.getUsers().getAnswers().get(j));
            answerDTO2.setUserDTO(userConverters.FromUsersInUserDTO(answer.getUsers()));
            answerDTO2.setQuestionDTO(questionConverts.QuestionInQuestionDTO(answer.getQuestion()));
            userDTO.getAnswers().add(answerDTO2);


        }
        answerDTO.setUserDTO(userDTO);
        answerDTO.setQuestionDTO(questionDTO);
        return answerDTO;
    }

    @Override
    public void delete(int id) {
        answerRepository.deleteById(id);

    }
}
