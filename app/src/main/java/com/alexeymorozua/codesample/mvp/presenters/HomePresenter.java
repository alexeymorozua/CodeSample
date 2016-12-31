package com.alexeymorozua.codesample.mvp.presenters;

import android.content.Context;
import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.DataManager;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.alexeymorozua.codesample.mvp.views.HomeView;
import com.alexeymorozua.codesample.util.RxBus;
import com.alexeymorozua.codesample.util.RxBusHelper;
import com.arellomobile.mvp.InjectViewState;
import com.lapism.searchview.SearchHistoryTable;
import com.lapism.searchview.SearchItem;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by john on 30.11.2016.
 */

@InjectViewState public class HomePresenter extends BasePresenter<HomeView> {

  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;
  @Inject Context mContext;

  private SearchHistoryTable mHistoryDatabase;
  private Long idRepository = 0L;

  public HomePresenter() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    initializeHistoryDatabase();

    showRepositoryDetail();
    hideSaveRepositoryDetail();
  }

  private void initializeHistoryDatabase() {
    mHistoryDatabase = new SearchHistoryTable(mContext);
    mHistoryDatabase.setHistorySize(10);
  }

  public void deleteSaveRepositories() {
    Subscription subscription = mDataManager.deleteAllRepositoriesDb()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(repositoryDetailDeleteResults -> {
          mRxBus.post(new RxBusHelper.DeleteAllRepositoriesDb());
        }, throwable -> {
          Timber.e(throwable.toString());
        });
    unsubscribeOnDestroy(subscription);
  }

  public void deleteHistoryDatabase() {
    mHistoryDatabase.clearDatabase();
    getViewState().setEmptyListSearchHistory();
  }

  public void startDownloadRepositories(String query) {
    mRxBus.post(new RxBusHelper.StartDownloadRepository(query));
    mHistoryDatabase.addItem(new SearchItem(query));
    getViewState().selectTab();
    getViewState().hideRepositoryDetail();
  }

  public void addRepository(RepositoryDetail repositoryDetail) {
    mRxBus.post(new RxBusHelper.AddRepositoryDb(repositoryDetail));
    getViewState().saveRepository();
  }

  public void deleteRepository(RepositoryDetail repositoryDetail) {
    mRxBus.post(new RxBusHelper.DeleteRepositoryDb(repositoryDetail));
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

  private void hideSaveRepositoryDetail() {
    Subscription subscription =
        mRxBus.filteredObservable(RxBusHelper.HideSaveRepositoryDetail.class)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(hideSaveRepositoryDetail -> {
              getViewState().hideRepositoryDetail();
            }, throwable -> {
              Timber.e(throwable.toString());
            });
    unsubscribeOnDestroy(subscription);
  }

  private void showRepositoryDetail() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.ShowRepositoryDetail.class)
        .filter(showRepositoryDetail -> !idRepository.equals(
            showRepositoryDetail.mRepositoryDetail.getId()))
        .doOnNext(showRepositoryDetail -> getViewState().hideRepositoryDetail())
        .delay(500, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(showRepositoryDetail -> {
          getViewState().showRepositoryDetail(showRepositoryDetail.mRepositoryDetail);
          idRepository = showRepositoryDetail.mRepositoryDetail.getId();
        }, throwable -> {
          Timber.e(throwable.toString());
        });
    unsubscribeOnDestroy(subscription);
  }

  public void resetIdRepository() {
    idRepository = 0L;
  }
}
