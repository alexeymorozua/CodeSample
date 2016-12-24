package com.alexeymorozua.codesample.mvp.data.model.vo;

import java.util.List;

/**
 * Created by john on 24.12.2016.
 */

public class SearchRepository {

  private List<RepositoryDetail> mRepositoryDetails;
  private int mTotalPages;

  public List<RepositoryDetail> getRepositoryDetails() {
    return mRepositoryDetails;
  }

  public void setRepositoryDetails(List<RepositoryDetail> repositoryDetails) {
    mRepositoryDetails = repositoryDetails;
  }

  public int getTotalPages() {
    return mTotalPages;
  }

  public void setTotalPages(int totalPages) {
    mTotalPages = totalPages;
  }
}
