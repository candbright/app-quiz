package com.candbright.quiz.ui.item;

import androidx.annotation.NonNull;

import com.candbright.base.adapter.DataItem;
import com.candbright.quiz.databinding.ItemQuestionBinding;
import com.candbright.quiz.model.data.Question;
import com.candbright.quiz.ui.holder.QuestionDetailHolder;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionItem extends DataItem<Question, QuestionDetailHolder, ItemQuestionBinding> {

    public QuestionItem(Question question) {
        super(question);
    }

    @Override
    protected QuestionDetailHolder createViewHolder(ItemQuestionBinding viewBinding) {
        return new QuestionDetailHolder(viewBinding.getRoot());
    }

    @NonNull
    @Override
    public QuestionItem clone() {
        return (QuestionItem) super.clone();
    }
}
