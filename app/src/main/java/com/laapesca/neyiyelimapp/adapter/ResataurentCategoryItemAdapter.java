package com.laapesca.neyiyelimapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.laapesca.neyiyelimapp.Model.BranchModel;
import com.laapesca.neyiyelimapp.Model.RestaurentModelData;
import com.laapesca.neyiyelimapp.Model.RestaurentModelDataCat;
import com.laapesca.neyiyelimapp.R;

import java.util.ArrayList;

public class ResataurentCategoryItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<BranchModel> modelList;
    private ResataurentCategoryItemAdapter.OnItemClickListener mItemClickListener;
    private Fragment fragment;
    boolean isLike=true;
    int lastPosition = 0;

    public ResataurentCategoryItemAdapter(Context context, ArrayList<BranchModel> modelList, Fragment fragment) {
        this.mContext = context;
        this.modelList = modelList;
        this.fragment = fragment;
    }

    public void updateList(ArrayList<BranchModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ResataurentCategoryItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_res_cat, viewGroup, false);
        return new ResataurentCategoryItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ResataurentCategoryItemAdapter.ViewHolder) {
            final BranchModel model = getItem(position);
            final ResataurentCategoryItemAdapter.ViewHolder genericViewHolder = (ResataurentCategoryItemAdapter.ViewHolder) holder;

          // genericViewHolder.txt_name.setText(Des);

           genericViewHolder.txt_re_name.setText(model.getMenuTitle());

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final ResataurentCategoryItemAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private BranchModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, BranchModel model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_re_name;


        public ViewHolder(final View itemView) {
            super(itemView);

            this.txt_re_name=itemView.findViewById(R.id.txt_re_name);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }

}

