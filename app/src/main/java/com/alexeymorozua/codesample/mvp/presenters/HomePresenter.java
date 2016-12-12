package com.alexeymorozua.codesample.mvp.presenters;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.local.PreferencesHelper;
import com.alexeymorozua.codesample.mvp.views.HomeView;
import com.alexeymorozua.codesample.util.BusHelper;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;

/**
 * Created by john on 30.11.2016.
 */

@InjectViewState public class HomePresenter extends MvpPresenter<HomeView> {

  @Inject PreferencesHelper mPreferencesHelper;
  @Inject Bus mBus;

  public HomePresenter() {
    CodeSampleApp.getAppComponent().inject(this);

    mBus.register(this);
  }

  public void signOut() {
    mPreferencesHelper.clear();
    getViewState().signOut();
  }

  public void hideRepositoryDetail() {
    getViewState().hideRepositoryDetail();
  }

  @Subscribe public void showRepositoryDetail(BusHelper.ShowRepositoryDetail showRepositoryDetail) {
    getViewState().showRepositoryDetail(showRepositoryDetail.mRepository);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mBus.unregister(this);
  }
}
