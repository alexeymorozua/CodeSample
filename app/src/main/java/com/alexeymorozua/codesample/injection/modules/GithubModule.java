package com.alexeymorozua.codesample.injection.modules;

import com.alexeymorozua.codesample.injection.scopes.PerApplication;
import com.alexeymorozua.codesample.mvp.data.remote.GithubApi;
import com.alexeymorozua.codesample.mvp.data.remote.GithubService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by john on 29.11.2016.
 */

@Module(includes = { ApiModule.class }) public class GithubModule {
  @Provides @PerApplication public GithubService provideGithubService(GithubApi githubApi) {
    return new GithubService(githubApi);
  }
}
