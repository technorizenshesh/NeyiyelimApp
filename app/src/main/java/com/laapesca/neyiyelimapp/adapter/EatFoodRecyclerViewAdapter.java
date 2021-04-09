package com.laapesca.neyiyelimapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.laapesca.neyiyelimapp.Model.DiscoversModel;
import com.laapesca.neyiyelimapp.R;

import java.util.ArrayList;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class EatFoodRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    int pos = 0;
    private Context mContext;
    private ArrayList<DiscoversModel> modelList;
    private OnItemClickListener mItemClickListener;
    boolean isLike = true;
    int lastPosition = 0;

    public EatFoodRecyclerViewAdapter(Context context, ArrayList<DiscoversModel> modelArrayList_nearBy) {
        this.mContext = context;
        this.modelList = modelArrayList_nearBy;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favr, viewGroup, false);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_eat_food, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final DiscoversModel model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;
        }

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private DiscoversModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, DiscoversModel model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout item_rl;
        TextView txt_name;
        //  CircleImageView category_img;

        public ViewHolder(final View itemView) {
            super(itemView);

            item_rl = itemView.findViewById(R.id.item_rl);
            //  this.category_img=itemView.findViewById(R.id.category_img);
            // this.item_rl =itemView.findViewById(R.id.item_rl);

            item_rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));

                }
            });
        }
    }

}

