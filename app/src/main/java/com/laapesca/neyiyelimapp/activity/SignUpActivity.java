package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.laapesca.neyiyelimapp.Model.CountryNameModel;
import com.laapesca.neyiyelimapp.Model.CountryNameModelData;
import com.laapesca.neyiyelimapp.Model.GetCityModel;
import com.laapesca.neyiyelimapp.Model.GetCityModelData;
import com.laapesca.neyiyelimapp.Model.RestaurentModel;
import com.laapesca.neyiyelimapp.Model.RestaurentModelData;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.adapter.CitySpinnerAdapter;
import com.laapesca.neyiyelimapp.adapter.CountryInSpinnerAdapter;
import com.laapesca.neyiyelimapp.adapter.CountrySpinnerAdapter;
import com.laapesca.neyiyelimapp.databinding.ActivitySignUpBinding;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    String message="";
    private SessionManager sessionManager;

    private String code[] ={"1","2","3","4","5","6","7","8","9","10"};
    private int flags[]= {R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo};
    private ArrayList<CountryNameModelData> CountryList = new ArrayList<CountryNameModelData>();
    private ArrayList<GetCityModelData> CityList = new ArrayList<GetCityModelData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);

        init();


    }

    private void init() {

        sessionManager = new SessionManager(SignUpActivity.this);

        binding.btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Validation();

            }
        });

        binding.spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3){

               String CountryId = CountryList.get(pos).getCountryID();

                if (sessionManager.isNetworkAvailable()) {

                    binding.progressBar.setVisibility(View.VISIBLE);

                    getCity(CountryId);

                }else {
                    Toast.makeText(SignUpActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(SignUpActivity.this, CountryId, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(View.VISIBLE);

            getCountry();

        }else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }


    }

    private void Validation() {

        String UserName = binding.edtUserName.getText().toString();
        String Email = binding.edtEmail.getText().toString();
        String Mobile = binding.edtMobile.getText().toString();
        String Pasword = binding.edtPassword.getText().toString();

        if(UserName.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter UserName", Toast.LENGTH_SHORT).show();

        }else if(Email.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();

        }else if(Mobile.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter Mobile", Toast.LENGTH_SHORT).show();

        }else if(Pasword.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();

        }else {

            if (sessionManager.isNetworkAvailable()) {

                binding.progressBar.setVisibility(View.VISIBLE);

                signUpMethod(UserName,Email,Mobile,Pasword);

            }else {
                Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }


        }

    }

    private void signUpMethod(String userName, String email, String mobile, String pasword){

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .signup(userName,email,pasword,mobile,"hbhjbhjh","75.2325","75.2325");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    binding.btSignUp.setEnabled(true);
                    binding.progressBar.setVisibility(View.GONE);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString ("status");
                    message = jsonObject.getString ("message");

                    if (status.equalsIgnoreCase("1")) {

                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));

                    } else {
                        binding.btSignUp.setEnabled(true);
                    }
                } catch (JSONException e) {
                    Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.btSignUp.setEnabled(true);
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getCountry() {

        Call<CountryNameModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_country();
        call.enqueue(new Callback<CountryNameModel>() {
            @Override
            public void onResponse(Call<CountryNameModel> call, Response<CountryNameModel> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    CountryNameModel myclass= response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")){

                        CountryList= (ArrayList<CountryNameModelData>) myclass.getResult();
                        CountryInSpinnerAdapter customAdapter=new CountryInSpinnerAdapter(SignUpActivity.this,flags,CountryList);
                        binding.spinnerCountry.setAdapter(customAdapter);


                    }else {
                        Toast.makeText(SignUpActivity.this, result, Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CountryNameModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getCity(String countryId) {

        Call<GetCityModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_city(countryId);
        call.enqueue(new Callback<GetCityModel>() {
            @Override
            public void onResponse(Call<GetCityModel> call, Response<GetCityModel> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    GetCityModel myclass= response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")){

                       CityList= (ArrayList<GetCityModelData>) myclass.getResult();
                        CitySpinnerAdapter customAdapter=new CitySpinnerAdapter(SignUpActivity.this,flags,CityList);
                        binding.spinnerCity.setAdapter(customAdapter);


                    }else {
                        Toast.makeText(SignUpActivity.this, result, Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetCityModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}