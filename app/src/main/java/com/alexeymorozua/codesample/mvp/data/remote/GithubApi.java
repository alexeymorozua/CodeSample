package com.alexeymorozua.codesample.mvp.data.remote;

import com.alexeymorozua.codesample.mvp.data.model.repository.SearchRepository;
import com.alexeymorozua.codesample.mvp.data.model.user.User;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by john on 29.11.2016.
 */

public interface GithubApi {

  Integer PAGE_SIZE = 20;

  @GET("/user") Observable<User> signIn(@Header("Authorization") String token);

  @GET("/search/repositories?sort=stars&order=desc")
  Observable<SearchRepository> getSearchRepositories(@Query("q") String query,
      @Query("page") int page, @Query("per_page") int pageSize);
}
