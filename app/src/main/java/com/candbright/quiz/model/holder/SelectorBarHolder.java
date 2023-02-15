package com.candbright.quiz.model.holder;

import android.view.View;

import com.candbright.base.adapter.BaseViewHolder;
import com.candbright.quiz.R;
import com.candbright.quiz.databinding.ItemBarSelectorBinding;
import com.candbright.quiz.model.item.SelectorBarItem;
import com.candbright.quiz.util.Utility;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class SelectorBarHolder extends BaseViewHolder<SelectorBarItem, ItemBarSelectorBinding> {
    private static final String TAG = SelectorBarHolder.class.getSimpleName();

    public SelectorBarHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(SelectorBarItem data) {
        if (data.getStrRes() != null) {
            rootBinding.tvMode.setText(Utility.getString(data.getStrRes()));
        } else if (data.getIntRes() != 0) {
            rootBinding.tvMode.setText(Utility.getString(data.getIntRes()));
        }
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
