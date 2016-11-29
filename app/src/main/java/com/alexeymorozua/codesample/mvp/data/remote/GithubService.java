package com.alexeymorozua.codesample.mvp.data.remote;

import com.alexeymorozua.codesample.mvp.data.model.User;
import rx.Observable;

/**
 * Created by john on 29.11.2016.
 */

public class GithubService {

  private GithubApi mGithubApi;

  public GithubService(GithubApi githubApi) {
    mGithubApi = githubApi;
  }

  public Observable<User> signIn(String token) {
    return mGithubApi.signIn(token);
  }
}
