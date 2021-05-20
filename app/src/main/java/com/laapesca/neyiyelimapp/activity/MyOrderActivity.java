package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.laapesca.neyiyelimapp.Model.GetCardOne;
import com.laapesca.neyiyelimapp.Model.GetCardTwo;
import com.laapesca.neyiyelimapp.Model.MyOrder;
import com.laapesca.neyiyelimapp.Model.MyOrderModel;
import com.laapesca.neyiyelimapp.Model.MyOrderModelData;
import com.laapesca.neyiyelimapp.Model.SelectAdreess;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.adapter.MyOrderRecyclerViewAdapter;
import com.laapesca.neyiyelimapp.adapter.SelectedRecyclerViewAdapter;
import com.laapesca.neyiyelimapp.databinding.ActivityMyOrderBinding;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderActivity extends AppCompatActivity {

    ActivityMyOrderBinding binding;
    private SessionManager sessionManager;

    private MyOrderRecyclerViewAdapter mAdapter;
    private ArrayList<MyOrderModelData> modelList_slected_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_my_order);

        sessionManager = new SessionManager(MyOrderActivity.this);

        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(View.VISIBLE);

            getMyorderMethod();

        }else {
            Toast.makeText(MyOrderActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }




    }

    private void setAdapter(ArrayList<MyOrderModelData> modelList_slected_list) {

        mAdapter = new MyOrderRecyclerViewAdapter(MyOrderActivity.this, modelList_slected_list);
        binding.recyclerMyorder.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyOrderActivity.this);
        binding.recyclerMyorder.setLayoutManager(linearLayoutManager);
        binding.recyclerMyorder.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new MyOrderRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, MyOrderModelData model) {


            }
        });
    }

    public void getMyorderMethod() {

        String user_id = Preference.get( MyOrderActivity.this, Preference.KEY_USER_ID);

        Call<MyOrderModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_my_order(user_id);
        call.enqueue(new Callback<MyOrderModel>() {
            @Override
            public void onResponse(Call<MyOrderModel> call, Response<MyOrderModel> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);


                    MyOrderModel myclass= response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")){

                        binding.txtEmty.setVisibility(View.GONE);
                        modelList_slected_list = (ArrayList<MyOrderModelData>) myclass.getResult();
                        setAdapter(modelList_slected_list);

                    }else {
                        binding.txtEmty.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    binding.txtEmty.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MyOrderModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.txtEmty.setVisibility(View.VISIBLE);
            }
        });
    }
}