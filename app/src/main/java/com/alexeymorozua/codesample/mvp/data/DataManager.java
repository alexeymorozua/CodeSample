package com.alexeymorozua.codesample.mvp.data;

import android.util.Base64;
import com.alexeymorozua.codesample.CodeSampleApp;
import com.alexeymorozua.codesample.mvp.data.local.PreferencesHelper;
import com.alexeymorozua.codesample.mvp.data.model.dto.UserDTO;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.alexeymorozua.codesample.mvp.data.model.vo.SearchRepository;
import com.alexeymorozua.codesample.mvp.data.remote.GithubApi;
import com.alexeymorozua.codesample.mvp.data.remote.GithubService;
import com.alexeymorozua.codesample.util.PageLinksUtil;
import java.text.Format;
import java.text.SimpleDateFormat;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by john on 23.12.2016.
 */

public class DataManager {

  @Inject GithubService mGithubService;
  @Inject PreferencesHelper mPreferencesHelper;

  public DataManager() {
    CodeSampleApp.getAppComponent().inject(this);
  }

  public void clearPreferences() {
    mPreferencesHelper.clear();
  }

  public Observable<String> getToken() {
    return Observable.just(mPreferencesHelper.getToken());
  }

  public Observable<UserDTO> setToken(String email, String password) {
    String credentials = String.format("%s:%s", email, password);
    final String token = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    return mGithubService.signIn(token).doOnNext(user -> mPreferencesHelper.setToken(token));
  }

  public Observable<SearchRepository> getSearchRepository(String query, int page) {
    SearchRepository repository = new SearchRepository();
    return mGithubService.getSearchRepositories(query, page, GithubApi.PAGE_SIZE)
        .doOnNext(searchRepositoryDTOResponse -> repository.setTotalPages(
            PageLinksUtil.getTotalPages(searchRepositoryDTOResponse.headers().get("Link"))))
        .concatMap(searchRepositoryDTOResponse -> Observable.from(
            searchRepositoryDTOResponse.body().getRepositories()))
        .map(repositoryDTO -> {
          Format formatter = new SimpleDateFormat("MM.dd.yyyy", java.util.Locale.getDefault());
          String date = formatter.format(repositoryDTO.getUpdatedAt());
          return new RepositoryDetail(repositoryDTO.getName(), repositoryDTO.getFullName(),
              repositoryDTO.getDescription(), repositoryDTO.getLanguage(),
              repositoryDTO.getStargazersCount(), date, repositoryDTO.getHtmlUrl(),
              repositoryDTO.getOwnerDTO().getAvatarUrl(), repositoryDTO.getOwnerDTO().getLogin());
        })
        .toList()
        .map(repositoryDetails -> {
          repository.setRepositoryDetails(repositoryDetails);
          return repository;
        });
  }
}
