package com.laapesca.neyiyelimapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.laapesca.neyiyelimapp.Model.MyOrder;
import com.laapesca.neyiyelimapp.Model.MyProfileModel;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.activity.AboutActivity;
import com.laapesca.neyiyelimapp.activity.ChangePassword;
import com.laapesca.neyiyelimapp.activity.DeliveryAddress;
import com.laapesca.neyiyelimapp.activity.EditProfile;
import com.laapesca.neyiyelimapp.activity.LoginActivity;
import com.laapesca.neyiyelimapp.activity.MyOrderActivity;
import com.laapesca.neyiyelimapp.activity.ProfileActivity;
import com.laapesca.neyiyelimapp.listner.FragmentListener;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {

    View view;
    RelativeLayout ll_profile;
    RelativeLayout RR_aboout;
    RelativeLayout RR_myOrder;
    RelativeLayout RR_changePassword;
    RelativeLayout RR_logout;
    RelativeLayout RR_delivery_address;

    public AccountFragment() {
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
        view = inflater.inflate(R.layout.accountdetails_fragment, container, false);

        ll_profile =view.findViewById(R.id.ll_profile);
        RR_changePassword =view.findViewById(R.id.RR_changePassword);
        RR_logout =view.findViewById(R.id.RR_logout);
        RR_delivery_address =view.findViewById(R.id.RR_delivery_address);
        RR_aboout =view.findViewById(R.id.RR_aboout);
        RR_myOrder =view.findViewById(R.id.RR_myOrder);

        ll_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ProfileActivity.class));

            }
        });

        RR_myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), MyOrderActivity.class));

            }
        });
        RR_aboout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), AboutActivity.class));

            }
        });

        RR_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ChangePassword.class));

            }
        });

        RR_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), LoginActivity.class));

            }
        });

        RR_delivery_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getActivity(), DeliveryAddress.class);
                intent.putExtra("Type","Payment");
                startActivity(intent);
            }
        });


        return view;
    }


}