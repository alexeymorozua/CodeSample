package com.alexeymorozua.codesample.mvp.data.remote;

import com.alexeymorozua.codesample.mvp.data.model.User;
import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by john on 29.11.2016.
 */

public interface GithubApi {

  Integer PAGE_SIZE = 50;

  @GET("/user") Observable<User> signIn(@Header("Authorization") String token);
}
