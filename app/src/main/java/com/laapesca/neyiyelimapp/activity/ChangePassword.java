package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.databinding.ActivityChangePasswordBinding;
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

public class ChangePassword extends AppCompatActivity {

    ActivityChangePasswordBinding binding;

    private SessionManager sessionManager;
    String message ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_change_password);

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

                String CurrentPassword= binding.edtCurrentPassword.getText().toString();
                String NewPassword = binding.edtNewPassword.getText().toString();

                if(CurrentPassword.equalsIgnoreCase(""))
                {

                    Toast.makeText(ChangePassword.this, "Please Enter Current Password.", Toast.LENGTH_SHORT).show();

                }else if(NewPassword.equalsIgnoreCase(""))
                {
                    Toast.makeText(ChangePassword.this, "Please Enter New Password.", Toast.LENGTH_SHORT).show();

                }else {

                    if (sessionManager.isNetworkAvailable()) {

                        binding.progressBar.setVisibility(View.VISIBLE);

                        ChangePasswordMethod(CurrentPassword,NewPassword);

                    }else {
                        Toast.makeText(ChangePassword.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    private void ChangePasswordMethod(String CurrentPassword,String NewPassword){

        String UserId = Preference.get(this, Preference.KEY_USER_ID);

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .change_password(UserId,CurrentPassword,NewPassword);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString ("status");
                    message = jsonObject.getString ("message");

                    if (status.equalsIgnoreCase("1")) {

                        Toast.makeText(ChangePassword.this, message, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(ChangePassword.this, HomeScreenActivity.class));

                    }else
                    {
                        Toast.makeText(ChangePassword.this, message, Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    Toast.makeText(ChangePassword.this, message, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(ChangePassword.this, message, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(ChangePassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}