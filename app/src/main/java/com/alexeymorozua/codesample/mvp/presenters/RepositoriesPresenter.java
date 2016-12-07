package com.alexeymorozua.codesample.mvp.presenters;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.model.repository.Repository;
import com.alexeymorozua.codesample.mvp.data.model.repository.SearchRepository;
import com.alexeymorozua.codesample.mvp.data.remote.GithubApi;
import com.alexeymorozua.codesample.mvp.data.remote.GithubService;
import com.alexeymorozua.codesample.mvp.views.RepositoriesView;
import com.arellomobile.mvp.InjectViewState;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by john on 02.12.2016.
 */

@InjectViewState public class RepositoriesPresenter extends BasePresenter<RepositoriesView> {

  @Inject GithubService mGithubService;

  public RepositoriesPresenter() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    loadRepositories();
  }

  public void loadNextRepositories(int currentCount) {
    int page = currentCount / GithubApi.PAGE_SIZE + 1;

    loadData(page, true);
  }

  private void loadRepositories() {
    loadData(1, false);
  }

  private void loadData(int page, boolean isPageLoading) {

    getViewState().onStartLoading();

    Observable<SearchRepository> observable =
        mGithubService.getSearchRepositories("Dagger 2 and Retrofit 2", page, GithubApi.PAGE_SIZE);

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

    boolean maybeMore = repositories.size() >= GithubApi.PAGE_SIZE;

    if (isPageLoading) {
      getViewState().addRepositories(repositories, maybeMore);
    } else {
      getViewState().setRepositories(repositories, maybeMore);
    }
  }

  public void onErrorCancel() {
    getViewState().hideError();
  }
}
