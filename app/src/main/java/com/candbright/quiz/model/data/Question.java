package com.candbright.quiz.model.data;

import com.candbright.quiz.dao.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;

@Entity
public class Question {
    @Id(autoincrement = true)
    private Long id;
    //难度
    private int difficulty;
    //题目类型
    private int subject;
    //问题描述
    private String description;
    //答案类型
    private int questionType;
    //答案
    private String answer;
    //选项
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> urlPath;

    @Generated(hash = 1190655336)
    public Question(Long id, int difficulty, int subject, String description,
                    int questionType, String answer, List<String> urlPath) {
        this.id = id;
        this.difficulty = difficulty;
        this.subject = subject;
        this.description = description;
        this.questionType = questionType;
        this.answer = answer;
        this.urlPath = urlPath;
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

    public int getSubject() {
        return subject;
    }

    public Question setSubject(int subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Question setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getQuestionType() {
        return questionType;
    }

    public Question setQuestionType(int questionType) {
        this.questionType = questionType;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public Question setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public List<String> getUrlPath() {
        return urlPath;
    }

    public Question setUrlPath(List<String> urlPath) {
        this.urlPath = urlPath;
        return this;
    }
}
