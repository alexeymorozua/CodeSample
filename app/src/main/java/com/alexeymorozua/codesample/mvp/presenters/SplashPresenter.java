package com.alexeymorozua.codesample.mvp.presenters;

import android.text.TextUtils;
import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.DataManager;
import com.alexeymorozua.codesample.mvp.views.SplashView;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by john on 24.11.2016.
 */

public class SplashPresenter extends BasePresenter<SplashView> {

  @Inject DataManager mDataManager;

  public SplashPresenter() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  public void checkAuthorized() {

    Subscription subscription = mDataManager.getToken()
        .delay(1, TimeUnit.SECONDS)
        .subscribe(token -> {
      for (SplashView splashView : getAttachedViews()) {
        splashView.setAuthorized(!TextUtils.isEmpty(token));
      }
    });
    unsubscribeOnDestroy(subscription);
    }
}
