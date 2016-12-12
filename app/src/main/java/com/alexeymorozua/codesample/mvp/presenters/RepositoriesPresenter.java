package com.alexeymorozua.codesample.mvp.presenters;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.model.repository.Repository;
import com.alexeymorozua.codesample.mvp.data.model.repository.SearchRepository;
import com.alexeymorozua.codesample.mvp.data.remote.GithubApi;
import com.alexeymorozua.codesample.mvp.data.remote.GithubService;
import com.alexeymorozua.codesample.mvp.views.RepositoriesView;
import com.alexeymorozua.codesample.util.BusHelper;
import com.alexeymorozua.codesample.util.PageLinksUtil;
import com.arellomobile.mvp.InjectViewState;
import com.squareup.otto.Bus;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by john on 02.12.2016.
 */

@InjectViewState public class RepositoriesPresenter extends BasePresenter<RepositoriesView> {

  @Inject GithubService mGithubService;
  @Inject Bus mBus;

  public RepositoriesPresenter() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    loadRepositories();
  }

  public void showRepositoryDetail(Repository repository) {
    mBus.post(new BusHelper.ShowRepositoryDetail(repository));
  }

  public void loadNextRepositories(int page) {
    loadData(page, true);
  }

  private void loadRepositories() {
    loadData(1, false);
  }

  private void loadData(int page, boolean isPageLoading) {

    if (!isPageLoading) {
      getViewState().onStartLoading();
    }

    Observable<SearchRepository> observable =
        mGithubService.getSearchRepositories("Android", page, GithubApi.PAGE_SIZE)
            .doOnNext(searchRepositoryResponse -> {
              int totalPages =
                  PageLinksUtil.getTotalPages(searchRepositoryResponse.headers().get("Link"));
              if (totalPages > 0) {
                getViewState().setTotalPages(totalPages);
              }
            })
            .map(Response::body);

    Subscription subscription =
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(repositories -> {
          getViewState().onFinishLoading();
          onLoadingSuccess(isPageLoading, repositories.getRepositories());
        }, error -> {
          getViewState().onFinishLoading();
          getViewState().showError(error.toString());
          Timber.e(error.toString());
        });
    unsubscribeOnDestroy(subscription);
  }

  private void onLoadingSuccess(boolean isPageLoading, List<Repository> repositories) {

    if (isPageLoading) {
      getViewState().addRepositories(repositories);
    } else {
      getViewState().setRepositories(repositories);
    }
  }

  public void onErrorCancel() {
    getViewState().hideError();
  }
}
