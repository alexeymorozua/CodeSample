package com.alexeymorozua.codesample.util;

import com.alexeymorozua.codesample.mvp.data.model.repository.Repository;

/**
 * Created by john on 06.12.2016.
 */

public final class BusHelper {

  public static class ShowRepositoryDetail {
    public Repository mRepository;

    public ShowRepositoryDetail(Repository repository) {
      mRepository = repository;
    }
  }

  public static class StartDownloadRepository {
    public String query;

    public StartDownloadRepository(String query) {
      this.query = query;
    }
  }
}
