package com.candbright.quiz.model.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class QuestionBank {
    @Id(autoincrement = true)
    private Long id;
    //题目类型
    private int subject;

    @Generated(hash = 1391845408)
    public QuestionBank(Long id, int subject) {
        this.id = id;
        this.subject = subject;
    }

    @Generated(hash = 1279952239)
    public QuestionBank() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSubject() {
        return this.subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }
}
