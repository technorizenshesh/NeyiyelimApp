package com.laapesca.neyiyelimapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.laapesca.neyiyelimapp.Model.MyOrder;
import com.laapesca.neyiyelimapp.Model.MyOrderModelData;
import com.laapesca.neyiyelimapp.Model.SelectAdreess;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.listner.onListClickListener;

import java.util.ArrayList;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class MyOrderRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    int pos = 0;
    private Context mContext;
    private ArrayList<MyOrderModelData> modelList;
    private OnItemClickListener mItemClickListener;
    LinearLayout LL_view;
   int lastPosition;
    boolean isEual=false;

    public MyOrderRecyclerViewAdapter(Context context, ArrayList<MyOrderModelData> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<MyOrderModelData> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_order_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final MyOrderModelData model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

           genericViewHolder.txt_Order_id.setText("Order Id : "+model.getOrderId());
           genericViewHolder.txt_delivery_date.setText("Delivery Date : "+model.getDeliveryDate());
           genericViewHolder.txt_delivery_time.setText("Delivery Time : "+model.getDeliveryDate());
           genericViewHolder.txt_Amount.setText("Total : "+model.getTotalAmount());
           genericViewHolder.txt_Address.setText("Address : "+model.getPerIvrCost());

        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

   public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private MyOrderModelData getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, MyOrderModelData model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_Order_id;
        private TextView txt_delivery_date;
        private TextView txt_delivery_time;
        private TextView txt_Amount;
        private TextView txt_Address;

        public ViewHolder(final View itemView) {
            super(itemView);

           this.txt_Order_id=itemView.findViewById(R.id.txt_Order_id);
           this.txt_delivery_date=itemView.findViewById(R.id.txt_delivery_date);
           this.txt_delivery_time=itemView.findViewById(R.id.txt_delivery_time);
           this.txt_Amount=itemView.findViewById(R.id.txt_Amount);
           this.txt_Address=itemView.findViewById(R.id.txt_address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }

    private void ColorView(int color,int color1) {

        LL_view.setBackgroundResource(color1);
    }




}

