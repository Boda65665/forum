package com.example.demo.Entiti;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="question")
public class Question {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @ManyToOne()
    @JoinColumn(name="user_id", nullable=false)
    private Users user;

    @OneToMany(mappedBy="question")
    List<Answer> Answers = new ArrayList<Answer>();

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    @Column(name="subject")
    private String subject;

    @Column(name = "body")
    private String body;


    @Column(name="status")
    private Boolean status;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Question(Users user, String subject, String body, Boolean status) {
        this.user = user;
        this.subject = subject;
        this.body = body;
        this.status = status;
    }

    public List<Answer> getAnswers() {
        return Answers;
    }

    public void setAnswers(List<Answer> answers) {
        Answers = answers;
    }

    public Question(){}
}
