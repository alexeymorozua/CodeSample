package com.alexeymorozua.codesample.mvp.presenters;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.DataManager;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.alexeymorozua.codesample.mvp.views.RepositoriesSaveView;
import com.alexeymorozua.codesample.util.RxBus;
import com.alexeymorozua.codesample.util.RxBusHelper;
import com.arellomobile.mvp.InjectViewState;
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
  @Inject RxBus mRxBus;

  private RepositoryDetail mRepositoryDetail;

  public RepositoriesSavePresenter() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    addAllRepositories();

    addRepositoryDb();
    deleteRepositoryDb();
    deleteAllRepositoriesDb();
  }

  public void showRepositoryDetail(RepositoryDetail repositoryDetail) {
    mRxBus.post(new RxBusHelper.ShowRepositoryDetail(repositoryDetail));
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

  private void addRepositoryDb() {
    Subscription subscription =
        mRxBus.filteredObservable(RxBusHelper.AddRepositoryDb.class)
            .doOnNext(addRepositoryDb -> {
              addRepositoryDb.mRepositoryDetail.setSave(true);
              mRepositoryDetail = addRepositoryDb.mRepositoryDetail;
            })
            .concatMap(
                addRepositoryDb -> mDataManager.addRepositoryDb(addRepositoryDb.mRepositoryDetail))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(putResult -> {
              getViewState().addRepository(mRepositoryDetail);
            }, throwable -> {
              Timber.e(throwable.toString());
            });
    unsubscribeOnDestroy(subscription);
  }

  private void deleteRepositoryDb() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.DeleteRepositoryDb.class)
        .doOnNext(deleteRepositoryDb -> mRepositoryDetail = deleteRepositoryDb.mRepositoryDetail)
        .concatMap(deleteRepositoryDb -> mDataManager.deleteRepositoryDb(
            deleteRepositoryDb.mRepositoryDetail))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(deleteResult -> {
          mRxBus.post(new RxBusHelper.SyncRepositoryDb());
          mRxBus.post(new RxBusHelper.HideSaveRepositoryDetail());
          getViewState().deleteRepository(mRepositoryDetail);
        }, throwable -> {
          Timber.e(throwable.toString());
        });
    unsubscribeOnDestroy(subscription);
  }

  private void deleteAllRepositoriesDb() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.DeleteAllRepositoriesDb.class)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(deleteAllRepositoriesDb -> {
          getViewState().deleteAllSaveRepositories();
          mRxBus.post(new RxBusHelper.SyncRepositoryDb());
          mRxBus.post(new RxBusHelper.HideSaveRepositoryDetail());
        }, throwable -> {
          Timber.e(throwable.toString());
        });
    unsubscribeOnDestroy(subscription);
  }
}
