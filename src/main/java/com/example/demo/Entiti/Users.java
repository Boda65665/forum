package com.example.demo.Entiti;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity
public class Users {
    @Column(name="email")
    String email;
    @Column(name = "login")
    private String login;
    @Column(name="password")
    private String password;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy="user")
    List<Question> questionList = new ArrayList<Question>();

    @OneToMany(mappedBy="user")
    List<Answer> Answers = new ArrayList<Answer>();

    public Users() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Answer> getAnswers() {
        return Answers;
    }

    public void setAnswers(List<Answer> answers) {
        Answers = answers;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
