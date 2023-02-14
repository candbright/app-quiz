package com.candbright.quiz.model.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.candbright.base.adapter.BaseDiffAdapter;
import com.candbright.base.adapter.BaseViewHolder;
import com.candbright.base.adapter.OnItemEventListener;
import com.candbright.base.adapter.SortedItem;

import java.util.List;


/**
 * <p>created by wyh in 2021/10/19</p>
 * <p>根据{@link SortedItem}中的sortedIndex属性对adapter中的数据进行有序排列。
 * 通过比较Item的toString方法来判断数据改变前后的Item是否一致，如果不一致，则可以调用{@link #notifyDiff()}方法通知RecyclerView刷新布局。</p>
 */
public class MyDiffAdapter extends BaseDiffAdapter {

    private OnItemEventListener mListener;

    public MyDiffAdapter(List<SortedItem> datas) {
        super(datas);
    }

    @Override
    protected boolean areItemsTheSame(SortedItem oldItem, SortedItem newItem) {
        return oldItem.getSortedIndex() == newItem.getSortedIndex();

    }

    @Override
    protected boolean areContentsTheSame(SortedItem oldItem, SortedItem newItem) {
        return oldItem.toString().equals(newItem.toString());
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SortedItem sortedItem = null;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getClass().hashCode() == viewType) {
                sortedItem = data.get(i);
                break;
            }
        }

        if (null == sortedItem) {
            return null;
        }

        return sortedItem.bindViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (mListener != null) {
            holder.setOnItemEventListener(mListener);
        }
        holder.bindViewData(data.get(position));
    }

    public void setOnItemListener(OnItemEventListener l) {
        mListener = l;
    }

    /**
     * 通过Item的index属性得到Item在data中的实际位置
     *
     * @param sortIndex Item的index属性
     * @return Item在data中的实际位置
     */
    public int getRealIndex(int sortIndex) {
        return getRealIndex(super.data, sortIndex);
    }

    public int getRealIndex(List<SortedItem> datas, int sortIndex) {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getSortedIndex() == sortIndex) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取准备修改的Item，并将源数据中的Item变为拷贝，以便于和temp数据区分
     *
     * @param sortIndex
     * @return
     */
    public SortedItem getChangedItem(int sortIndex) {
        int realIndex = getRealIndex(sortIndex);
        if (realIndex == -1) {
            return null;
        }
        SortedItem copy = null;
        try {
            copy = (SortedItem) super.data.get(realIndex).clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        data.set(realIndex, copy);
        return copy;
    }
}
