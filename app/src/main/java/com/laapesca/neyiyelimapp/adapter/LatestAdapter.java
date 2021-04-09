package com.laapesca.neyiyelimapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.databinding.HomeFragmentAdapterBinding;

import org.jetbrains.annotations.NotNull;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.MyView> {

    private final Context context;

    public LatestAdapter(Context context) {
        this.context = context;
    }

    @NotNull
    @Override
    public MyView onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        HomeFragmentAdapterBinding homeBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.home_fragment_adapter, parent, false);
        return new MyView(homeBinding);
    }

    @Override
    public void onBindViewHolder(@NotNull final MyView holder, final int position) {


        holder.homeBinding.addCart.setOnClickListener(v -> {


        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyView extends RecyclerView.ViewHolder {
        HomeFragmentAdapterBinding homeBinding;

        public MyView(HomeFragmentAdapterBinding homeBinding) {
            super(homeBinding.getRoot());
            this.homeBinding = homeBinding;
        }
    }
}
