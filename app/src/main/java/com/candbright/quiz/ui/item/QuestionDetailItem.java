package com.candbright.quiz.ui.item;

import androidx.annotation.NonNull;

import com.candbright.base.adapter.DataItem;
import com.candbright.quiz.databinding.ItemQuestionDetailBinding;
import com.candbright.quiz.model.data.Question;
import com.candbright.quiz.ui.holder.QuestionDetailHolder;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionDetailItem extends DataItem<Question, QuestionDetailHolder, ItemQuestionDetailBinding> {

    public QuestionDetailItem(Question question) {
        super(question);
    }

    @Override
    protected QuestionDetailHolder createViewHolder(ItemQuestionDetailBinding viewBinding) {
        return new QuestionDetailHolder(viewBinding.getRoot());
    }

    @NonNull
    @Override
    public QuestionDetailItem clone() {
        return (QuestionDetailItem) super.clone();
    }
}
