package com.alexeymorozua.codesample.mvp.presenters;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.DataManager;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.alexeymorozua.codesample.mvp.views.RepositoriesView;
import com.alexeymorozua.codesample.util.BusHelper;
import com.arellomobile.mvp.InjectViewState;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by john on 02.12.2016.
 */

@InjectViewState public class RepositoriesPresenter extends BasePresenter<RepositoriesView> {

  @Inject DataManager mDataManager;
  @Inject Bus mBus;

  private String mQuery;

  public RepositoriesPresenter() {
    CodeSampleApp.getAppComponent().inject(this);

    mBus.register(this);
  }

  public void showRepositoryDetail(RepositoryDetail repositoryDetail) {
    mBus.post(new BusHelper.ShowRepositoryDetail(repositoryDetail));
  }

  public void loadNextRepositories(int page) {
    loadData(page, true, mQuery);
  }

  private void loadRepositories(String query) {
    loadData(1, false, query);
  }

  private void loadData(int page, boolean isPageLoading, String query) {

    if (!isPageLoading) {
      getViewState().onStartLoading();
    }

    Subscription subscription = mDataManager.getSearchRepository(query, page)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(searchRepository -> {
          getViewState().onFinishLoading();
          onLoadingSuccess(isPageLoading, searchRepository.getRepositoryDetails(),
              searchRepository.getTotalPages());
        }, error -> {
          getViewState().onFinishLoading();
          getViewState().showError(error.toString());
          Timber.e(error.toString());
        });
    unsubscribeOnDestroy(subscription);
  }

  private void onLoadingSuccess(boolean isPageLoading, List<RepositoryDetail> repositories,
      int totalPages) {

    if (isPageLoading) {
      getViewState().addRepositories(repositories);
    } else {
      getViewState().setTotalPages(totalPages);
      getViewState().setRepositories(repositories);
    }
  }

  public void onErrorCancel() {
    getViewState().hideError();
  }

  @Subscribe
  public void downloadRepositories(BusHelper.StartDownloadRepository downloadRepository) {
    mQuery = downloadRepository.query;
    loadRepositories(mQuery);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mBus.unregister(this);
  }
}
