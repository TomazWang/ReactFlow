package com.tomazwang.myapplication.service;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.tomazwang.myapplication.data.vo.Order;
import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TomazWang on 2018/03/07.
 *
 * @author Tomaz Wang
 * @since 2018/03/07
 **/

public class OrderRemoteDataSource {

    private static final String TAG = OrderRemoteDataSource.class.getSimpleName();
    private static final char[] CHARS =
            new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N' };

    private List<Order> mockOrderLsit = new ArrayList<>();
    private Handler mIoHandler;
    private BehaviorProcessor<List<Order>> dataSource = BehaviorProcessor.create();

    private Handler io() {
        if (mIoHandler == null) {
            HandlerThread handlerThread = new HandlerThread("remote io");
            handlerThread.start();
            mIoHandler = new Handler(handlerThread.getLooper());
        }

        return mIoHandler;
    }

    public OrderRemoteDataSource() {
        start();
    }

    private void start() {
        // do some init
        next();
    }

    @SuppressLint("DefaultLocale")
    private void next() {
        Log.d(TAG, "next: create an order");
        int size = mockOrderLsit.size();
        String id = "";

        if (size > 9999) {
            size = size - 9999;
            id = id + "A";
        }

        id = id + String.format("%04d", size);

        int seed1 = (int) (Math.random() * 30);
        int seed2 = (int) (Math.random() * 45);

        char name1 = CHARS[seed1 % CHARS.length];
        char name2 = CHARS[seed2 % CHARS.length];

        mockOrderLsit.add(new Order(id, (seed1 + seed2) % 3, String.valueOf(name1) + String.valueOf(name2) + id));

        io().postDelayed(new Runnable() {
            @Override
            public void run() {
                next();
            }
        }, 5000);

        dataSource.onNext(mockOrderLsit);
    }

    public Flowable<List<Order>> fetchOrders() {
        return dataSource.onBackpressureLatest();
    }
}
