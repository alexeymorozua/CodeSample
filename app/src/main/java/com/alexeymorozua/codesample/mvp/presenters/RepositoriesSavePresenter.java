package com.alexeymorozua.codesample.mvp.presenters;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.DataManager;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.alexeymorozua.codesample.mvp.views.RepositoriesSaveView;
import com.alexeymorozua.codesample.util.BusHelper;
import com.arellomobile.mvp.InjectViewState;
import com.squareup.otto.Bus;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by john on 26.12.2016.
 */

@InjectViewState public class RepositoriesSavePresenter
    extends BasePresenter<RepositoriesSaveView> {

  @Inject DataManager mDataManager;
  @Inject Bus mBus;

  public RepositoriesSavePresenter() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    addAllRepositories();
  }

  public void showRepositoryDetail(RepositoryDetail repositoryDetail) {
    mBus.post(new BusHelper.ShowRepositoryDetail(repositoryDetail));
  }

  private void addAllRepositories() {
    Subscription subscription = mDataManager.getAllRepositories()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(repositoryDetails -> {
          getViewState().addAllRepositories(repositoryDetails);
        }, throwable -> {
          Timber.e(throwable.toString());
        });
    unsubscribeOnDestroy(subscription);
  }
}
