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
import com.example.demo.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionServiseImp implements QuestionService{
    QuestionConverts convert = new QuestionConverts();
    UserConverters userConverters = new UserConverters();
    AnswerConwert answerConwert = new AnswerConwert();
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public void saveAll(QuestionDTO questionDTO) {
        Question question = convert.QuestionDTOInQuestion(questionDTO);

        Users users = userConverters.FromUserDTOinUsers(questionDTO.getUser());

        question.setUser(users);
        questionRepository.save(question);


    }

    @Override
    public QuestionDTO findById(int id) {
        Question question = questionRepository.findById(id);
        UserDTO userDTO = userConverters.FromUsersInUserDTO(question.getUser());
        QuestionDTO questionDTO = convert.QuestionInQuestionDTO(questionRepository.findById(id));
        List<AnswerDTO> answerDTOS = new ArrayList<AnswerDTO>();
        AnswerDTO answer = new AnswerDTO();

        for(int i = 0;i<question.getAnswers().size();i++) {
            answer = answerConwert.AnswerInAnswerDTO(question.getAnswers().get(i));
            answer.setUserDTO(userConverters.FromUsersInUserDTO(question.getAnswers().get(i).getUsers()));

            answerDTOS.add(answer);
        }
        questionDTO.setAnswers(answerDTOS);
        questionDTO.setUser(userDTO);
        return questionDTO;
    }

    @Override
    public List<QuestionDTO> findByAll() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionDTO> questionDTOS = new ArrayList<QuestionDTO>();

        for(int i=0;i<questions.size();i++) {
            QuestionDTO questionDTO = convert.QuestionInQuestionDTO(questions.get(i));
            Question question = questions.get(i);
            List<AnswerDTO> answerDTOS = new ArrayList<AnswerDTO>();
            AnswerDTO answer = new AnswerDTO();
            for(int j = 0;j<question.getAnswers().size();j++) {

                answer = answerConwert.AnswerInAnswerDTO(question.getAnswers().get(j));
                answer.setUserDTO(userConverters.FromUsersInUserDTO(question.getAnswers().get(j).getUsers()));

                answerDTOS.add(answer);
            }
            UserDTO userDTO = userConverters.FromUsersInUserDTO(question.getUser());
            questionDTO.setAnswers(answerDTOS);

            questionDTO.setUser(userDTO);
            questionDTOS.add(i,questionDTO);

        }
        return questionDTOS;
    }

    @Override
    public void delete(int id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<QuestionDTO> findByStatus(Boolean status, UserDTO userDTOq) {
        Users users = userConverters.FromUserDTOinUsers(userDTOq);
        List<Question> questions = questionRepository.findByStatusAndUser(status,users);
        List<QuestionDTO> questionDTOS = new ArrayList<QuestionDTO>();
        for(int i=0;i<questions.size();i++) {
            QuestionDTO questionDTO = convert.QuestionInQuestionDTO(questions.get(i));
            Question question = questions.get(i);
            UserDTO userDTO = userConverters.FromUsersInUserDTO(question.getUser());

            questionDTO.setUser(userDTO);
           questionDTOS.add(i, questionDTO);
       }
       return questionDTOS;
    }
}
