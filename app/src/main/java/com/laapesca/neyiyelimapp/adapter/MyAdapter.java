package com.laapesca.neyiyelimapp.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.laapesca.neyiyelimapp.fragment.PrintMediaFragment;
import com.laapesca.neyiyelimapp.fragment.SocialMediaFragment;
import com.laapesca.neyiyelimapp.fragment.VisualMediaFragment;


public class MyAdapter extends FragmentStatePagerAdapter {


    Context context;
    int totalTabs;

    public MyAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                VisualMediaFragment tab1 = new VisualMediaFragment();
                return tab1;
            case 1:
                PrintMediaFragment tab2 = new PrintMediaFragment();
                return tab2;

            case 2:
                SocialMediaFragment tab3 = new SocialMediaFragment();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}



