package com.example.rubylopez.movietest.allmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.example.rubylopez.movietest.R;
import com.example.rubylopez.movietest.common.datasources.apiconnections.ApiConnection;
import com.example.rubylopez.movietest.common.models.MoviesResults;

import java.util.List;

public class AllMoviesActivity extends AppCompatActivity implements AllMoviesViewInterface {

    @BindView(R.id.rvList)
    RecyclerView rvList;

    AllMoviesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies);

        presenter = new AllMoviesPresenter(this, ApiConnection.getApi(this));
        presenter.getMovies();
    }

    private void initialize(){

    }

    @Override
    public void onGetMoviesSucess(List<MoviesResults> result) {
        //rvList.setAdapter();
    }

    @Override
    public void onGetMoviesFailure(String error) {

    }
}
