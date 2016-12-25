package com.alexeymorozua.codesample.injection.modules;

import android.content.Context;
import com.alexeymorozua.codesample.injection.scopes.PerApplication;
import com.alexeymorozua.codesample.mvp.data.local.DatabaseHelper;
import com.alexeymorozua.codesample.mvp.data.local.PreferencesHelper;
import com.alexeymorozua.codesample.mvp.data.remote.GithubApi;
import com.alexeymorozua.codesample.mvp.data.remote.GithubService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by john on 23.12.2016.
 */

@Module(includes = { ApiModule.class, DbModule.class }) public class DataManagerModule {

  @Provides @PerApplication public PreferencesHelper provideSharedPreferences(Context context) {
    return new PreferencesHelper(context);
  }

  @Provides @PerApplication public GithubService provideGithubService(GithubApi githubApi) {
    return new GithubService(githubApi);
  }

  @Provides @PerApplication public DatabaseHelper provideDatabaseHelper() {
    return new DatabaseHelper();
  }
}
