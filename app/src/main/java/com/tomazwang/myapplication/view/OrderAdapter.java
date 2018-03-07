package com.tomazwang.myapplication.view;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tomazwang.myapplication.R;
import com.tomazwang.myapplication.data.vo.Order;
import com.tomazwang.myapplication.view.diff.OrderDiffCallback;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TomazWang on 2018/03/07.
 *
 * @author Tomaz Wang
 * @since 2018/03/07
 **/

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private static final String TAG = OrderAdapter.class.getSimpleName();
    private final List<Order> orderList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_order, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void swapData(List<Order> newList) {
        OrderDiffCallback diffCallback = new OrderDiffCallback(orderList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        orderList.clear();
        orderList.addAll(newList);

        diffResult.dispatchUpdatesTo(this);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTxtDataItemId;
        TextView mTxtDataItemName;
        TextView mTxtDataItemOrder;

        ViewHolder(View itemView) {
            super(itemView);
            mTxtDataItemId = itemView.findViewById(R.id.txt_dataItem_id);
            mTxtDataItemName = itemView.findViewById(R.id.txt_dataItem_name);
            mTxtDataItemOrder = itemView.findViewById(R.id.txt_dataItem_order);
        }

        void bind(Order order) {
            Log.d(TAG, "bind: binding order " + order.getId());
            mTxtDataItemId.setText(order.getId());
            mTxtDataItemName.setText(order.getProductName());
            mTxtDataItemOrder.setText(String.valueOf(order.getState()));
        }
    }
}