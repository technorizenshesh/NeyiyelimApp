package com.laapesca.neyiyelimapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.android.material.button.MaterialButton;
import com.laapesca.neyiyelimapp.Model.MyProfileModel;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.activity.ChangePassword;
import com.laapesca.neyiyelimapp.activity.EditProfile;
import com.laapesca.neyiyelimapp.activity.HomeScreenActivity;
import com.laapesca.neyiyelimapp.activity.LoginActivity;
import com.laapesca.neyiyelimapp.listner.FragmentListener;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    View view;
    FragmentListener listener;
    private ProgressBar progressBar;
    private MaterialButton btn_Editprofile;
    private MaterialButton btnChangePassword;
    private MaterialButton btnLogout;
    private SessionManager sessionManager;

    TextView txxt_userName;
    TextView txxt_email;
    TextView txxt_mobile;
    TextView txxt_Country;


    public ProfileFragment(FragmentListener listener) {
        this.listener = listener;
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        txxt_userName= view.findViewById(R.id.txxt_userName);
        txxt_email=view.findViewById(R.id.txxt_email);
        txxt_mobile=view.findViewById(R.id.txxt_mobile);
        txxt_Country=view.findViewById(R.id.txxt_Country);
        progressBar=view.findViewById(R.id.progressBar);
        btn_Editprofile=view.findViewById(R.id.btn_Editprofile);
        btnChangePassword=view.findViewById(R.id.btnChangePassword);
        btnLogout=view.findViewById(R.id.btnLogout);
        sessionManager = new SessionManager(getActivity());

        btn_Editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfile.class));
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangePassword.class));
            }
        });

        if (sessionManager.isNetworkAvailable()) {

           progressBar.setVisibility(View.VISIBLE);

            callProfileApi();

        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }

        return view;
    }


    private void callProfileApi() {

     String UserId = Preference.get(getActivity(), Preference.KEY_USER_ID);

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

                        txxt_userName.setText(UserName);
                        txxt_email.setText(Email);
                        if(Mobile.equalsIgnoreCase(""))
                        {
                            txxt_mobile.setText("Null");

                        }else {

                            txxt_mobile.setText(Mobile);
                        }

                        //String Country =myLogin.getResult().getEmail().toString();

                    }else {

                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), myLogin.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<MyProfileModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}