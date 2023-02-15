package com.candbright.quiz.model.item;

import androidx.annotation.NonNull;

import com.candbright.base.adapter.SortedItem;
import com.candbright.quiz.databinding.ItemQuestionSubjectBinding;
import com.candbright.quiz.model.holder.QuestionSubjectHolder;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionSubjectItem extends SortedItem<QuestionSubjectHolder, ItemQuestionSubjectBinding> {

    //题目类型
    private String subject;

    public String getSubject() {
        return subject;
    }

    public QuestionSubjectItem setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    protected QuestionSubjectHolder createViewHolder(ItemQuestionSubjectBinding viewBinding) {
        return new QuestionSubjectHolder(viewBinding.getRoot());
    }

    @NonNull
    @Override
    public QuestionSubjectItem clone() {
        return (QuestionSubjectItem) super.clone();
    }
}
