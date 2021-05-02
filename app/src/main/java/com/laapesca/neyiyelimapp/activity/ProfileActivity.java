package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.laapesca.neyiyelimapp.Model.MyProfileModel;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.databinding.ActivityProfileBinding;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);

        sessionManager = new SessionManager(ProfileActivity.this);

        binding.RRBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(View.VISIBLE);

            callProfileApi();

        }else {
            Toast.makeText(ProfileActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }

    }

    private void callProfileApi() {

        String UserId = Preference.get(ProfileActivity.this, Preference.KEY_USER_ID);

        Call<MyProfileModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .getProfile(UserId);
        call.enqueue(new Callback<MyProfileModel>() {
            @Override
            public void onResponse(Call<MyProfileModel> call, Response<MyProfileModel> response) {
                try {

                    MyProfileModel myLogin = response.body();

                    if (myLogin.getStatus().equalsIgnoreCase("1")){

                        String UserName =myLogin.getResult().getUsername().toString();
                        String Email =myLogin.getResult().getEmail().toString();
                        String Mobile =myLogin.getResult().getCellPhone().toString();

                        binding.txxtUserName.setText(UserName);
                        binding.txxtEmail.setText(Email);
                        if(Mobile.equalsIgnoreCase(""))
                        {
                            binding.txxtMobile.setText("Null");

                        }else {

                            binding.txxtMobile.setText(Mobile);
                        }

                        //String Country =myLogin.getResult().getEmail().toString();

                    }else {

                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(ProfileActivity.this, myLogin.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<MyProfileModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}