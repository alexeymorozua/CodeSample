package com.alexeymorozua.codesample.util;

import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;

/**
 * Created by john on 06.12.2016.
 */

public final class RxBusHelper {

  public static class ShowRepositoryDetail {
    public RepositoryDetail mRepositoryDetail;

    public ShowRepositoryDetail(RepositoryDetail repositoryDetail) {
      this.mRepositoryDetail = repositoryDetail;
    }
  }

  public static class HideSaveRepositoryDetail {
  }

  public static class StartDownloadRepository {
    public String query;

    public StartDownloadRepository(String query) {
      this.query = query;
    }
  }

  public static class AddRepositoryDb {
    public RepositoryDetail mRepositoryDetail;

    public AddRepositoryDb(RepositoryDetail repositoryDetail) {
      mRepositoryDetail = repositoryDetail;
    }
  }

  public static class DeleteRepositoryDb {
    public RepositoryDetail mRepositoryDetail;

    public DeleteRepositoryDb(RepositoryDetail repositoryDetail) {
      this.mRepositoryDetail = repositoryDetail;
    }
  }

  public static class SyncRepositoryDb {
  }

  public static class DeleteAllRepositoriesDb {
  }
}
