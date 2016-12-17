package com.alexeymorozua.codesample.mvp.presenters;

import android.support.annotation.NonNull;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by john on 24.11.2016.
 */

public class BasePresenter<View extends MvpView> extends MvpPresenter<View> {

  private CompositeSubscription compositeSubscription = new CompositeSubscription();

  protected void unsubscribeOnDestroy(@NonNull Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    compositeSubscription.clear();
  }
}
