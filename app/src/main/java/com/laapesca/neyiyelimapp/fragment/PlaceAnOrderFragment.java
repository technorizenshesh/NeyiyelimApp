package com.laapesca.neyiyelimapp.fragment;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laapesca.neyiyelimapp.activity.HomeScreenActivity;
import com.laapesca.neyiyelimapp.databinding.FragmentPlaceAnOrderBinding;
import com.laapesca.neyiyelimapp.listner.FragmentListener;


public class PlaceAnOrderFragment extends Fragment {

    private FragmentPlaceAnOrderBinding binding;

    FragmentListener listener;

    public PlaceAnOrderFragment(FragmentListener listener) {
        this.listener = listener;
    }

    public PlaceAnOrderFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding= FragmentPlaceAnOrderBinding.inflate(inflater,container,false);


        binding.payu.setOnClickListener(v -> {
            listener.click(new PaymentFragment(listener));
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        ActionBar actionBar = ((HomeScreenActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Place An Order");
    }
}