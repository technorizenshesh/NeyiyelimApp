package com.laapesca.neyiyelimapp.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.activity.HomeScreenActivity;
import com.laapesca.neyiyelimapp.databinding.FragmentCartDetailBinding;
import com.laapesca.neyiyelimapp.listner.FragmentListener;


public class CartDetailFragment extends Fragment {


    private FragmentCartDetailBinding binding;

    FragmentListener listener;

    public CartDetailFragment(FragmentListener listener) {
        this.listener = listener;
    }

    public CartDetailFragment() {
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
        binding= FragmentCartDetailBinding.inflate(inflater, container, false);



        binding.edit.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_editcart);


            Button dialogButton = (Button) dialog.findViewById(R.id.addToCart);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    AddCartDailog();
                }
            });

            dialog.show();

        });





        return binding.getRoot();

    }


    public void AddCartDailog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_addtocart);


        Button dialogButton = (Button) dialog.findViewById(R.id.addToCart);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                AddToCartDailog();
            }
        });

        dialog.show();
    }


    public void AddToCartDailog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_addtocart2);


        Button dialogButton = (Button) dialog.findViewById(R.id.addToCart);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.click(new PlaceAnOrderFragment(listener));
                dialog.dismiss();

            }
        });

        dialog.show();
    }


    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = ((HomeScreenActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Cart Detail");
    }
}