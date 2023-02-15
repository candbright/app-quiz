package com.candbright.quiz.model.item;

import androidx.annotation.NonNull;

import com.candbright.base.adapter.DataItem;
import com.candbright.base.adapter.SortedItem;
import com.candbright.quiz.databinding.ItemQuestionBinding;
import com.candbright.quiz.model.data.Question;
import com.candbright.quiz.model.holder.QuestionHolder;
import com.candbright.quiz.model.holder.QuestionSubjectHolder;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionItem extends DataItem<Question, QuestionHolder, ItemQuestionBinding> {

    public QuestionItem(Question question) {
        super(question);
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
