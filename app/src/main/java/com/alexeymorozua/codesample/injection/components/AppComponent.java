package com.alexeymorozua.codesample.injection.components;

import com.alexeymorozua.codesample.injection.modules.AppModule;
import com.alexeymorozua.codesample.injection.scopes.PerApplication;
import com.alexeymorozua.codesample.mvp.presenters.SignInPresenter;
import com.alexeymorozua.codesample.mvp.presenters.SplashPresenter;
import dagger.Component;

/**
 * Created by john on 25.11.2016.
 */

@PerApplication @Component(modules = AppModule.class) public interface AppComponent {

  void inject(SignInPresenter presenter);

  void inject(SplashPresenter presenter);
}
