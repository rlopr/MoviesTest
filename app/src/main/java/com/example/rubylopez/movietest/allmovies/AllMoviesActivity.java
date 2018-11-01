package com.example.rubylopez.movietest.allmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.example.rubylopez.movietest.R;
import com.example.rubylopez.movietest.common.datasources.apiconnections.ApiConnection;
import com.example.rubylopez.movietest.common.models.MoviesResults;

import java.util.List;

public class AllMoviesActivity extends AppCompatActivity implements AllMoviesViewInterface {

    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    Unbinder unbinder;

    AllMoviesPresenter presenter;
    MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies);
        unbinder = ButterKnife.bind(this);

        initialize();
        presenter = new AllMoviesPresenter(this, ApiConnection.getApi(this));
        presenter.getMovies();
    }

    private void initialize(){
        adapter = new MoviesAdapter(this);
        rvList.setAdapter(adapter);
    }

    @Override
    public void onGetMoviesSucess(List<MoviesResults> result) {
        adapter.addItems(result);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onGetMoviesFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
