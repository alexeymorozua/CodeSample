package com.alexeymorozua.codesample;

import com.alexeymorozua.codesample.injection.components.AppComponent;
import com.alexeymorozua.codesample.injection.components.DaggerAppComponent;
import com.alexeymorozua.codesample.injection.modules.AppModule;
import com.arellomobile.mvp.MvpApplication;
import timber.log.Timber;

/**
 * Created by john on 24.11.2016.
 */

public class CodeSampleApp extends MvpApplication {

  private static AppComponent sAppComponent;

  public static AppComponent getAppComponent() {
    return sAppComponent;
  }

  @Override public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }
}
