package com.example.rubylopez.movietest.moviedetail.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.rubylopez.movietest.R;
import com.example.rubylopez.movietest.common.datasources.apiconnections.ApiConnection;
import com.example.rubylopez.movietest.common.models.Genre;
import com.example.rubylopez.movietest.common.models.MovieResult;
import com.example.rubylopez.movietest.common.models.MovieResultDetailed;
import com.example.rubylopez.movietest.common.utilities.Constants;
import com.example.rubylopez.movietest.moviedetail.presenter.MovieDetailPresenter;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailViewInterface {

    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.tvReleaseDate)
    TextView tvReleaseDate;
    @BindView(R.id.tvGenre)
    TextView tvGenre;
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
        presenter.setMovie((MovieResult) getIntent().getParcelableExtra(EXTRA_MOVIE));
    }

    private void initialize() {

    }

    @Override
    public void onGetMovieSucess(MovieResult result) {
        setTitle(result.getOriginal_title());

        Picasso.get().load(Constants.APIConstants.IMAGES_MEDIUM + result.getPoster_path()).into(ivImage);
        tvReleaseDate.setText(result.getRelease_date());
        tvDescription.setText(result.getOverview());
    }

    @Override
    public void onGetMovieDetailSucess(MovieResultDetailed result) {
        if (result.getGenres() == null) return;

        StringBuilder genresText = new StringBuilder();
        for (Genre genre : result.getGenres()) {
            if (genresText.length() > 0) {
                genresText.append(", ");
            }
            genresText.append(genre.getName());
        }

        tvGenre.setText(genresText.toString());
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onGetMovieFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter = null;
    }

}
