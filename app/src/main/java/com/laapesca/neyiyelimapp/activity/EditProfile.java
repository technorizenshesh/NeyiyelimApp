package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.laapesca.neyiyelimapp.Model.MyProfileModel;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.databinding.ActivityEditProfileBinding;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {

    ActivityEditProfileBinding binding;
    private SessionManager sessionManager;
    String message ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_profile);

        sessionManager = new SessionManager(this);

        binding.RRBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String UserName= binding.edtUser.getText().toString();
                String mobile = binding.edtMobile.getText().toString();
                String Email = binding.edEmail.getText().toString();

                if(UserName.equalsIgnoreCase(""))
                {

                    Toast.makeText(EditProfile.this, "Please Enter UserName.", Toast.LENGTH_SHORT).show();

                }else if(mobile.equalsIgnoreCase(""))
                {
                    Toast.makeText(EditProfile.this, "Please Enter Mobile.", Toast.LENGTH_SHORT).show();

                }else if(Email.equalsIgnoreCase(""))
                {
                    Toast.makeText(EditProfile.this, "Please Enter Email.", Toast.LENGTH_SHORT).show();

                }else {

                    if (sessionManager.isNetworkAvailable()) {

                        binding.progressBar.setVisibility(View.VISIBLE);

                        UpdateMethod(UserName,mobile,Email);

                    }else {
                        Toast.makeText(EditProfile.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(View.VISIBLE);

            callProfileApi();

        }else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }

    }

    private void UpdateMethod(String UserName,String mobile,String Email){

        String UserId = Preference.get(this, Preference.KEY_USER_ID);

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .update_profile(UserId,UserName,Email,mobile,"abc.jpg");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString ("status");
                    message = jsonObject.getString ("message");

                    if (status.equalsIgnoreCase("1")) {

                        Toast.makeText(EditProfile.this, message, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(EditProfile.this, HomeScreenActivity.class));

                    }
                } catch (JSONException e) {
                    Toast.makeText(EditProfile.this, message, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(EditProfile.this, message, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(EditProfile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void callProfileApi() {

        String UserId = Preference.get(this, Preference.KEY_USER_ID);

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
                        String City =myLogin.getResult().getCity().toString();

                        binding.edtUser.setText(UserName);
                        binding.edtMobile.setText(Mobile);
                        binding.edEmail.setText(Email);
                        //String Country =myLogin.getResult().getEmail().toString();

                    }else {

                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(EditProfile.this, myLogin.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(EditProfile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}