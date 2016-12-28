package com.alexeymorozua.codesample.mvp.presenters;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.DataManager;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.alexeymorozua.codesample.mvp.views.RepositoriesSaveView;
import com.alexeymorozua.codesample.util.BusHelper;
import com.arellomobile.mvp.InjectViewState;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
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

    mBus.register(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    addAllRepositories();
  }

  public void showRepositoryDetail(RepositoryDetail repositoryDetail) {
    mBus.post(new BusHelper.ShowRepositoryDetail(repositoryDetail));
  }

  private void addAllRepositories() {
    Subscription subscription = mDataManager.getAllRepositoriesDb()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(repositoryDetails -> {
          getViewState().addAllRepositories(repositoryDetails);
        }, throwable -> {
          Timber.e(throwable.toString());
        });
    unsubscribeOnDestroy(subscription);
  }

  @Subscribe public void addRepositoryDb(BusHelper.AddRepositoryDb mRepositoryDetail) {
    mRepositoryDetail.mRepositoryDetail.setSave(true);
    Subscription subscription = mDataManager.addRepositoryDb(mRepositoryDetail.mRepositoryDetail)
        .observeOn(AndroidSchedulers.mainThread()).subscribe(putResult -> {
          getViewState().addRepository(mRepositoryDetail.mRepositoryDetail);
        }, throwable -> {
          Timber.e(throwable.toString());
        });
    unsubscribeOnDestroy(subscription);
  }

  @Subscribe public void deleteRepositoryDb(BusHelper.DeleteRepositoryDb deleteRepositoryDb) {
    deleteRepositoryDb.mRepositoryDetail.setSave(false);
    Subscription subscription =
        mDataManager.deleteRepositoryDb(deleteRepositoryDb.mRepositoryDetail)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(deleteResult -> {
          mBus.post(new BusHelper.SyncRepositoryDb(true));
          mBus.post(new BusHelper.HideSaveRepositoryDetail(true));
          getViewState().deleteRepository(deleteRepositoryDb.mRepositoryDetail);
        }, throwable -> {
          Timber.e(throwable.toString());
        });
    unsubscribeOnDestroy(subscription);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mBus.unregister(this);
  }
}
