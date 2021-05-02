package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.laapesca.neyiyelimapp.Model.DeliveryAddressModelAdreess;
import com.laapesca.neyiyelimapp.Model.GetSummeryModel;
import com.laapesca.neyiyelimapp.Model.SelectAdreess;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.databinding.ActivityCheckOutSummeryPageBinding;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutSummeryPage extends AppCompatActivity {

    ActivityCheckOutSummeryPageBinding binding;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_check_out_summery_page);

        sessionManager = new SessionManager(CheckOutSummeryPage.this);

        binding.checkOnline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.checkCash.setChecked(false);
                }
            }
        });

        binding.checkCash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.checkOnline.setChecked(false);
                }
            }
        });

        binding.btnFinalPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CheckOutSummeryPage.this, HomeScreenActivity.class));
            }
        });

        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(View.VISIBLE);

            getSummeryMethod();

        }else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }




    }


    public void getSummeryMethod() {

        String user_id = Preference.get(CheckOutSummeryPage.this, Preference.KEY_USER_ID);

        Call<GetSummeryModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_summary(user_id);
        call.enqueue(new Callback<GetSummeryModel>() {
            @Override
            public void onResponse(Call<GetSummeryModel> call, Response<GetSummeryModel> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    GetSummeryModel myclass= response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")){

                        binding.txtReName.setText(myclass.getResult().get(0).getRestaurantName());
                        binding.txtAddressType.setText(myclass.getResult().get(0).getAddressDetial().getAddressType());
                        binding.txtAddressDirection.setText(myclass.getResult().get(0).getAddressDetial().getAddressDirection());
                        binding.txtAddress.setText(myclass.getResult().get(0).getAddressDetial().getAddress());
                        binding.txtTotal.setText(myclass.getTotal()+"");

                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetSummeryModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }


    public void getPlaceOrder() {

        String user_id = Preference.get(CheckOutSummeryPage.this, Preference.KEY_USER_ID);

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .add_placeorder(user_id,"","","","","",
                        "","","","","","");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString ("status");

                    if (status.equalsIgnoreCase("1")){

                        Toast.makeText(CheckOutSummeryPage.this, "SuccessFully.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}