package com.alexeymorozua.codesample.injection.modules;

import android.app.Application;
import android.content.Context;
import com.alexeymorozua.codesample.injection.scopes.PerApplication;
import com.alexeymorozua.codesample.mvp.data.DataManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by john on 25.11.2016.
 */

@Module public class AppModule {

  private final Application mApplication;

  public AppModule(Application application) {
    mApplication = application;
  }

  @Provides @PerApplication Context provideAppContext() {
    return mApplication;
  }

  @Provides @PerApplication DataManager provideDataManager() {
    return new DataManager();
  }
}
