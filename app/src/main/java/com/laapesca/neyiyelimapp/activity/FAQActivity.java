package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.databinding.ActivityFAQBinding;

public class FAQActivity extends AppCompatActivity {

    private ActivityFAQBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_f_a_q);

        binding.layPromo.tvHeader.setText("FAQ");
        binding.layPromo.imgHeader.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

    }
}