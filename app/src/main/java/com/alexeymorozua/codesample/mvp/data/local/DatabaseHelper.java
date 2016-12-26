package com.alexeymorozua.codesample.mvp.data.local;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResult;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by john on 25.12.2016.
 */

public class DatabaseHelper {

  @Inject StorIOSQLite mStorIOSQLite;

  public DatabaseHelper() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  public Observable<PutResult> saveRepository(RepositoryDetail repositoryDetail) {
    return mStorIOSQLite.put().object(repositoryDetail).prepare().asRxObservable();
  }

  public Observable<List<RepositoryDetail>> getAllRepositories() {
    return mStorIOSQLite.get()
        .listOfObjects(RepositoryDetail.class)
        .withQuery(RepositoriesDetailTable.QUERY_ALL)
        .prepare()
        .asRxObservable()
        .take(1);
  }
}
