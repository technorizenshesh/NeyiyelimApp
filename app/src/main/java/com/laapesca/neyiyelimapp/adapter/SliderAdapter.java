package com.laapesca.neyiyelimapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.activity.SliderItem;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {


    private final Context context;
    private ArrayList<SliderItem> myProductModeList = new ArrayList<>();


    public SliderAdapter(Context context, ArrayList<SliderItem> myProductModeListls) {
        this.context = context;
        this.myProductModeList = myProductModeListls;


    }


    public void renewItems(ArrayList<SliderItem> sliderItems) {
        this.myProductModeList = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.myProductModeList.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(SliderItem sliderItem) {
        this.myProductModeList.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        SliderItem sliderItem = myProductModeList.get(position);
        viewHolder.textViewDescription.setText(sliderItem.getSlderName());
        viewHolder.textViewDescription.setTextSize(16);
        viewHolder.textViewDescription.setTextColor(Color.WHITE);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getSliderImageUrl())
                .fitCenter()
                .into(viewHolder.imageViewBackground);

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return myProductModeList.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }


}
