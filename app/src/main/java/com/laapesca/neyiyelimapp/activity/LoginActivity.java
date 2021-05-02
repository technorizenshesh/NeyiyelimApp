package com.laapesca.neyiyelimapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.databinding.ActivityLoginBinding;
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

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    ActivityLoginBinding binding;
    String message="";
    private SessionManager sessionManager;

    // Google SignIn
    private RelativeLayout RR_faceBook_login;
    private RelativeLayout RR_google_login;
    private SignInButton signInButton;
    FirebaseAuth mAuth;
    private final static int RC_SIGN_IN = 1;
    private GoogleApiClient googleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

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

        binding.imgGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, RC_SIGN_IN);
            }
        });

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

                Toast.makeText(LoginActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }

        } else {

            Toast.makeText( this, "Login Unsuccessful", Toast.LENGTH_SHORT ).show();

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void init() {

        sessionManager = new SessionManager(LoginActivity.this);

        binding.txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        binding.tvForogtPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
            }
        });

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Validation();

            }
        });
    }

    private void Validation() {

        String Email=binding.edtEmail.getText().toString();
        String Password=binding.edtPassword.getText().toString();

        if(Email.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();

        }else if(Password.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();

        }else {

            if (sessionManager.isNetworkAvailable()) {

                binding.progressBar.setVisibility(View.VISIBLE);

                loginMethod(Email,Password);

            }else {
                Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void loginMethod(String email,String pasword){

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .login(email,pasword,"bjkbbbjb","75.2325","75.2325");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    binding.btLogin.setEnabled(true);
                    binding.progressBar.setVisibility(View.GONE);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString ("status");
                    message = jsonObject.getString ("message");

                    JSONObject resultOne = jsonObject.getJSONObject("result");
                    String UserId = resultOne.getString("customerID");

                    if (status.equalsIgnoreCase("1")) {

                     Preference.save( LoginActivity.this, Preference.KEY_USER_ID,UserId);

                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(LoginActivity.this, HomeScreenActivity.class));

                    } else {
                        binding.btLogin.setEnabled(true);
                    }
                } catch (JSONException e) {
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.btLogin.setEnabled(true);
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

                    binding.btLogin.setEnabled(true);
                    binding.progressBar.setVisibility(View.GONE);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString ("status");
                    message = jsonObject.getString ("message");

                    JSONObject resultOne = jsonObject.getJSONObject("result");
                    String UserId = resultOne.getString("customerID");

                    if (status.equalsIgnoreCase("1")) {

                        Preference.save( LoginActivity.this, Preference.KEY_USER_ID,UserId);

                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(LoginActivity.this, HomeScreenActivity.class));

                    } else {
                        binding.btLogin.setEnabled(true);
                    }
                } catch (JSONException e) {
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.btLogin.setEnabled(true);
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}