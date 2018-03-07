package com.tomazwang.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.tomazwang.myapplication.data.OrderRepository;
import com.tomazwang.myapplication.data.vo.Order;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by TomazWang on 2018/03/07.
 *
 * @author Tomaz Wang
 * @since 2018/03/07
 **/

public class OrderListVM extends AndroidViewModel {

    private static final String TAG = OrderListVM.class.getSimpleName();

    private final OrderRepository mOrderRepo;
    private final MutableLiveData<List<Order>> liveOrders = new MutableLiveData<>();
    private Disposable orderListSubscribtion;

    public OrderListVM(@NonNull Application application) {
        super(application);
        mOrderRepo = new OrderRepository(this.getApplication());
        sortById();
    }

    public LiveData<List<Order>> getOrders() {
        return liveOrders;
    }

    public void sortByName() {
        if (orderListSubscribtion != null && !orderListSubscribtion.isDisposed()) {
            orderListSubscribtion.dispose();
        }

        orderListSubscribtion = mOrderRepo.getOrders()
                .doOnNext(new Consumer<List<Order>>() {
                    @Override
                    public void accept(List<Order> orders) throws Exception {
                        Collections.sort(orders, new Comparator<Order>() {
                            @Override
                            public int compare(Order o1, Order o2) {
                                return o1.getProductName().compareTo(o2.getProductName());
                            }
                        });
                        liveOrders.postValue(orders);
                    }
                })
                .subscribe();
    }

    public void sortById() {
        if (orderListSubscribtion != null && !orderListSubscribtion.isDisposed()) {
            orderListSubscribtion.dispose();
        }

        orderListSubscribtion = mOrderRepo.getOrders()
                .doOnNext(new Consumer<List<Order>>() {
                    @Override
                    public void accept(List<Order> orders) throws Exception {
                        Collections.sort(orders, new Comparator<Order>() {
                            @Override
                            public int compare(Order o1, Order o2) {
                                return o1.getId().compareTo(o2.getId());
                            }
                        });
                        liveOrders.postValue(orders);
                    }
                })
                .subscribe();
    }

    public void sortByState() {
        if (orderListSubscribtion != null && !orderListSubscribtion.isDisposed()) {
            orderListSubscribtion.dispose();
        }

        orderListSubscribtion = mOrderRepo.getOrders()
                .doOnNext(new Consumer<List<Order>>() {
                    @Override
                    public void accept(List<Order> orders) throws Exception {
                        Collections.sort(orders, new Comparator<Order>() {
                            @Override
                            public int compare(Order o1, Order o2) {
                                return o1.getState() - o2.getState();
                            }
                        });
                        liveOrders.postValue(orders);
                    }
                })
                .subscribe();
    }
}
