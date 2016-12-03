package com.alexeymorozua.codesample.mvp.data.model.repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchRepository {

  @SerializedName("total_count") @Expose private int totalCount;
  @SerializedName("incomplete_results") @Expose private boolean incompleteResults;
  @SerializedName("items") @Expose private List<Repository> repositories = null;

  /**
   * @return The totalCount
   */
  public int getTotalCount() {
    return totalCount;
  }

  /**
   * @param totalCount The total_count
   */
  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  /**
   * @return The incompleteResults
   */
  public boolean isIncompleteResults() {
    return incompleteResults;
  }

  /**
   * @param incompleteResults The incomplete_results
   */
  public void setIncompleteResults(boolean incompleteResults) {
    this.incompleteResults = incompleteResults;
  }

  /**
   * @return The repositories
   */
  public List<Repository> getRepositories() {
    return repositories;
  }

  /**
   * @param repositories The repositories
   */
  public void setRepositories(List<Repository> repositories) {
    this.repositories = repositories;
  }
}
