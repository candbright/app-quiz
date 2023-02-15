package com.candbright.quiz.model.item;

import androidx.annotation.NonNull;

import com.candbright.base.adapter.SortedItem;
import com.candbright.quiz.databinding.ItemQuestionBinding;
import com.candbright.quiz.model.holder.QuestionHolder;
import com.candbright.quiz.model.holder.QuestionSubjectHolder;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionItem extends SortedItem<QuestionHolder, ItemQuestionBinding> {

    //题目类型
    private String subject;

    public String getSubject() {
        return subject;
    }

    public QuestionItem setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    protected QuestionHolder createViewHolder(ItemQuestionBinding viewBinding) {
        return new QuestionHolder(viewBinding.getRoot());
    }

    @NonNull
    @Override
    public QuestionItem clone() {
        return (QuestionItem) super.clone();
    }
}
