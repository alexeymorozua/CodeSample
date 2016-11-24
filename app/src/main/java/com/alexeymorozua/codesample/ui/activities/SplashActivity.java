package com.alexeymorozua.codesample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.alexeymorozua.codesample.mvp.presenters.SplashPresenter;
import com.alexeymorozua.codesample.mvp.views.SplashView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;


/**
 * Created by john on 24.11.2016.
 */

public class SplashActivity extends MvpAppCompatActivity implements SplashView {

    @InjectPresenter
    SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMvpDelegate().onAttach();

        mSplashPresenter.checkAuthorized();
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, isAuthorized ? HomeActivity.class : SignInActivity.class);
            startActivity(intent);
            this.finish();
        }, 1000);
    }
}
