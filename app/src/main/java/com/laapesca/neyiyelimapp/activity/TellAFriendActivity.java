package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.graphics.Color;
import android.os.Bundle;

import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.adapter.SliderAdapter;
import com.laapesca.neyiyelimapp.databinding.ActivityTellAFriendBinding;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class TellAFriendActivity extends AppCompatActivity {

    private ArrayList<SliderItem> sliderItems;
    private SliderAdapter adapter;
    private ActivityTellAFriendBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_tell_a_friend);
       // setContentView(R.layout.activity_tell_a_friend);

        binding.layPromo.tvHeader.setText("Tell a Friend");
        binding.layPromo.imgHeader.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });
        sliderItems=new ArrayList<>();

        sliderItems.add(new SliderItem("1","Test","https://drive.google.com/file/d/1duWIfQHQm2lUmxaKk4lzPkGlRGya5UNL/view?usp=sharing","true"));

        adapter = new SliderAdapter(this, sliderItems);

        binding.imageSlider.setSliderAdapter(adapter);
        adapter.notifyDataSetChanged();
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        binding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        binding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        binding.imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        binding.imageSlider.startAutoCycle();

    }
}