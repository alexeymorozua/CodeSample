package com.alexeymorozua.codesample.mvp.data.remote;

import com.alexeymorozua.codesample.mvp.data.model.repository.SearchRepository;
import com.alexeymorozua.codesample.mvp.data.model.user.User;
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

  public Observable<SearchRepository> getSearchRepositories(String query, int page, int pageSize) {
    return mGithubApi.getSearchRepositories(query, page, pageSize);
  }
}
