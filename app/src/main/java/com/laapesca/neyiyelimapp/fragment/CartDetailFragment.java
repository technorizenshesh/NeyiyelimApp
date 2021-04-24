package com.laapesca.neyiyelimapp.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.laapesca.neyiyelimapp.Model.GetCardItemModel;
import com.laapesca.neyiyelimapp.Model.GetCardOne;
import com.laapesca.neyiyelimapp.Model.GetCardTwo;
import com.laapesca.neyiyelimapp.Model.GetMenu;
import com.laapesca.neyiyelimapp.Model.GetMenuItem;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.activity.HomeScreenActivity;
import com.laapesca.neyiyelimapp.adapter.CardDetailAdapter;
import com.laapesca.neyiyelimapp.adapter.ResataurentCategoryItemAdapterOne;
import com.laapesca.neyiyelimapp.databinding.FragmentCartDetailBinding;
import com.laapesca.neyiyelimapp.listner.FragmentListener;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartDetailFragment extends Fragment {


    private FragmentCartDetailBinding binding;

    FragmentListener listener;

    private ArrayList<GetCardTwo> modellist = new ArrayList<GetCardTwo>();
    CardDetailAdapter mAdapterListRestaurent;
    private SessionManager sessionManager;
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

        sessionManager = new SessionManager(getActivity());

        binding.RRBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        binding.RRClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sessionManager.isNetworkAvailable()) {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    emptyMethod();

                }else {
                    Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
                }
            }
        });

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

        binding.btnMakeoffer.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_editcart);


            Button dialogButton = (Button) dialog.findViewById(R.id.addToCart);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                   // AddCartDailog();
                }
            });

            dialog.show();

        });

        if (sessionManager.isNetworkAvailable()) {

            binding.progressBar.setVisibility(View.VISIBLE);

            getCardMethod();

        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }





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
       // ActionBar actionBar = ((HomeScreenActivity)getActivity()).getSupportActionBar();
      //  actionBar.setTitle("Cart Detail");
    }

    private void setAdapter(ArrayList<GetCardTwo> modellist) {

        mAdapterListRestaurent = new CardDetailAdapter(getActivity(),modellist, CartDetailFragment.this);
        binding.recycleGetitemCat.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.recycleGetitemCat.setLayoutManager(linearLayoutManager);
        binding.recycleGetitemCat.setAdapter(mAdapterListRestaurent);

        mAdapterListRestaurent.SetOnItemClickListener(new CardDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, GetCardTwo model) {


            }
        });
    }

    public void getCardMethod() {

        String user_id = Preference.get( getActivity(), Preference.KEY_USER_ID);

        Call<GetCardOne> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_card_item(user_id);
        call.enqueue(new Callback<GetCardOne>() {
            @Override
            public void onResponse(Call<GetCardOne> call, Response<GetCardOne> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);


                    GetCardOne myclass= response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")){

                        binding.txtEmty.setVisibility(View.GONE);

                        binding.LLCard.setVisibility(View.VISIBLE);

                        modellist = (ArrayList<GetCardTwo>) myclass.getResult();

                        if(!modellist.isEmpty())
                        {
                            setAdapter(modellist);
                            binding.txtRestaurentName.setText(modellist.get(0).getRestaurantName().toString());

                            double TotaalPrice = myclass.getTotal();
                            binding.txtPrice.setText("Total: "+TotaalPrice);
                            binding.tcc0.setText(""+TotaalPrice);
                            binding.priceTotla.setText(""+TotaalPrice);

                        }

                    }else {
                        binding.LLCard.setVisibility(View.GONE);
                        binding.txtEmty.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    binding.LLCard.setVisibility(View.GONE);
                    binding.txtEmty.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetCardOne> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.LLCard.setVisibility(View.GONE);
                binding.txtEmty.setVisibility(View.VISIBLE);
            }
        });
    }

    public void emptyMethod() {

        String user_id = Preference.get( getActivity(), Preference.KEY_USER_ID);

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .empty_cart(user_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    binding.progressBar.setVisibility(View.GONE);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString ("status");
                    String result = jsonObject.getString ("result");

                    if (status.equalsIgnoreCase("1")){

                        binding.LLCard.setVisibility(View.GONE);
                        binding.txtEmty.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

                    }else {
                        binding.LLCard.setVisibility(View.GONE);
                        binding.txtEmty.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    binding.LLCard.setVisibility(View.GONE);
                    binding.txtEmty.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.LLCard.setVisibility(View.GONE);
                binding.txtEmty.setVisibility(View.VISIBLE);
            }
        });
    }
}