package com.example.rubylopez.movietest.moviedetail.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.rubylopez.movietest.R;
import com.example.rubylopez.movietest.allmovies.adapter.MovieClickListener;
import com.example.rubylopez.movietest.allmovies.adapter.MoviesAdapter;
import com.example.rubylopez.movietest.common.datasources.apiconnections.ApiConnection;
import com.example.rubylopez.movietest.common.models.MoviesResults;
import com.example.rubylopez.movietest.common.utilities.Constants;
import com.example.rubylopez.movietest.moviedetail.presenter.MovieDetailPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailViewInterface {

    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    Unbinder unbinder;

    MovieDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        unbinder = ButterKnife.bind(this);

        initialize();
        presenter = new MovieDetailPresenter(this, ApiConnection.getApi(this));
        presenter.setMovie((MoviesResults) getIntent().getParcelableExtra(EXTRA_MOVIE));
    }

    private void initialize() {

    }

    @Override
    public void onGetMovieSucess(MoviesResults result) {
        setTitle(result.getOriginal_title());

        Picasso.get().load(Constants.APIConstants.IMAGES + result.getPoster_path()).into(ivImage);
        tvDescription.setText(result.getOverview());

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onGetMovieFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter = null;
    }

}
