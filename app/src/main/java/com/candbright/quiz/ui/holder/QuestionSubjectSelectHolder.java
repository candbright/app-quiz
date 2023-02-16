package com.candbright.quiz.ui.holder;

import android.view.View;

import com.candbright.base.adapter.BaseViewHolder;
import com.candbright.quiz.R;
import com.candbright.quiz.databinding.ItemBarSelectorBinding;
import com.candbright.quiz.ui.item.QuestionSubjectSelectItem;
import com.candbright.quiz.util.Utility;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class QuestionSubjectSelectHolder extends BaseViewHolder<QuestionSubjectSelectItem, ItemBarSelectorBinding> {
    public QuestionSubjectSelectHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(QuestionSubjectSelectItem data) {
        rootBinding.tvMode.setText(Utility.getString(data.getData().getSubject()));
        if (!data.isSelected()) {
            rootBinding.tvMode.setTextColor(Utility.getColor(R.color.color_text_hint));
        } else {
            rootBinding.tvMode.setTextColor(Utility.getColor(R.color.pantone_flesh_2));
        }
        rootBinding.tvMode.setOnClickListener(v -> {
            mListener.onItemEvent(data.getSortedIndex(), null);
        });
    }
}
