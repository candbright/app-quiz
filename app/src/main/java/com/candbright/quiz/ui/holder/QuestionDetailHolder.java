package com.candbright.quiz.ui.holder;

import android.view.View;

import com.candbright.base.adapter.DataHolder;
import com.candbright.quiz.databinding.ItemQuestionDetailBinding;
import com.candbright.quiz.ui.item.QuestionInfoItem;
import com.candbright.quiz.util.Utility;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionDetailHolder extends DataHolder<QuestionInfoItem, ItemQuestionDetailBinding> {

    public QuestionDetailHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(QuestionInfoItem data) {
        rootBinding.tvTitle.setText(data.getData().getSubject());
        rootBinding.getRoot().setOnClickListener(v -> {
            mListener.onItemEvent(data.getSortedIndex(), null);
        });
    }
}
