package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.databinding.ActivityMyTatsBinding;

public class MyTatsActivity extends AppCompatActivity {

    private ActivityMyTatsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_my_tats);
        //  setContentView(R.layout.activity_promotions);

        binding.layPromo.tvHeader.setText("My Tats");
        binding.layPromo.imgHeader.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });
    }
}