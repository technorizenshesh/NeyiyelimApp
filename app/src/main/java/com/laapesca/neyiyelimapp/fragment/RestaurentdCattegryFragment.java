package com.laapesca.neyiyelimapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laapesca.neyiyelimapp.Model.Branch;
import com.laapesca.neyiyelimapp.Model.BranchModel;
import com.laapesca.neyiyelimapp.Model.RestaurentModel;
import com.laapesca.neyiyelimapp.Model.RestaurentModelData;
import com.laapesca.neyiyelimapp.Model.RestaurentModelDataCat;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.adapter.ResataurentCategoryItemAdapter;
import com.laapesca.neyiyelimapp.listner.FragmentListener;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurentdCattegryFragment extends Fragment {

    View view;

    private ProgressBar progressBar;
    Fragment fragment;
    private ArrayList<BranchModel> modellist = new ArrayList<BranchModel>();
    ResataurentCategoryItemAdapter mAdapterListRestaurent;
    RecyclerView recycle_itemCat;

    private SessionManager sessionManager;
    private TextView txt_emty;
    private RelativeLayout RR_back;

    public RestaurentdCattegryFragment() {
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
        view = inflater.inflate(R.layout.fragment_category, container, false);

        sessionManager = new SessionManager(getActivity());
        recycle_itemCat =view.findViewById(R.id.recycle_itemCat);
        progressBar =view.findViewById(R.id.progressBar);
        txt_emty =view.findViewById(R.id.txt_emty);
        RR_back =view.findViewById(R.id.RR_back);

        RR_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        if (sessionManager.isNetworkAvailable()) {

            progressBar.setVisibility(View.VISIBLE);

            getRestaurentMethod();

        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void setAdapter(ArrayList<BranchModel> modellist) {

        mAdapterListRestaurent = new ResataurentCategoryItemAdapter(getActivity(), this.modellist,RestaurentdCattegryFragment.this);
        recycle_itemCat.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycle_itemCat.setLayoutManager(linearLayoutManager);
        recycle_itemCat.setAdapter(mAdapterListRestaurent);

        mAdapterListRestaurent.SetOnItemClickListener(new ResataurentCategoryItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, BranchModel model) {

                Preference.save( getActivity(),Preference.KEY_MEnu_ID,model.getMenuID());

              fragment = new RestaurentdCattegryFragmentOne();
                loadFragment(fragment);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("home").replace(R.id.mainContent, fragment).commit();
        }
    }

    public void getRestaurentMethod() {

     //  String branchId = Preference.get( getActivity(), Preference.KEY_BRANCH_ID);
        String branchId = "1179";

        Call<Branch> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_menu(branchId);
        call.enqueue(new Callback<Branch>() {
            @Override
            public void onResponse(Call<Branch> call, Response<Branch> response) {
                try {

                    progressBar.setVisibility(View.GONE);

                    Branch myclass= response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")){

                        txt_emty.setVisibility(View.GONE);

                        modellist= (ArrayList<BranchModel>) myclass .getResult();

                        setAdapter(modellist);

                    }else {
                        txt_emty.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    txt_emty.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Branch> call, Throwable t) {
                  progressBar.setVisibility(View.GONE);
                txt_emty.setVisibility(View.VISIBLE);
            }
        });
    }

}