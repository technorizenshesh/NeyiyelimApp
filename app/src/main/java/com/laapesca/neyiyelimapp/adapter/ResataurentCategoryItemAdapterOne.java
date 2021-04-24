package com.laapesca.neyiyelimapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.laapesca.neyiyelimapp.Model.GetMenuItem;
import com.laapesca.neyiyelimapp.Model.RestaurentModelDataCat;
import com.laapesca.neyiyelimapp.R;

import java.util.ArrayList;

public class ResataurentCategoryItemAdapterOne extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<GetMenuItem> modelList;
    private ResataurentCategoryItemAdapterOne.OnItemClickListener mItemClickListener;
    private Fragment fragment;
    boolean isLike=true;
    int lastPosition = 0;

    public ResataurentCategoryItemAdapterOne(Context context, ArrayList<GetMenuItem> modelList, Fragment fragment) {
        this.mContext = context;
        this.modelList = modelList;
        this.fragment = fragment;
    }

    public void updateList(ArrayList<GetMenuItem> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ResataurentCategoryItemAdapterOne.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_res_cat_one, viewGroup, false);
        return new ResataurentCategoryItemAdapterOne.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ResataurentCategoryItemAdapterOne.ViewHolder) {
            final GetMenuItem model = getItem(position);
            final ResataurentCategoryItemAdapterOne.ViewHolder genericViewHolder = (ResataurentCategoryItemAdapterOne.ViewHolder) holder;

          // genericViewHolder.txt_name.setText(Des);

           genericViewHolder.tv_name.setText(model.getSubmenuTitle());
           genericViewHolder.tv_price.setText("₺"+model.getSubmenuPrice());

           String ImageProduct = model.getImage().toString();

           if(!ImageProduct.equalsIgnoreCase(""))
           {
               Glide.with(mContext).load(ImageProduct).into(genericViewHolder.img_product);
           }

        }
    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void SetOnItemClickListener(final ResataurentCategoryItemAdapterOne.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private GetMenuItem getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, GetMenuItem model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_price;
        private ImageView img_product;

        public ViewHolder(final View itemView) {
            super(itemView);

          this.tv_name=itemView.findViewById(R.id.tv_name);
          this.tv_price=itemView.findViewById(R.id.tv_price);
          this.img_product=itemView.findViewById(R.id.img_product);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }

}

