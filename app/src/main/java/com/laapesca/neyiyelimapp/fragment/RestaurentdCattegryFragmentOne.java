package com.laapesca.neyiyelimapp.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laapesca.neyiyelimapp.Model.Branch;
import com.laapesca.neyiyelimapp.Model.BranchModel;
import com.laapesca.neyiyelimapp.Model.GetMenu;
import com.laapesca.neyiyelimapp.Model.GetMenuItem;
import com.laapesca.neyiyelimapp.Model.RestaurentModelDataCat;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.activity.LoginActivity;
import com.laapesca.neyiyelimapp.adapter.CountrySpinnerAdapter;
import com.laapesca.neyiyelimapp.adapter.ResataurentCategoryItemAdapter;
import com.laapesca.neyiyelimapp.adapter.ResataurentCategoryItemAdapterOne;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;
import com.laapesca.neyiyelimapp.utils.SessionManager;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurentdCattegryFragmentOne extends Fragment {

    View view;

    private ProgressBar progressBar;
    private Button btn_next;

    private ArrayList<GetMenuItem> modellist = new ArrayList<GetMenuItem>();
    ResataurentCategoryItemAdapterOne mAdapterListRestaurent;
    RecyclerView recycle_itemCat;

    private SessionManager sessionManager;
    private TextView txt_emty;
    Fragment fragment;
    RelativeLayout RR_back;


    private String code[] ={"1","2","3","4","5","6","7","8","9","10"};
    private int flags[]= {R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo};
    String Quantity = "";

    public RestaurentdCattegryFragmentOne() {
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
        view = inflater.inflate(R.layout.fragment_category_one, container, false);

        sessionManager = new SessionManager(getActivity());
        recycle_itemCat =view.findViewById(R.id.recycle_itemCat);
        txt_emty =view.findViewById(R.id.txt_emty);
        progressBar =view.findViewById(R.id.progressBar);
        btn_next =view.findViewById(R.id.btn_next);

        RR_back =view.findViewById(R.id.RR_back);
        RR_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new CartDetailFragment();
                loadFragment(fragment);

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

    private void setAdapter(ArrayList<GetMenuItem> modellist) {

        mAdapterListRestaurent = new ResataurentCategoryItemAdapterOne(getActivity(), this.modellist, RestaurentdCattegryFragmentOne.this);
        recycle_itemCat.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycle_itemCat.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recycle_itemCat.setAdapter(mAdapterListRestaurent);

        mAdapterListRestaurent.SetOnItemClickListener(new ResataurentCategoryItemAdapterOne.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, GetMenuItem model) {

                AlertDie(model.getSubmenuID(),model.getSubmenuPrice());

            }
        });
    }

    private void AlertDie(String submenuID,String Price1) {

        CountrySpinnerAdapter customAdapter=new CountrySpinnerAdapter(getActivity(),flags,code);

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_editcart);
        Button dialogButton = (Button) dialog.findViewById(R.id.addToCart);
        Button btn_recal = (Button) dialog.findViewById(R.id.btn_recal);
        TextView txt_price = (TextView) dialog.findViewById(R.id.txt_price);
        RelativeLayout RR_cross = (RelativeLayout) dialog.findViewById(R.id.RR_cross);
        EditText edt_descriptio = (EditText) dialog.findViewById(R.id.edt_descriptio);
        Spinner spinnergender = (Spinner) dialog.findViewById(R.id.spinnergender);
        spinnergender.setAdapter(customAdapter);
        txt_price.setText(Price1+"");
        btn_recal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Price= Integer.parseInt(Quantity);
                int Price11= Integer.parseInt(Price1);
                int TotalPrice=  Price*Price11;
                txt_price.setText(TotalPrice+"");
            }
        });

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

                String Remark =  edt_descriptio.getText().toString();

                if (sessionManager.isNetworkAvailable()) {

                    progressBar.setVisibility(View.VISIBLE);

                    addToCard(submenuID,Remark,Quantity);

                }else {
                    Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
                }

               /* fragment = new CartDetailFragment();
                loadFragment(fragment);*/

                // AddCartDailog();
            }
        });

        spinnergender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3){

                Quantity = code[pos];

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        RR_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("home").replace(R.id.mainContent, fragment).commit();
        }
    }

    public void getRestaurentMethod() {

        //  String branchId = Preference.get( getActivity(), Preference.KEY_BRANCH_ID);
        String branchId = "652";
        // String menuID = Preference.get( getActivity(), Preference.KEY_BRANCH_ID);
        String menuID = "665";

        Call<GetMenu> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_sub_menu(branchId,menuID);
        call.enqueue(new Callback<GetMenu>() {
            @Override
            public void onResponse(Call<GetMenu> call, Response<GetMenu> response) {
                try {

                    progressBar.setVisibility(View.GONE);

                    GetMenu myclass= response.body();

                    String status = myclass.getStatus();
                    String result = myclass.getMessage();

                    if (status.equalsIgnoreCase("1")){

                        txt_emty.setVisibility(View.GONE);

                        modellist = (ArrayList<GetMenuItem>) myclass.getResult();
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
            public void onFailure(Call<GetMenu> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                txt_emty.setVisibility(View.VISIBLE);
            }
        });
    }

    public void addToCard(String submenuID, String remark, String qantity) {

        String UserId = Preference.get( getActivity(), Preference.KEY_USER_ID);

        String RestaurentName = Preference.get( getActivity(),Preference.KEY_RestaurentName);
        String RestaurentName_img =  Preference.get( getActivity(),Preference.KEY_RestaurentName_img);

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .add_to_cart(UserId,submenuID,qantity,remark,RestaurentName,RestaurentName_img);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    progressBar.setVisibility(View.GONE);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString ("status");
                    String result = jsonObject.getString ("result");

                    if (result.equalsIgnoreCase("successfully")){

                        Toast.makeText(getActivity(), "item add successfully", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}