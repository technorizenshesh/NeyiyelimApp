package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.laapesca.neyiyelimapp.Model.MyOrder;
import com.laapesca.neyiyelimapp.Model.SelectAdreess;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.adapter.MyOrderRecyclerViewAdapter;
import com.laapesca.neyiyelimapp.adapter.SelectedRecyclerViewAdapter;
import com.laapesca.neyiyelimapp.databinding.ActivityMyOrderBinding;

import java.util.ArrayList;

public class MyOrderActivity extends AppCompatActivity {

    ActivityMyOrderBinding binding;

    private MyOrderRecyclerViewAdapter mAdapter;
    private ArrayList<MyOrder> modelList_slected_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_my_order);

        setAdapter();

    }

    private void setAdapter() {

        modelList_slected_list.add(new MyOrder(""));
        modelList_slected_list.add(new MyOrder(""));
        modelList_slected_list.add(new MyOrder(""));
        modelList_slected_list.add(new MyOrder(""));

        mAdapter = new MyOrderRecyclerViewAdapter(MyOrderActivity.this, this.modelList_slected_list);
        binding.recyclerMyorder.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyOrderActivity.this);
        binding.recyclerMyorder.setLayoutManager(linearLayoutManager);
        binding.recyclerMyorder.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new MyOrderRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, MyOrder model) {


            }
        });
    }
}