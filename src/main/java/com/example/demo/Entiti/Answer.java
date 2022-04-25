package com.example.demo.Entiti;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }
    @Column(name="body")
    private String body;
    @Column(name="date")
    private String date;
    @ManyToOne()
    @JoinColumn(name="q_id", nullable=false)
    private Question question;
    @ManyToOne()
    @JoinColumn(name="user_id", nullable=false)
    private Users user;

    public Answer(String body, String date, Question question, Users user) {
        this.body = body;
        this.date = date;
        this.question = question;
        this.user = user;
    }

    public Answer() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Users getUsers() {
        return user;
    }

    public void setUsers(Users user) {
        this.user = user;
    }
}
