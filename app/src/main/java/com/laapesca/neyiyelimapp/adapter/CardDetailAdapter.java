package com.laapesca.neyiyelimapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.laapesca.neyiyelimapp.Model.GetCardItemModel;
import com.laapesca.neyiyelimapp.Model.GetCardTwo;
import com.laapesca.neyiyelimapp.Model.GetMenu;
import com.laapesca.neyiyelimapp.Model.GetMenuItem;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.utils.Preference;
import com.laapesca.neyiyelimapp.utils.RetrofitClients;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<GetCardTwo> modelList;
    private CardDetailAdapter.OnItemClickListener mItemClickListener;
    private Fragment fragment;
    boolean isLike=true;
    int lastPosition = 0;

    public CardDetailAdapter(Context context, ArrayList<GetCardTwo> modelList, Fragment fragment) {
        this.mContext = context;
        this.modelList = modelList;
        this.fragment = fragment;
    }

    public void updateList(ArrayList<GetCardTwo> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public CardDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_details_item, viewGroup, false);
        return new CardDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof CardDetailAdapter.ViewHolder) {
            final GetCardTwo model = getItem(position);
            final CardDetailAdapter.ViewHolder genericViewHolder = (CardDetailAdapter.ViewHolder) holder;


            genericViewHolder.item_name.setText(model.getProductDetial().get(0).getSubmenuTitle() +"( "+(model.getQuantity()+" )"));
            genericViewHolder.item_price_sub.setText(model.getNewTotal().toString());
        }
    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void SetOnItemClickListener(final CardDetailAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private GetCardTwo getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, GetCardTwo model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView item_name;
        private TextView item_price_sub;

        public ViewHolder(final View itemView) {
            super(itemView);

          this.item_name=itemView.findViewById(R.id.item_name);
          this.item_price_sub=itemView.findViewById(R.id.item_price_sub);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }



}

