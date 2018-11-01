package com.example.rubylopez.movietest.allmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rubylopez.movietest.R;
import com.example.rubylopez.movietest.common.models.MoviesResults;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviewViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.tvName)
    TextView tvName;

    public MoviewViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void init(MoviesResults item) {
        //Picasso.with(itemView.getContext()).load(b.getUrlLogo()).into(ivImage);
        tvName.setText(item.getOriginal_title());
    }

}
