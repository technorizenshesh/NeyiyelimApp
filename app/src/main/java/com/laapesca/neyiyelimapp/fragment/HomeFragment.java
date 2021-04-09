package com.laapesca.neyiyelimapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laapesca.neyiyelimapp.activity.HomeScreenActivity;
import com.laapesca.neyiyelimapp.adapter.LatestAdapter;
import com.laapesca.neyiyelimapp.databinding.FragmentHomeBinding;
import com.laapesca.neyiyelimapp.listner.FragmentListener;

public class HomeFragment extends Fragment {

    private View root;
    private RecyclerView re;
    private FragmentHomeBinding binding;

    FragmentListener listener;

    public HomeFragment(FragmentListener listener) {
        this.listener = listener;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        //  root = inflater.inflate(R.layout.fragment_home, container, false);

        SetuUI();
        return binding.getRoot();
    }

    private void SetuUI() {

        final LatestAdapter adapter = new LatestAdapter(getContext());
        binding.re.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.re.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = ((HomeScreenActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Home");
    }
}