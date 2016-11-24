package com.alexeymorozua.codesample.mvp.presenters;

import com.alexeymorozua.codesample.mvp.views.SplashView;

/**
 * Created by john on 24.11.2016.
 */

public class SplashPresenter extends BasePresenter<SplashView> {

    public void checkAuthorized() {
        for (SplashView splashView : getAttachedViews()) {
            splashView.setAuthorized(true);
        }
    }
}
