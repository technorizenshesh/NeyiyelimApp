package com.laapesca.neyiyelimapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.laapesca.neyiyelimapp.Model.CountryNameModelData;
import com.laapesca.neyiyelimapp.Model.RestaurentModelData;
import com.laapesca.neyiyelimapp.R;

import java.util.ArrayList;

public class CountryInSpinnerAdapter extends BaseAdapter {

    Context context;
    int flags[];
    String code[];
    String[] countryNames;
    LayoutInflater inflter;
    TextView countrycode;
    ImageView icon;
    private ArrayList<CountryNameModelData> modelList;

    public CountryInSpinnerAdapter(Context applicationContext, int[] flags, ArrayList<CountryNameModelData> modelList) {
        this.context = applicationContext;
        this.flags = flags;
        this.modelList = modelList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return modelList.size();
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
        view = inflter.inflate(R.layout.spinner_layout_one, null);
        //icon = (ImageView) view.findViewById(R.id.img_flag);
       countrycode = (TextView) view.findViewById(R.id.textview);
        // icon.setImageResource(flags[i]);
      countrycode.setText(modelList.get(i).getCountryName());
        return view;

    }
}

