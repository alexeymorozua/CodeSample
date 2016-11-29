package com.alexeymorozua.codesample.injection.modules;

import com.alexeymorozua.codesample.injection.scopes.PerApplication;
import com.alexeymorozua.codesample.mvp.data.remote.GithubApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by john on 29.11.2016.
 */

@Module(includes = { RetrofitModule.class }) public class ApiModule {
  @Provides @PerApplication public GithubApi provideAuthApi(Retrofit retrofit) {
    return retrofit.create(GithubApi.class);
  }
}
