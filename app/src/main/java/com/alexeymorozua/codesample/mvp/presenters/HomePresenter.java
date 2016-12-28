package com.alexeymorozua.codesample.mvp.presenters;

import android.content.Context;
import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.DataManager;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.alexeymorozua.codesample.mvp.views.HomeView;
import com.alexeymorozua.codesample.util.BusHelper;
import com.arellomobile.mvp.InjectViewState;
import com.lapism.searchview.SearchHistoryTable;
import com.lapism.searchview.SearchItem;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by john on 30.11.2016.
 */

@InjectViewState public class HomePresenter extends BasePresenter<HomeView> {

  @Inject DataManager mDataManager;
  @Inject Bus mBus;
  @Inject Context mContext;

  private SearchHistoryTable mHistoryDatabase;
  private Long idRepository = 0L;

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

  public void addRepository(RepositoryDetail repositoryDetail) {
    mBus.post(new BusHelper.AddRepositoryDb(repositoryDetail));
    getViewState().saveRepository();
  }

  public void deleteRepository(RepositoryDetail repositoryDetail) {
    mBus.post(new BusHelper.DeleteRepositoryDb(repositoryDetail));
    getViewState().deleteRepository();
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
    if (!idRepository.equals(showRepositoryDetail.mRepositoryDetail.getId())) {
      getViewState().hideRepositoryDetail();
      Subscription subscription = Observable.just(showRepositoryDetail.mRepositoryDetail)
          .delay(500, TimeUnit.MILLISECONDS)
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(repositoryDetail -> {
            getViewState().showRepositoryDetail(repositoryDetail);
            idRepository = repositoryDetail.getId();
          }, throwable -> {
            Timber.e(throwable.toString());
          });
      unsubscribeOnDestroy(subscription);
    }
  }

  @Subscribe public void hideSaveRepositoryDetail(
      BusHelper.HideSaveRepositoryDetail hideSaveRepositoryDetail) {
    getViewState().hideRepositoryDetail();
  }

  public void resetIdRepository() {
    idRepository = 0L;
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mBus.unregister(this);
  }
}
