package com.alexeymorozua.codesample.injection.modules;

import android.app.Application;
import android.content.Context;
import com.alexeymorozua.codesample.injection.scopes.PerApplication;
import com.alexeymorozua.codesample.mvp.data.local.PreferencesHelper;
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

  @Provides @PerApplication PreferencesHelper provideSharedPreferences() {
    return new PreferencesHelper(mApplication);
  }
}
