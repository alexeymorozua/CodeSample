package com.alexeymorozua.codesample.mvp.presenters;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.local.PreferencesHelper;
import com.alexeymorozua.codesample.mvp.views.HomeView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import javax.inject.Inject;

/**
 * Created by john on 30.11.2016.
 */

@InjectViewState public class HomePresenter extends MvpPresenter<HomeView> {

  @Inject PreferencesHelper mPreferencesHelper;

  public HomePresenter() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  public void signOut() {
    mPreferencesHelper.clear();
    getViewState().signOut();
  }
}
