package com.laapesca.neyiyelimapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.laapesca.neyiyelimapp.Model.CategoryModelData;
import com.laapesca.neyiyelimapp.Model.RestaurentModelData;
import com.laapesca.neyiyelimapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LatestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<RestaurentModelData> modelList;
    private LatestAdapter.OnItemClickListener mItemClickListener;

    public LatestAdapter(Context context, ArrayList<RestaurentModelData> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<RestaurentModelData> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public LatestAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_fragment_adapter, viewGroup, false);
        return new LatestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof LatestAdapter.ViewHolder) {
            final RestaurentModelData model = getItem(position);
            final LatestAdapter.ViewHolder genericViewHolder = (LatestAdapter.ViewHolder) holder;


            if( model.getRestaurantData().getRestaurantName()!=null)
            {
                String RestaurantName = model.getRestaurantData().getRestaurantName().toString();
                genericViewHolder.txt_re_name.setText(RestaurantName);
            }

            if(model.getMinDeliveryTime()!=null)
            {
                String ServicesTime = model.getMinDeliveryTime().toString();
                genericViewHolder.txt_servicesTime.setText(ServicesTime);
            }

            if( model.getBranchMinOrderAmount()!=null)
            {
                String MinmumOrder = model.getBranchMinOrderAmount().toString();
                genericViewHolder.txt_minimumOrder.setText(MinmumOrder);
            }

        }

    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final LatestAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private RestaurentModelData getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, RestaurentModelData model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView txt_re_name;
        private TextView payment_type;
        private TextView txt_minimumOrder;
        private TextView txt_servicesTime;
        private TextView txt_COntryname;
        private TextView txt_City;


        public ViewHolder(final View itemView) {
            super(itemView);

            this.txt_re_name=itemView.findViewById(R.id.txt_re_name);
            this.payment_type=itemView.findViewById(R.id.payment_type);
            this.txt_minimumOrder=itemView.findViewById(R.id.txt_minimumOrder);
            this.txt_servicesTime=itemView.findViewById(R.id.txt_servicesTime);
            this.txt_COntryname=itemView.findViewById(R.id.txt_COntryname);
            this.txt_City=itemView.findViewById(R.id.txt_City);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }

}

