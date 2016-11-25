package com.alexeymorozua.codesample.mvp.presenters;

import android.text.TextUtils;
import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.local.PreferencesHelper;
import com.alexeymorozua.codesample.mvp.views.SplashView;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;

/**
 * Created by john on 24.11.2016.
 */

public class SplashPresenter extends BasePresenter<SplashView> {

  @Inject PreferencesHelper mPreferencesHelper;

  public SplashPresenter() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  public void checkAuthorized() {

    Subscription subscription = Observable.just(mPreferencesHelper.getToken()).subscribe(token -> {
      for (SplashView splashView : getAttachedViews()) {
        splashView.setAuthorized(!TextUtils.isEmpty(token));
      }
    });
    unsubscribeOnDestroy(subscription);
    }
}
