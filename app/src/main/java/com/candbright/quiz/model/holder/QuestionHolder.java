package com.candbright.quiz.model.holder;

import android.view.View;

import com.candbright.base.adapter.BaseViewHolder;
import com.candbright.quiz.databinding.ItemQuestionBinding;
import com.candbright.quiz.model.item.QuestionItem;
import com.candbright.quiz.util.Utility;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionHolder extends BaseViewHolder<QuestionItem, ItemQuestionBinding> {
    private static final String TAG = QuestionHolder.class.getSimpleName();

    public QuestionHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(QuestionItem data) {
        rootBinding.tvSubject.setText(Utility.getString(data.getSubject()));
        rootBinding.cardRoot.setOnClickListener(v -> {
            mListener.onItemEvent(data.getSortedIndex(), null);
        });
    }
}
