package com.candbright.quiz.model.holder;

import android.view.View;

import com.candbright.base.adapter.BaseViewHolder;
import com.candbright.base.adapter.DataHolder;
import com.candbright.quiz.databinding.ItemQuestionBinding;
import com.candbright.quiz.model.item.QuestionItem;
import com.candbright.quiz.util.Utility;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionHolder extends DataHolder<QuestionItem, ItemQuestionBinding> {
    private static final String TAG = QuestionHolder.class.getSimpleName();

    public QuestionHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(QuestionItem data) {
        rootBinding.tvTitle.setText(Utility.getString(data.getData().getSubject()));
        rootBinding.getRoot().setOnClickListener(v -> {
            mListener.onItemEvent(data.getSortedIndex(), null);
        });
    }
}
