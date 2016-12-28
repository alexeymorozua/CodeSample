package com.alexeymorozua.codesample.mvp.data.local;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResult;
import com.pushtorefresh.storio.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio.sqlite.queries.Query;
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

  public Observable<PutResult> addRepository(RepositoryDetail repositoryDetail) {
    return mStorIOSQLite.put().object(repositoryDetail).prepare().asRxObservable();
  }

  public Observable<List<RepositoryDetail>> getAllRepositoriesDb() {
    return mStorIOSQLite.get()
        .listOfObjects(RepositoryDetail.class)
        .withQuery(RepositoriesDetailTable.QUERY_ALL)
        .prepare()
        .asRxObservable()
        .take(1);
  }

  public Observable<RepositoryDetail> getRepositoryDb(Long id) {
    return mStorIOSQLite.get()
        .object(RepositoryDetail.class)
        .withQuery(Query.builder()
            .table(RepositoriesDetailTable.TABLE)
            .where(RepositoriesDetailTable.COLUMN_ID + "=?")
            .whereArgs(id)
            .build())
        .prepare()
        .asRxObservable()
        .take(1);
  }

  public Observable<DeleteResult> deleteRepositoryDb(RepositoryDetail repositoryDetail) {
    return mStorIOSQLite.delete().object(repositoryDetail).prepare().asRxObservable();
  }
}
