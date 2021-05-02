package com.laapesca.neyiyelimapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.laapesca.neyiyelimapp.Model.SelectAdreess;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.activity.DeliveryAddress;
import com.laapesca.neyiyelimapp.listner.onListClickListener;

import java.util.ArrayList;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class SelectedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    int pos = 0;
    private Context mContext;
    private ArrayList<SelectAdreess> modelList;
    private OnItemClickListener mItemClickListener;
    LinearLayout LL_view;
   int lastPosition;
    boolean isEual=false;
    onListClickListener listner;

    public SelectedRecyclerViewAdapter(Context context, ArrayList<SelectAdreess> modelList, onListClickListener listner) {
        this.mContext = context;
        this.modelList = modelList;
        this.listner = listner;
    }

    public void updateList(ArrayList<SelectAdreess> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.slected_list_wakend, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final SelectAdreess model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txt_address.setText(model.getAddress());
            genericViewHolder.txt_addressType.setText(model.getAddressType());
            genericViewHolder.txt_address_direction.setText(model.getAddressDirection());


            genericViewHolder.card_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listner.listClick(position,model.getId());

                     isEual= true;

                    lastPosition=position;
                   notifyDataSetChanged();
                }
            });

            genericViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    isEual= true;
                    listner.listClick(position,model.getId());
                    lastPosition=position;
                    notifyDataSetChanged();
                }
            });

            if(isEual)
            {
                if(lastPosition==position)
                {
                    genericViewHolder.checkBox.setChecked(true);

                }else
                {
                    genericViewHolder.checkBox.setChecked(false);
                }

            }else
            {
                String lastPosition1 = model.getIsActive();

                if(lastPosition1.equalsIgnoreCase("1"))
                {
                    genericViewHolder.checkBox.setChecked(true);

                }else
                {
                    genericViewHolder.checkBox.setChecked(false);
                }

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

    private SelectAdreess getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, SelectAdreess model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_addressType;
        private TextView txt_address_direction;
        private TextView txt_address;
        private CardView card_img;
        private LinearLayout LL_view;
        private CheckBox checkBox;

        public ViewHolder(final View itemView) {
            super(itemView);

           this.txt_addressType=itemView.findViewById(R.id.txt_addressType);
           this.txt_address_direction=itemView.findViewById(R.id.txt_address_direction);
           this.txt_address=itemView.findViewById(R.id.txt_address);
           this.card_img=itemView.findViewById(R.id.card_img);
           this.LL_view=itemView.findViewById(R.id.LL_view);
           this.checkBox=itemView.findViewById(R.id.checkBox);

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

