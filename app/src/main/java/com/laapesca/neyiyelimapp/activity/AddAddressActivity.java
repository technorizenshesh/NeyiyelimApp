package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.laapesca.neyiyelimapp.Model.CountryNameModel;
import com.laapesca.neyiyelimapp.Model.CountryNameModelData;
import com.laapesca.neyiyelimapp.Model.GetCityModel;
import com.laapesca.neyiyelimapp.Model.GetCityModelData;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.adapter.CitySpinnerAdapter;
import com.laapesca.neyiyelimapp.adapter.CountryInSpinnerAdapter;
import com.laapesca.neyiyelimapp.databinding.ActivityAddAddressBinding;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  AddAddressActivity extends AppCompatActivity {

    ActivityAddAddressBinding binding;
    private SessionManager sessionManager;
    String AddressType = "";
    private ArrayList<CountryNameModelData> CountryList = new ArrayList<CountryNameModelData>();
    private ArrayList<GetCityModelData> CityList = new ArrayList<GetCityModelData>();
    private int flags[]= {R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo};
    String City ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_address);

        sessionManager = new SessionManager(AddAddressActivity.this);

        CountryList.clear();
        CityList.clear();

        binding.RRBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        binding.checkHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.checkWork.setChecked(false);
                    binding.checkCamus.setChecked(false);
                    AddressType ="H";
                }
            }
        });

        binding.checkWork.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.checkHome.setChecked(false);
                    binding.checkCamus.setChecked(false);

                    AddressType ="W";
                }
            }
        });

        binding.checkCamus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.checkWork.setChecked(false);
                    binding.checkHome.setChecked(false);
                    AddressType ="C";
                }
            }
        });

        binding.btSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

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
                    Toast.makeText(AddAddressActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(AddAddressActivity.this, CountryId, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        binding.spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3){

                City = CountryList.get(pos).getCountryID();

                Toast.makeText(AddAddressActivity.this, City, Toast.LENGTH_SHORT).show();

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

        String Location = binding.edtLocation.getText().toString();
        String Address = binding.edtAddress.getText().toString();
        String AddressDirection = binding.edtAddressDirecton.getText().toString();

        String OtherAddress = binding.edtAddressDirecton.getText().toString();

        if(Address.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter Address", Toast.LENGTH_SHORT).show();

        }else if(AddressDirection.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter AddressDirection", Toast.LENGTH_SHORT).show();

        }else if(OtherAddress.equalsIgnoreCase("")) {

            Toast.makeText(this, "Please Enter OtherAddress", Toast.LENGTH_SHORT).show();

        }else {

            if (sessionManager.isNetworkAvailable()) {

                binding.progressBar.setVisibility(View.VISIBLE);

                addAddress(Address,AddressDirection,OtherAddress);

            }else {
                Toast.makeText(AddAddressActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void addAddress(String address, String addressDirection, String otherAddress) {

        String user_id = Preference.get(AddAddressActivity.this, Preference.KEY_USER_ID);

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .add_delivery_address(user_id,City,address,addressDirection,AddressType,otherAddress);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString ("status");

                    if (status.equalsIgnoreCase("1")){

                        Intent intent =new Intent(AddAddressActivity.this, DeliveryAddress.class);
                        intent.putExtra("Type","Payment");
                        startActivity(intent);
                        finish();
                        Toast.makeText(AddAddressActivity.this, "SuccessFully Save.", Toast.LENGTH_SHORT).show();
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
                        CountryInSpinnerAdapter customAdapter=new CountryInSpinnerAdapter(AddAddressActivity.this,flags,CountryList);
                        binding.spinnerCountry.setAdapter(customAdapter);


                    }else {
                        Toast.makeText(AddAddressActivity.this, result, Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CountryNameModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(AddAddressActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
                        CitySpinnerAdapter customAdapter=new CitySpinnerAdapter(AddAddressActivity.this,flags,CityList);
                        binding.spinnerCity.setAdapter(customAdapter);


                    }else {
                        Toast.makeText(AddAddressActivity.this, result, Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetCityModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(AddAddressActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}