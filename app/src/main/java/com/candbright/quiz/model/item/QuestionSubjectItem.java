package com.candbright.quiz.model.item;

import androidx.annotation.NonNull;

import com.candbright.base.adapter.DataItem;
import com.candbright.base.adapter.SortedItem;
import com.candbright.quiz.databinding.ItemQuestionSubjectBinding;
import com.candbright.quiz.model.data.QuestionSubject;
import com.candbright.quiz.model.holder.QuestionSubjectHolder;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionSubjectItem extends DataItem<QuestionSubject, QuestionSubjectHolder, ItemQuestionSubjectBinding> {

    public QuestionSubjectItem(QuestionSubject questionSubject) {
        super(questionSubject);
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
