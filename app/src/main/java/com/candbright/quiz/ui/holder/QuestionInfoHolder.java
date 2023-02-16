package com.candbright.quiz.ui.holder;

import android.view.View;

import com.candbright.base.adapter.DataHolder;
import com.candbright.quiz.databinding.ItemQuestionInfoBinding;
import com.candbright.quiz.ui.item.QuestionItem;
import com.candbright.quiz.util.Utility;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionInfoHolder extends DataHolder<QuestionItem, ItemQuestionInfoBinding> {

    public QuestionInfoHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(QuestionItem data) {
        rootBinding.tvQuestion.setText(Utility.getString(data.getData().getQuestion()));
        rootBinding.getRoot().setOnClickListener(v -> {
            mListener.onItemEvent(data.getSortedIndex(), null);
        });
    }
}
