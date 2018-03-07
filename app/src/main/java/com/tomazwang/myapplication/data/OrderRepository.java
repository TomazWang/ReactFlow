package com.tomazwang.myapplication.data;

import android.content.Context;
import com.tomazwang.myapplication.data.vo.Order;
import com.tomazwang.myapplication.service.OrderRemoteDataSource;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by TomazWang on 2018/03/07.
 *
 * @author Tomaz Wang
 * @since 2018/03/07
 **/
public class OrderRepository{

    private final OrderRemoteDataSource mRemoteDataSource;

    public OrderRepository(Context context) {
        mRemoteDataSource = new OrderRemoteDataSource();
    }

    public Flowable<List<Order>> getOrders(){
        return mRemoteDataSource.fetchOrders();
    }

}
