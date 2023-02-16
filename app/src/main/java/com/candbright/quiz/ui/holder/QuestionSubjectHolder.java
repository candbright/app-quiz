package com.candbright.quiz.ui.holder;

import android.view.View;

import com.candbright.base.adapter.DataHolder;
import com.candbright.quiz.databinding.ItemQuestionSubjectBinding;
import com.candbright.quiz.ui.item.QuestionSubjectItem;
import com.candbright.quiz.util.Utility;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionSubjectHolder extends DataHolder<QuestionSubjectItem, ItemQuestionSubjectBinding> {
    public QuestionSubjectHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(QuestionSubjectItem data) {
        rootBinding.tvSubject.setText(Utility.getString(data.getData().getSubject()));
        rootBinding.cardRoot.setOnClickListener(v -> {
            mListener.onItemEvent(data.getSortedIndex(), null);
        });
    }
}
