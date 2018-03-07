package com.tomazwang.myapplication.view.diff;

import android.support.v7.util.DiffUtil;
import com.tomazwang.myapplication.data.vo.Order;
import java.util.List;
import java.util.Objects;

/**
 * Created by TomazWang on 2018/03/07.
 *
 * @author Tomaz Wang
 * @since 2018/03/07
 **/

public class OrderDiffCallback extends DiffUtil.Callback{
    private final List<Order> oldList;
    private final List<Order> newList;

    public OrderDiffCallback(List<Order> oldList, List<Order> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Order oldOne = oldList.get(oldItemPosition);
        Order newOne = newList.get(newItemPosition);
        return Objects.equals(oldOne.getId(), newOne.getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Order oldOne = oldList.get(oldItemPosition);
        Order newOne = newList.get(newItemPosition);
        return Objects.equals(oldOne.getId(), newOne.getId())
                && Objects.equals(oldOne.getProductName(), newOne.getProductName())
                && oldOne.getState() == newOne.getState();
    }
}
