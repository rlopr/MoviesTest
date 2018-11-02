package com.example.rubylopez.movietest.allmovies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rubylopez.movietest.R;
import com.example.rubylopez.movietest.common.models.MoviesResults;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MoviesResults> itemList = new ArrayList<>();
    private MovieClickListener movieClickListener;

    public MoviesAdapter(Context context, MovieClickListener movieClickListener) {
        this.context = context;
        this.movieClickListener = movieClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MoviewViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie,
                viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        ((MoviewViewHolder) viewHolder).init(itemList.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieClickListener.onMovieClicked(viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void addItems(List<MoviesResults> list, boolean reset) {
        if (reset) {
            itemList = new ArrayList<>();
        }
        itemList.addAll(list);
        notifyDataSetChanged();
    }
}
