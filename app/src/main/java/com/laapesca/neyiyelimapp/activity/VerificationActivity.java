package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.databinding.ActivityVerificationBinding;

public class VerificationActivity extends AppCompatActivity {

    ActivityVerificationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_verification);

        init();
    }

    private void init() {

        binding.RRBack.setOnClickListener(v -> {

            finish();

        });


        binding.et1.setBackgroundResource(R.drawable.oval_orange);

        binding.et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s != null && s.length() != 0) {
                    binding.et2.requestFocus();
                    binding.et2.setBackgroundResource(R.drawable.oval_orange);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s != null && s.length() != 0) {
                    binding.et3.requestFocus();
                    binding.et3.setBackgroundResource(R.drawable.oval_orange);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s != null && s.length() != 0) {
                    binding.et4.requestFocus();
                    binding.et4.setBackgroundResource(R.drawable.oval_orange);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.btConfirm.setOnClickListener(v -> {

          //  AlertDaliog();

            //startActivity(new Intent(mContext,LoginActivity.class));
        });

    }



}