package com.alexeymorozua.codesample.mvp.presenters;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.DataManager;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.alexeymorozua.codesample.mvp.data.remote.GithubApi;
import com.alexeymorozua.codesample.mvp.views.RepositoriesView;
import com.alexeymorozua.codesample.util.RxBus;
import com.alexeymorozua.codesample.util.RxBusHelper;
import com.arellomobile.mvp.InjectViewState;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by john on 02.12.2016.
 */

@InjectViewState public class RepositoriesPresenter extends BasePresenter<RepositoriesView> {

  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  private String mQuery;
  private List<RepositoryDetail> mRepositoryDetailList = new ArrayList<>();
  private int mPage;

  public RepositoriesPresenter() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();

    downloadRepositories();
    syncRepositoryDb();
    pageLoading();
  }

  public void showRepositoryDetail(RepositoryDetail repositoryDetail) {
    mRxBus.post(new RxBusHelper.ShowRepositoryDetail(repositoryDetail));
  }

  private void loadNextRepositories(int page) {
    loadData(page, true, mQuery);
  }

  private void loadRepositories(String query) {
    mPage = 1;
    loadData(mPage, false, query);
  }

  private void loadData(int page, boolean isPageLoading, String query) {

    if (!isPageLoading) {
      getViewState().onStartLoading();
    }

    Subscription subscription = mDataManager.getSearchRepository(query, page)
        .observeOn(AndroidSchedulers.mainThread()).subscribe(repositories -> {
          getViewState().onFinishLoading();
          onLoadingSuccess(isPageLoading, repositories);
        }, throwable -> {
          getViewState().onFinishLoading();
          getViewState().showError(throwable.toString());
          Timber.e(throwable.toString());
        });
    unsubscribeOnDestroy(subscription);
  }

  private void onLoadingSuccess(boolean isPageLoading, List<RepositoryDetail> repositories) {

    boolean maybeMore = repositories.size() == GithubApi.PAGE_SIZE;

    if (isPageLoading) {
      getViewState().addRepositories(repositories, maybeMore);
    } else {
      getViewState().setRepositories(repositories, maybeMore);
      mRepositoryDetailList.clear();
    }

    mRepositoryDetailList.addAll(repositories);
  }

  public void onErrorCancel() {
    getViewState().hideError();
  }

  private void downloadRepositories() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.StartDownloadRepository.class)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(startDownloadRepository -> {
          mQuery = startDownloadRepository.query;
          loadRepositories(mQuery);
        }, throwable -> {
          Timber.e(throwable.toString());
        });
    unsubscribeOnDestroy(subscription);
  }

  private void syncRepositoryDb() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.SyncRepositoryDb.class)
        .concatMap(syncRepositoryDb -> Observable.from(mRepositoryDetailList))
        .concatMap(repositoryDetail -> mDataManager.syncRepositoryDb(repositoryDetail))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(repositoryDetail -> {
        }, throwable -> {
          Timber.e(throwable.toString());
        });
      unsubscribeOnDestroy(subscription);
  }

  private void pageLoading() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.PageRepositories.class)
        .subscribe(pageRepositories -> {
          mPage++;
          loadNextRepositories(mPage);
        }, throwable -> {
          Timber.e(throwable.toString());
        });
    unsubscribeOnDestroy(subscription);
  }
}
