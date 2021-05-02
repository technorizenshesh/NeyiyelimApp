package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.databinding.ActivityPromotionsBinding;

public class PromotionsActivity extends AppCompatActivity {

    private ActivityPromotionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_promotions);
      // setContentView(R.layout.activity_promotions);
        binding.layPromo.tvHeader.setText("Promotiones");

        binding.layPromo.imgHeader.setOnClickListener(v ->{
            onBackPressed();
            finish();
        });

    }
}