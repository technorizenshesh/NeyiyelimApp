package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.databinding.ActivityHelpBinding;

public class HelpActivity extends AppCompatActivity {

    private ActivityHelpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_help);

        binding.layPromo.tvHeader.setText("Help");
        binding.layPromo.imgHeader.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });
    }
}