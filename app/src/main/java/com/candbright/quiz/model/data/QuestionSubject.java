package com.candbright.quiz.model.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class QuestionSubject {
    @Id(autoincrement = true)
    private Long id;
    //题目类型
    private String subject;

    @Generated(hash = 1821314796)
    public QuestionSubject(Long id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    @Generated(hash = 1854115373)
    public QuestionSubject() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public QuestionSubject setSubject(String subject) {
        this.subject = subject;
        return this;
    }
}
