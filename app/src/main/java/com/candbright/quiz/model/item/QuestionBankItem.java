package com.candbright.quiz.model.item;

import androidx.annotation.NonNull;

import com.candbright.base.adapter.SortedItem;
import com.candbright.quiz.databinding.ItemQuestionBankBinding;
import com.candbright.quiz.model.holder.QuestionBankHolder;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionBankItem extends SortedItem<QuestionBankHolder, ItemQuestionBankBinding> {

    //题目类型
    private int subject;

    public int getSubject() {
        return subject;
    }

    public QuestionBankItem setSubject(int subject) {
        this.subject = subject;
        return this;
    }

    @Override
    protected QuestionBankHolder createViewHolder(ItemQuestionBankBinding viewBinding) {
        return new QuestionBankHolder(viewBinding.getRoot());
    }

    @NonNull
    @Override
    public QuestionBankItem clone() {
        return (QuestionBankItem) super.clone();
    }
}
