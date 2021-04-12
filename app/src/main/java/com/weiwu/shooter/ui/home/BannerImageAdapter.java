package com.weiwu.shooter.ui.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weiwu.shooter.dao.ImageBean;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class BannerImageAdapter extends BannerAdapter<ImageBean,BannerImageAdapter.BannerViewHolder> {

    public BannerImageAdapter(List<ImageBean> datas) {
        super(datas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, ImageBean data, int position, int size) {
        holder.imageView.setImageResource(data.imageRes);
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public BannerViewHolder(@NonNull ImageView imageView) {
            super(imageView);
            this.imageView = imageView;
        }
    }
}
