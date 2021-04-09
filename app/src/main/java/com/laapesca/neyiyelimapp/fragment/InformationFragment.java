package com.laapesca.neyiyelimapp.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.fragment.HomeFragment;
import com.laapesca.neyiyelimapp.databinding.FragmentInformationBinding;
import com.laapesca.neyiyelimapp.listner.FragmentListener;


public class InformationFragment extends Fragment {


    private FragmentInformationBinding binding;

    public InformationFragment() {
        // Required empty public constructor
    }
    FragmentListener listener;

    public InformationFragment(FragmentListener listener) {
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
        binding = FragmentInformationBinding.inflate(inflater, container, false);

        binding.trackOnline.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_trackonline);


            Button dialogButton = (Button) dialog.findViewById(R.id.track_onlinee);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    listener.click(new HomeFragment(listener));

                }
            });

            dialog.show();
        });

        return binding.getRoot();
    }
}