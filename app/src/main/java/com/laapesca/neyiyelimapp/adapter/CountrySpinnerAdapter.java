package com.laapesca.neyiyelimapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.laapesca.neyiyelimapp.R;

public class CountrySpinnerAdapter extends BaseAdapter {

    Context context;
    int flags[];
    String code[];
    String[] countryNames;
    LayoutInflater inflter;
    TextView countrycode;
    ImageView icon;

    public CountrySpinnerAdapter(Context applicationContext, int[] flags, String[] code) {
        this.context = applicationContext;
        this.flags = flags;
        this.code = code;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return code.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_layout, null);
        //icon = (ImageView) view.findViewById(R.id.img_flag);
       countrycode = (TextView) view.findViewById(R.id.textview);
        // icon.setImageResource(flags[i]);
      countrycode.setText(code[i]);
        return view;

    }
}

