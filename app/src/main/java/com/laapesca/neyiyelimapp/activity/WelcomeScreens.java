package com.laapesca.neyiyelimapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.databinding.ActivityWelcomeScreensBinding;

public class WelcomeScreens extends AppCompatActivity {

    ActivityWelcomeScreensBinding binding;

    PreferenceManager preferenceManager;
    LinearLayout Layout_bars;
    TextView[] bottomBars;
    int[] screens;
    Button Skip, Next;
    ViewPager vp;
    MyViewPagerAdapter myvpAdapter;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome_screens);

        binding.RRSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                startActivity(new Intent(WelcomeScreens.this, LoginActivity.class));

            }
        });

        vp = (ViewPager) findViewById(R.id.view_pager);

        screens = new int[]{
                R.layout.intro_screen1,
                R.layout.intro_screen2,
                R.layout.intro_screen3,
        };
        myvpAdapter = new MyViewPagerAdapter();
        vp.setAdapter(myvpAdapter);
        binding.dotsIndicator.setViewPager(vp);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0)
                {
                    binding.dotsIndicator.setSelectedDotColor(getResources().getColor(R.color.white));
                    binding.dotsIndicator.setDotsColor(getResources().getColor(R.color.white));
                }else if(position==1)
                {
                    binding.dotsIndicator.setSelectedDotColor(getResources().getColor(R.color.red));
                    binding.dotsIndicator.setDotsColor(getResources().getColor(R.color.red));

                }else if(position==2)
                {
                    binding.dotsIndicator.setSelectedDotColor(getResources().getColor(R.color.white));
                    binding.dotsIndicator.setDotsColor(getResources().getColor(R.color.white));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void next(View v) {
        int i = getItem(+1);
        if (i < screens.length) {
            vp.setCurrentItem(i);
        } else {
            startActivity(new Intent(WelcomeScreens.this, LoginActivity.class));
            finish();
        }
    }


    private int getItem(int i) {
        return vp.getCurrentItem() + i;
    }


    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater inflater;


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(screens[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return screens.length;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }

        @Override
        public boolean isViewFromObject(View v, Object object) {
            return v == object;
        }
    }
}