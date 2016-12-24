package com.alexeymorozua.codesample.mvp.presenters;

import android.content.Context;
import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.DataManager;
import com.alexeymorozua.codesample.mvp.views.HomeView;
import com.alexeymorozua.codesample.util.BusHelper;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lapism.searchview.SearchHistoryTable;
import com.lapism.searchview.SearchItem;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;

/**
 * Created by john on 30.11.2016.
 */

@InjectViewState public class HomePresenter extends MvpPresenter<HomeView> {

  @Inject DataManager mDataManager;
  @Inject Bus mBus;
  @Inject Context mContext;

  private SearchHistoryTable mHistoryDatabase;

  public HomePresenter() {
    CodeSampleApp.getAppComponent().inject(this);

    mBus.register(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    initializeHistoryDatabase();
  }

  private void initializeHistoryDatabase() {
    mHistoryDatabase = new SearchHistoryTable(mContext);
    mHistoryDatabase.setHistorySize(10);
  }

  public void clearHistoryDatabase() {
    mHistoryDatabase.clearDatabase();
    getViewState().setEmptyListSearchHistory();
  }

  public void startDownloadRepositories(String query) {
    mBus.post(new BusHelper.StartDownloadRepository(query));
    mHistoryDatabase.addItem(new SearchItem(query));
    getViewState().selectTab();
    getViewState().hideRepositoryDetail();
  }

  public void signOut() {
    mDataManager.clearPreferences();
    mHistoryDatabase.clearDatabase();
    getViewState().signOut();
  }

  public void hideRepositoryDetail() {
    getViewState().hideRepositoryDetail();
  }

  public void openRepositoryDetail() {
    getViewState().openRepositoryDetail();
  }

  public void closeRepositoryDetail() {
    getViewState().closeRepositoryDetail();
  }

  @Subscribe public void showRepositoryDetail(BusHelper.ShowRepositoryDetail showRepositoryDetail) {
    getViewState().showRepositoryDetail(showRepositoryDetail.mRepositoryDetail);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mBus.unregister(this);
  }
}
