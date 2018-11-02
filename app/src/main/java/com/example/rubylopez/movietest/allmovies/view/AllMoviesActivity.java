package com.example.rubylopez.movietest.allmovies.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.example.rubylopez.movietest.R;
import com.example.rubylopez.movietest.allmovies.adapter.MovieClickListener;
import com.example.rubylopez.movietest.allmovies.adapter.MoviesAdapter;
import com.example.rubylopez.movietest.allmovies.presenter.AllMoviesPresenter;
import com.example.rubylopez.movietest.common.datasources.apiconnections.ApiConnection;
import com.example.rubylopez.movietest.common.models.MovieResult;
import com.example.rubylopez.movietest.moviedetail.view.MovieDetailActivity;

import java.util.List;

public class AllMoviesActivity extends AppCompatActivity implements AllMoviesViewInterface, MovieClickListener {

    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    Unbinder unbinder;

    AllMoviesPresenter presenter;
    MoviesAdapter adapter;

    // For loading more.
    private int visibleThreshold = 20;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies);
        unbinder = ButterKnife.bind(this);

        initialize();
        presenter = new AllMoviesPresenter(this, ApiConnection.getApi(this));
        presenter.getMovies();
        loading = true;
    }

    private void initialize() {
        adapter = new MoviesAdapter(this, this);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rvList.setLayoutManager(layoutManager);
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    // End has been reached
                    presenter.getMovies();
                    loading = true;
                }
            }
        });
        rvList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                rvList.scrollToPosition(0);
                presenter.searchMovie(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });

        menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                rvList.scrollToPosition(0);
                loading = true;
                presenter.stopSearching();
                presenter.getMovies();
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetMoviesSucess(List<MovieResult> result, boolean reset) {
        adapter.addItems(result, reset);
        progressBar.setVisibility(View.GONE);
        loading = false;
    }

    @Override
    public void onGetMoviesFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        loading = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter = null;
        adapter = null;
    }

    @Override
    public void onMovieClicked(int position) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, presenter.getMovie(position));
        startActivity(intent);
    }
}
