package com.candbright.quiz.ui.item;

import androidx.annotation.NonNull;

import com.candbright.base.adapter.DataItem;
import com.candbright.quiz.databinding.ItemQuestionDetailBinding;
import com.candbright.quiz.databinding.ItemQuestionInfoBinding;
import com.candbright.quiz.model.data.Question;
import com.candbright.quiz.ui.holder.QuestionDetailHolder;
import com.candbright.quiz.ui.holder.QuestionInfoHolder;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionInfoItem extends DataItem<Question, QuestionInfoHolder, ItemQuestionInfoBinding> {

    public QuestionInfoItem(Question question) {
        super(question);
    }

    @Override
    protected QuestionInfoHolder createViewHolder(ItemQuestionInfoBinding viewBinding) {
        return new QuestionInfoHolder(viewBinding.getRoot());
    }

    @NonNull
    @Override
    public QuestionInfoItem clone() {
        return (QuestionInfoItem) super.clone();
    }
}
