package com.tomazwang.myapplication.data.vo;

import android.support.annotation.NonNull;

/**
 * Created by TomazWang on 2018/03/07.
 *
 * @author Tomaz Wang
 * @since 2018/03/07
 **/

public class Order {

    @NonNull
    private String id = "";

    private int state = 0;

    private String productName = "";

    @NonNull
    public String getId() {
        return id;
    }

    public int getState() {
        return state;
    }

    public String getProductName() {
        return productName;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Order(@NonNull String id, int state, String productName) {
        this.id = id;
        this.state = state;
        this.productName = productName;
    }
}
