package com.laapesca.neyiyelimapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.laapesca.neyiyelimapp.Model.AllImageModelData;
import com.laapesca.neyiyelimapp.Model.CategoryModelData;
import com.laapesca.neyiyelimapp.R;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class CategoryRecyleviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<CategoryModelData> modelList;
    private OnItemClickListener mItemClickListener;
    private Fragment fragment;
    boolean isLike=true;
    int lastPosition = 0;

    public CategoryRecyleviewAdapter(Context context, ArrayList<CategoryModelData> modelList, Fragment fragment) {
        this.mContext = context;
        this.modelList = modelList;
        this.fragment = fragment;
    }

    public void updateList(ArrayList<CategoryModelData> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_categorry, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final CategoryModelData model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            String CategoryName = model.getCategoryName().toString();

            genericViewHolder.txt_name.setText(CategoryName);



            genericViewHolder.RR_card_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    lastPosition =position;
                    notifyDataSetChanged();

                   // genericViewHolder.RR_card.setBackgroundResource(R.drawable.roundbttn_red);

                }
            });

            if(lastPosition == position)
            {
                genericViewHolder.RR_card.setBackgroundResource(R.drawable.roundbttn_red);

            }else
            {
                genericViewHolder.RR_card.setBackgroundResource(R.drawable.roundbttn_white_one);
            }

        }

    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private CategoryModelData getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, CategoryModelData model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_name;
        private RelativeLayout RR_card;
        private LinearLayout RR_card_main;

        public ViewHolder(final View itemView) {
            super(itemView);

           this.txt_name=itemView.findViewById(R.id.txt_name);
           this.RR_card=itemView.findViewById(R.id.RR_card);
           this.RR_card_main=itemView.findViewById(R.id.RR_card_main);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

