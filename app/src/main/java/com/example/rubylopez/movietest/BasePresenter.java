package com.example.rubylopez.movietest;

import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.datasources.interfaces.ApiEndpointInterface;

public abstract class BasePresenter {

    public BaseViewInterface view;
    protected ApiEndpointInterface api;

    public BasePresenter(BaseViewInterface view, ApiEndpointInterface api) {
        this.view = view;
        this.api = api;
    }

}
