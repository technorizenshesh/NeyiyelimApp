package com.laapesca.neyiyelimapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.laapesca.neyiyelimapp.Model.CountryNameModel;
import com.laapesca.neyiyelimapp.Model.CountryNameModelData;
import com.laapesca.neyiyelimapp.Model.GetCityModel;
import com.laapesca.neyiyelimapp.Model.GetCityModelData;
import com.laapesca.neyiyelimapp.Model.RegionModel;
import com.laapesca.neyiyelimapp.Model.RegionModelData;
import com.laapesca.neyiyelimapp.Model.RestaurentModel;
import com.laapesca.neyiyelimapp.Model.RestaurentModelData;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.adapter.CitySpinnerAdapter;
import com.laapesca.neyiyelimapp.adapter.CountryInSpinnerAdapter;
import com.laapesca.neyiyelimapp.adapter.CountrySpinnerAdapter;
import com.laapesca.neyiyelimapp.adapter.RegionSpinnerAdapter;
import com.laapesca.neyiyelimapp.databinding.ActivitySignUpBinding;
import com.laapesca.neyiyelimapp.utils.Preference;
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

public class SignUpActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    ActivitySignUpBinding binding;
    String message="";
    private SessionManager sessionManager;

    private String code[] ={"1","2","3","4","5","6","7","8","9","10"};
    private int flags[]= {R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo};
    private ArrayList<CountryNameModelData> CountryList = new ArrayList<CountryNameModelData>();
    private ArrayList<GetCityModelData> CityList = new ArrayList<GetCityModelData>();
    private ArrayList<RegionModelData> RegionList = new ArrayList<RegionModelData>();

    String CountryId ="";
    String CityId ="";
    String RegionId ="";
    String AddressType = "";

    // Google SignIn
    private RelativeLayout RR_faceBook_login;
    private RelativeLayout RR_google_login;
    private SignInButton signInButton;
    FirebaseAuth mAuth;
    private final static int RC_SIGN_IN = 1;
    private GoogleApiClient googleApiClient;
    String token="";
    private static final String TAG = "fireBaseToken";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);

        init();
        //Google SignIn
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //FirebaseToke
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        token = task.getResult();
                        Log.e("token",token);
                    }
                });


    }

    private void init() {

        sessionManager = new SessionManager(SignUpActivity.this);

        binding.imgGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, RC_SIGN_IN);

            }
        });

        binding.LLCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, "jjkb", Toast.LENGTH_SHORT).show();

            }
        });

        binding.btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Validation();

            }
        });

        binding.spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3){

                CountryId = CountryList.get(pos).getCountryID();

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

        binding.spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3){

                CityId = CityList.get(pos).getCityID();

                if (sessionManager.isNetworkAvailable()) {

                    binding.progressBar.setVisibility(View.VISIBLE);

                    get_region(CityId);

                }else {
                    Toast.makeText(SignUpActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(SignUpActivity.this, CityId, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        binding.spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3){

                RegionId = RegionList.get(pos).getRegionID();

                Toast.makeText(SignUpActivity.this, RegionId, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

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


        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(View.VISIBLE);

            getCountry();

        }else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }


    }

    //Google Login
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent( data );
            handleSignInResult( result );
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            String UsernAME=account.getDisplayName();
            String email=account.getEmail();
            String SocialId=account.getId();
            Uri Url=account.getPhotoUrl();

            Toast.makeText(this, ""+UsernAME, Toast.LENGTH_SHORT).show();
         /*   Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();*/

            if (sessionManager.isNetworkAvailable()) {

                binding.progressBar.setVisibility(View.VISIBLE);

                SocialLoginMethod(email,UsernAME,"88888888888",SocialId);

            }else {

                Toast.makeText(SignUpActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }

        } else {

            Toast.makeText( this, "Login Unsuccessful", Toast.LENGTH_SHORT ).show();

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void Validation() {

        String UserName = binding.edtUserName.getText().toString();
        String Email = binding.edtEmail.getText().toString();
        String Mobile = binding.edtMobile.getText().toString();
        String Pasword = binding.edtPassword.getText().toString();
        String AdressDirection = binding.edtAddressDirection.getText().toString();

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

                signUpMethod(UserName,Email,Mobile,Pasword,AdressDirection);

            }else {
                Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }


        }

    }

    private void signUpMethod(String userName, String email, String mobile, String pasword, String AdressDirection){

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .signup(userName,email,pasword,mobile,token,"75.2325","75.2325",CountryId,CityId,RegionId,RegionId,
                        AddressType,AdressDirection);
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
                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
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

    public void get_region(String CItyId) {

        Call<RegionModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_region(CItyId);
        call.enqueue(new Callback<RegionModel>() {
            @Override
            public void onResponse(Call<RegionModel> call, Response<RegionModel> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    RegionModel myclass= response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")){

                        RegionList= (ArrayList<RegionModelData>) myclass.getResult();
                        RegionSpinnerAdapter regionAdapter=new RegionSpinnerAdapter(SignUpActivity.this,flags,RegionList);
                        binding.spinnerRegion.setAdapter(regionAdapter);


                    }else {
                        Toast.makeText(SignUpActivity.this, result, Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RegionModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SocialLoginMethod(String email,String UserName,String Mobile,String SocialId){

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .social_login(email,UserName,Mobile,"123456","bjkbbbjb",SocialId,"75.2325","75.2325");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    binding.btSignUp.setEnabled(true);
                    binding.progressBar.setVisibility(View.GONE);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString ("status");
                    message = jsonObject.getString ("message");

                    JSONObject resultOne = jsonObject.getJSONObject("result");
                    String UserId = resultOne.getString("customerID");

                    if (status.equalsIgnoreCase("1")) {

                        Preference.save( SignUpActivity.this, Preference.KEY_USER_ID,UserId);

                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(SignUpActivity.this, HomeScreenActivity.class));

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


}