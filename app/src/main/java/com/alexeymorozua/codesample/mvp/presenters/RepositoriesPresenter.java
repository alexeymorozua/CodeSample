package com.alexeymorozua.codesample.mvp.presenters;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.model.vo.repository.RepositoryDetail;
import com.alexeymorozua.codesample.mvp.data.remote.GithubApi;
import com.alexeymorozua.codesample.mvp.data.remote.GithubService;
import com.alexeymorozua.codesample.mvp.views.RepositoriesView;
import com.alexeymorozua.codesample.util.BusHelper;
import com.alexeymorozua.codesample.util.PageLinksUtil;
import com.arellomobile.mvp.InjectViewState;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.text.Format;
import java.text.SimpleDateFormat;
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
  @Inject Bus mBus;

  private String mQuery;
  private int mTotalPages;

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

    Observable<List<RepositoryDetail>> observable =
        mGithubService.getSearchRepositories(query, page, GithubApi.PAGE_SIZE)
            .doOnNext(searchRepositoryDTOResponse -> {
              mTotalPages =
                  PageLinksUtil.getTotalPages(searchRepositoryDTOResponse.headers().get("Link"));
            })
            .flatMap(searchRepositoryDTOResponse -> Observable.from(
                searchRepositoryDTOResponse.body().getRepositories()))
            .map(repositoryDTO -> {
              Format formatter = new SimpleDateFormat("MM.dd.yyyy", java.util.Locale.getDefault());
              String date = formatter.format(repositoryDTO.getUpdatedAt());
              return new RepositoryDetail(repositoryDTO.getName(), repositoryDTO.getFullName(),
                  repositoryDTO.getDescription(), repositoryDTO.getLanguage(),
                  repositoryDTO.getStargazersCount(), date, repositoryDTO.getHtmlUrl(),
                  repositoryDTO.getOwnerDTO().getAvatarUrl(),
                  repositoryDTO.getOwnerDTO().getLogin());
            })
            .toList();

    Subscription subscription =
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(repositories -> {
          getViewState().onFinishLoading();
          onLoadingSuccess(isPageLoading, repositories);
        }, error -> {
          getViewState().onFinishLoading();
          getViewState().showError(error.toString());
          Timber.e(error.toString());
        });
    unsubscribeOnDestroy(subscription);
  }

  private void onLoadingSuccess(boolean isPageLoading, List<RepositoryDetail> repositories) {

    if (isPageLoading) {
      getViewState().addRepositories(repositories);
    } else {
      getViewState().setTotalPages(mTotalPages);
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
