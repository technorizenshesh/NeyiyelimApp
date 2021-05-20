package com.laapesca.neyiyelimapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.laapesca.neyiyelimapp.Model.BannerMoodel;
import com.laapesca.neyiyelimapp.Model.BannerMoodelData;
import com.laapesca.neyiyelimapp.Model.CategoryModel;
import com.laapesca.neyiyelimapp.Model.CategoryModelData;
import com.laapesca.neyiyelimapp.Model.RestaurentModel;
import com.laapesca.neyiyelimapp.Model.RestaurentModelData;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.adapter.CategoryRecyleviewAdapter;
import com.laapesca.neyiyelimapp.adapter.LatestAdapter;
import com.laapesca.neyiyelimapp.adapter.ViewPagerAdapter;
import com.laapesca.neyiyelimapp.databinding.FragmentHomeBinding;
import com.laapesca.neyiyelimapp.databinding.FragmentSearchBinding;
import com.laapesca.neyiyelimapp.listner.FragmentListener;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFraagmentFragment extends Fragment {

    private FragmentSearchBinding binding;

    private ArrayList<RestaurentModelData> modellist = new ArrayList<RestaurentModelData>();

    LatestAdapter mAdapterListRestaurent;
    Boolean loading = true;
    int pastVisibleItems, visibleItemCount, totalItemCount;
    LinearLayoutManager linearLayoutManager;
    int page = 1;

    private SessionManager sessionManager;
    Fragment fragment;

    public SearchFraagmentFragment() { ;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        //  root = inflater.inflate(R.layout.fragment_home, container, false);
        sessionManager = new SessionManager(getActivity());

        binding.RRBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        linearLayoutManager = new LinearLayoutManager(getActivity());

        binding.btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
                if (!loading) {
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        loading = true;
                        page = page+1;

                        Toast.makeText(getActivity(), ""+page, Toast.LENGTH_SHORT).show();

                        if (sessionManager.isNetworkAvailable()) {

                            binding.progressBar.setVisibility(View.VISIBLE);

                            getRestaurentMethod(String.valueOf(page));

                        }else {
                            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                // Toast.makeText( HomeProductListSearch.this, "", Toast.LENGTH_SHORT ).show();

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                // Toast.makeText( HomeProductListSearch.this, ""+charSequence, Toast.LENGTH_SHORT ).show();
                if(charSequence.length()<=0)
                {
                    binding.reSearch.setVisibility( View.INVISIBLE );

                }else
                {
                    binding.reSearch.setVisibility( View.VISIBLE );
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());
            }
        });

        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(View.VISIBLE);

            getRestaurentMethod(String.valueOf(page));

        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }


        return binding.getRoot();
    }

    void filter(String text) {

        List<RestaurentModelData> temp = new ArrayList();
        for (RestaurentModelData d : modellist) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.getMinDeliveryTime().toLowerCase().contains(text.toString().toLowerCase())) {
                temp.add(d);

            }
            //update recyclerview
            mAdapterListRestaurent.updateList((ArrayList<RestaurentModelData>) temp);

        }
    }

    private void SetuUI(ArrayList<RestaurentModelData> modellist) {

        mAdapterListRestaurent = new LatestAdapter(getActivity(),modellist);
        binding.reSearch.setHasFixedSize(true);
        // use a linear layout manager

        binding.reSearch.setLayoutManager(linearLayoutManager);
        binding.reSearch.setAdapter(mAdapterListRestaurent);

        mAdapterListRestaurent.SetOnItemClickListener(new LatestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, RestaurentModelData model) {

                Preference.save( getActivity(),Preference.KEY_BRANCH_ID,model.getBranchID());

                Preference.save( getActivity(),Preference.KEY_RestaurentName,model.getBranchID());
                Preference.save( getActivity(),Preference.KEY_RestaurentName_img,model.getBranchID());

                fragment = new RestaurentdCattegryFragment();
                loadFragment(fragment);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }



    public void getRestaurentMethod(String page) {

        Call<RestaurentModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_all_restaurant(page);
        call.enqueue(new Callback<RestaurentModel>() {
            @Override
            public void onResponse(Call<RestaurentModel> call, Response<RestaurentModel> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    RestaurentModel myclass= response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")){

                        modellist = (ArrayList<RestaurentModelData>) myclass.getResult();
                        loading =false;
                        SetuUI(modellist);

                    }else {
                        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RestaurentModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("home").replace(R.id.mainContent, fragment).commit();
        }
    }
}