package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.adapter.MyAdapter;
import com.laapesca.neyiyelimapp.databinding.ActivityPressMediaBinding;

public class PressMediaActivity extends AppCompatActivity {

    private ActivityPressMediaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_press_media);
        // setContentView(R.layout.activity_tell_a_friend);

        binding.layPromo.tvHeader.setText("Press & Media");
        binding.layPromo.imgHeader.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });



        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Visual Media"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Print Media"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Social Media"));

        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        binding.viewPager.setAdapter(new MyAdapter(this, getSupportFragmentManager(), binding.tabLayout.getTabCount()));
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }
}