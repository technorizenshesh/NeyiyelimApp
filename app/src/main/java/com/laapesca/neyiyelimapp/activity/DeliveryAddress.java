package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.laapesca.neyiyelimapp.Model.DeliveryAddressModelAdreess;
import com.laapesca.neyiyelimapp.Model.GetCardOne;
import com.laapesca.neyiyelimapp.Model.GetCardTwo;
import com.laapesca.neyiyelimapp.Model.SelectAdreess;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.adapter.SelectedRecyclerViewAdapter;
import com.laapesca.neyiyelimapp.databinding.ActivityDeliveryAddressBinding;
import com.laapesca.neyiyelimapp.listner.onListClickListener;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryAddress extends AppCompatActivity  implements onListClickListener {

    ActivityDeliveryAddressBinding binding;
    private SelectedRecyclerViewAdapter mAdapter;
    private ArrayList<SelectAdreess> modelList_slected_list = new ArrayList<>();
    private SessionManager sessionManager;

    String delivery_address_id= "";
    String TypeAddress="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_delivery_address);

        sessionManager = new SessionManager(DeliveryAddress.this);


        Intent intent=getIntent();
        if(intent!=null)
        {
            TypeAddress = intent.getStringExtra("Type");
        }

        binding.RRBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();


            }
        });

        binding.btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DeliveryAddress.this, AddAddressActivity.class));
                finish();
            }
        });

        binding.btnSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!delivery_address_id.equalsIgnoreCase(""))
                {
                    if (sessionManager.isNetworkAvailable()) {

                        binding.progressBar.setVisibility(View.VISIBLE);

                        addSelectAddress(delivery_address_id);

                    }else {
                        Toast.makeText(DeliveryAddress.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
                    }

                }else
                {
                    if(TypeAddress.equalsIgnoreCase("Payment"))
                    {
                        startActivity(new Intent(DeliveryAddress.this, CheckOutSummeryPage.class));

                    }else
                    {
                        startActivity(new Intent(DeliveryAddress.this, HomeScreenActivity.class));
                    }

                }


            }
        });


        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(View.VISIBLE);

            getDeliveryMethod();

        }else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }



    }

    private void setAdapter(ArrayList<SelectAdreess> modelList_slected_list) {

        mAdapter = new SelectedRecyclerViewAdapter(DeliveryAddress.this, this.modelList_slected_list,DeliveryAddress.this);
        binding.recyclerViewSelectedLest.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DeliveryAddress.this);
        binding.recyclerViewSelectedLest.setLayoutManager(linearLayoutManager);
        binding.recyclerViewSelectedLest.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new SelectedRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, SelectAdreess model) {


            }
        });
    }

    public void getDeliveryMethod() {

        String user_id = Preference.get(DeliveryAddress.this, Preference.KEY_USER_ID);

        Call<DeliveryAddressModelAdreess> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_delivery_address(user_id);
        call.enqueue(new Callback<DeliveryAddressModelAdreess>() {
            @Override
            public void onResponse(Call<DeliveryAddressModelAdreess> call, Response<DeliveryAddressModelAdreess> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);


                    DeliveryAddressModelAdreess myclass= response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")){

                        binding.txtEmty.setVisibility(View.GONE);

                        modelList_slected_list = (ArrayList<SelectAdreess>) myclass.getResult();

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
            public void onFailure(Call<DeliveryAddressModelAdreess> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.txtEmty.setVisibility(View.VISIBLE);
            }
        });
    }

    public void addSelectAddress(String delivery_address_id) {

        String user_id = Preference.get(DeliveryAddress.this, Preference.KEY_USER_ID);

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .add_select_address(delivery_address_id,user_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString ("status");

                    if (status.equalsIgnoreCase("1")){

                        if(TypeAddress.equalsIgnoreCase("Payment"))
                        {
                            startActivity(new Intent(DeliveryAddress.this, CheckOutSummeryPage.class));

                        }else
                        {
                            startActivity(new Intent(DeliveryAddress.this, HomeScreenActivity.class));
                        }

                        Toast.makeText(DeliveryAddress.this, "SuccessFully Save.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void listClick(int i, String DeliveryAddress_id) {

        delivery_address_id=DeliveryAddress_id;

    }
}