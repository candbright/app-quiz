package com.candbright.quiz.model.data;

import com.candbright.quiz.dao.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Question {
    @Id(autoincrement = true)
    private Long id;
    //难度
    private int difficulty;
    //题目类型
    private String subject;
    //问题
    private String question;
    //选项
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> answers;
    //答案类型
    private String answerType;
    //答案
    private String answer;
    //答案解释
    private String description;


    @Generated(hash = 165085850)
    public Question(Long id, int difficulty, String subject, String question,
            List<String> answers, String answerType, String answer,
            String description) {
        this.id = id;
        this.difficulty = difficulty;
        this.subject = subject;
        this.question = question;
        this.answers = answers;
        this.answerType = answerType;
        this.answer = answer;
        this.description = description;
    }

    @Generated(hash = 1868476517)
    public Question() {
    }


    public Long getId() {
        return id;
    }

    public Question setId(Long id) {
        this.id = id;
        return this;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Question setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Question setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public Question setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswerType() {
        return answerType;
    }

    public Question setAnswerType(String answerType) {
        this.answerType = answerType;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public Question setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public Question setAnswers(List<String> answers) {
        this.answers = answers;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Question setDescription(String description) {
        this.description = description;
        return this;
    }
}
