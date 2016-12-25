package com.alexeymorozua.codesample.mvp.data.local;

import com.alexeymorozua.codesample.CodeSampleApp;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import javax.inject.Inject;

/**
 * Created by john on 25.12.2016.
 */

public class DatabaseHelper {

  @Inject StorIOSQLite mStorIOSQLite;

  public DatabaseHelper() {
    CodeSampleApp.getAppComponent().inject(this);
  }
}
