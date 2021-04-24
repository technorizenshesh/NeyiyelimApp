package com.laapesca.neyiyelimapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.laapesca.neyiyelimapp.Model.AllImageModelData;
import com.laapesca.neyiyelimapp.Model.BannerMoodel;
import com.laapesca.neyiyelimapp.Model.BannerMoodelData;
import com.laapesca.neyiyelimapp.Model.CategoryModel;
import com.laapesca.neyiyelimapp.Model.CategoryModelData;
import com.laapesca.neyiyelimapp.Model.RestaurentModel;
import com.laapesca.neyiyelimapp.Model.RestaurentModelData;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.activity.HomeScreenActivity;
import com.laapesca.neyiyelimapp.activity.LoginActivity;
import com.laapesca.neyiyelimapp.adapter.CategoryRecyleviewAdapter;
import com.laapesca.neyiyelimapp.adapter.LatestAdapter;
import com.laapesca.neyiyelimapp.adapter.ViewPagerAdapter;
import com.laapesca.neyiyelimapp.databinding.FragmentHomeBinding;
import com.laapesca.neyiyelimapp.listner.FragmentListener;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private View root;
    private RecyclerView re;
    private FragmentHomeBinding binding;

    private int dotscount;
    private ImageView[] dots;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500; //delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;

    FragmentListener listener;
    private ArrayList<RestaurentModelData> modellist = new ArrayList<RestaurentModelData>();
    private ArrayList<CategoryModelData> modelListCategory = new ArrayList<CategoryModelData>();
    private ArrayList<BannerMoodelData> MobelestBanner = new ArrayList<BannerMoodelData>();

    CategoryRecyleviewAdapter mAdapter;
    LatestAdapter mAdapterListRestaurent;
    private SessionManager sessionManager;
    Fragment fragment;

    public HomeFragment(FragmentListener listener) {
        this.listener = listener;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        //  root = inflater.inflate(R.layout.fragment_home, container, false);
        sessionManager = new SessionManager(getActivity());


        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(View.VISIBLE);

            getRestaurentMethod();

           // getCategoryMethod();
            //getBanneer();

        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }

        binding.searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new SearchFraagmentFragment();
                loadFragment(fragment);
            }
        });

        MobelestBanner.clear();
        MobelestBanner.add(new BannerMoodelData(R.drawable.img));
        MobelestBanner.add(new BannerMoodelData(R.drawable.img));
        MobelestBanner.add(new BannerMoodelData(R.drawable.img));
        MobelestBanner.add(new BannerMoodelData(R.drawable.img));
        MobelestBanner.add(new BannerMoodelData(R.drawable.img));

        viewPagerSlider(MobelestBanner);

        return binding.getRoot();
    }

    private void SetuUI(ArrayList<RestaurentModelData> modellist) {

        mAdapterListRestaurent = new LatestAdapter(getActivity(),modellist);
        binding.re.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.re.setLayoutManager(linearLayoutManager);
        binding.re.setAdapter(mAdapterListRestaurent);

        mAdapterListRestaurent.SetOnItemClickListener(new LatestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, RestaurentModelData model) {

                Preference.save( getActivity(),Preference.KEY_BRANCH_ID,model.getBranchID());

                Preference.save( getActivity(),Preference.KEY_RestaurentName,model.getRestaurantName());
                Preference.save( getActivity(),Preference.KEY_RestaurentName_img,model.getRestaurantLogo());

                fragment = new RestaurentdCattegryFragment();
                loadFragment(fragment);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
      //  ActionBar actionBar = ((HomeScreenActivity) getActivity()).getSupportActionBar();
       // actionBar.setTitle("Home");
    }

    private void viewPagerSlider(ArrayList<BannerMoodelData> mobelestBanner) {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity(), mobelestBanner);
        binding.viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            binding.SliderDots.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_active_dot));

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    try {
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_active_dot));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == mobelestBanner.size() - 1) {
                    currentPage = 0;
                }
                try {
                    binding.viewPager.setCurrentItem(currentPage++, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    private void setAdapter(ArrayList<CategoryModelData> modelListCategory) {

        mAdapter = new CategoryRecyleviewAdapter(getActivity(),modelListCategory,HomeFragment.this);
        binding.categoryRecycleview.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.categoryRecycleview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        binding.categoryRecycleview.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new CategoryRecyleviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, CategoryModelData model) {

            }
        });
    }

    public void getCategoryMethod() {

        Call<CategoryModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_category();

        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    CategoryModel myclass= response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")){



                        modelListCategory = (ArrayList<CategoryModelData>) myclass.getResult();

                        //getRestaurentMethod(modelListCategory.get(0).getId());

                        setAdapter(modelListCategory);

                    }else {
                        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getBanneer() {

        Call<BannerMoodel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_banner();

        call.enqueue(new Callback<BannerMoodel>() {
            @Override
            public void onResponse(Call<BannerMoodel> call, Response<BannerMoodel> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    BannerMoodel myclass= response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")){

                        MobelestBanner = (ArrayList<BannerMoodelData>) myclass.getResult();

                        viewPagerSlider(MobelestBanner);

                    }else {
                        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BannerMoodel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getRestaurentMethod() {

        Call<RestaurentModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_all_restaurant();
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