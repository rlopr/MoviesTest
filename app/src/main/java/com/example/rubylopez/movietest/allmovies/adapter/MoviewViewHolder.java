package com.example.rubylopez.movietest.allmovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rubylopez.movietest.R;
import com.example.rubylopez.movietest.common.models.MovieResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.rubylopez.movietest.common.utilities.Constants;
import com.squareup.picasso.Picasso;

public class MoviewViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.tvGenre)
    TextView tvGenre;
    @BindView(R.id.tvReleaseDate)
    TextView tvReleaseDate;
    @BindView(R.id.tvName)
    TextView tvName;

    public MoviewViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void init(MovieResult item) {
        Picasso.get().load(Constants.APIConstants.IMAGES_SMALL + item.getPoster_path()).into(ivImage);
        tvReleaseDate.setText(item.getRelease_date());
        tvName.setText(item.getOriginal_title());
    }

}
