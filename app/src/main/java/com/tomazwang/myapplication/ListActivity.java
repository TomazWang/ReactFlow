package com.tomazwang.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.tomazwang.myapplication.data.vo.Order;
import com.tomazwang.myapplication.view.OrderAdapter;
import com.tomazwang.myapplication.viewmodel.OrderListVM;
import java.util.List;

/**
 * Created by TomazWang on 2018/03/07.
 *
 * @author Tomaz Wang
 * @since 2018/03/07
 **/

public class ListActivity extends AppCompatActivity {

    private static final String TAG = ListActivity.class.getSimpleName();

    private RecyclerView mRvMainMainList;
    private OrderAdapter mOrderAdapter = new OrderAdapter();
    private OrderListVM mOrderListVM;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRvMainMainList = findViewById(R.id.rv_main_mainList);

        // init view
        mRvMainMainList.setLayoutManager(new LinearLayoutManager(this));
        mRvMainMainList.setAdapter(mOrderAdapter);

        // observe data
        mOrderListVM = ViewModelProviders.of(this).get(OrderListVM.class);

        mOrderListVM.getOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(@Nullable List<Order> orders) {
                mOrderAdapter.swapData(orders);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_id:
                mOrderListVM.sortById();
                break;
            case R.id.sort_by_name:
                mOrderListVM.sortByName();
                break;
            case R.id.sort_by_state:
                mOrderListVM.sortByState();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
