package com.alexeymorozua.codesample.injection.modules;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import com.alexeymorozua.codesample.injection.scopes.PerApplication;
import com.alexeymorozua.codesample.mvp.data.local.DbOpenHelper;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetailSQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import dagger.Module;
import dagger.Provides;

/**
 * Created by john on 25.12.2016.
 */

@Module public class DbModule {

  @Provides @PerApplication public SQLiteOpenHelper provideSQLiteOpenHelper(Context context) {
    return new DbOpenHelper(context);
  }

  @Provides @PerApplication
  public StorIOSQLite provideStorIOSQLite(SQLiteOpenHelper sqLiteOpenHelper) {
    return DefaultStorIOSQLite.builder()
        .sqliteOpenHelper(sqLiteOpenHelper)
        .addTypeMapping(RepositoryDetail.class, new RepositoryDetailSQLiteTypeMapping())
        .build();
  }
}
