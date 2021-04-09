package com.laapesca.neyiyelimapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laapesca.neyiyelimapp.databinding.FragmentPaymentBinding;
import com.laapesca.neyiyelimapp.fragment.InformationFragment;
import com.laapesca.neyiyelimapp.listner.FragmentListener;


public class PaymentFragment extends Fragment {


    private FragmentPaymentBinding binding;
    FragmentListener listener;

    public PaymentFragment() {
        // Required empty public constructor
    }

    public PaymentFragment(FragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(inflater, container, false);

        binding.checku.setOnClickListener(v -> {
            listener.click(new InformationFragment(listener));
        });
        return binding.getRoot();


    }
}