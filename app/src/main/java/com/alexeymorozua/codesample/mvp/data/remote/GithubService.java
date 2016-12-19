package com.alexeymorozua.codesample.mvp.data.remote;

import com.alexeymorozua.codesample.mvp.data.model.dto.repository.SearchRepositoryDTO;
import com.alexeymorozua.codesample.mvp.data.model.dto.user.UserDTO;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by john on 29.11.2016.
 */

public class GithubService {

  private GithubApi mGithubApi;

  public GithubService(GithubApi githubApi) {
    mGithubApi = githubApi;
  }

  public Observable<UserDTO> signIn(String token) {
    return mGithubApi.signIn(token);
  }

  public Observable<Response<SearchRepositoryDTO>> getSearchRepositories(String query, int page,
      int pageSize) {
    return mGithubApi.getSearchRepositories(query, page, pageSize);
  }
}
