package com.alexeymorozua.codesample.mvp.data.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchRepositoryDTO {

  @SerializedName("total_count") @Expose private int totalCount;
  @SerializedName("incomplete_results") @Expose private boolean incompleteResults;
  @SerializedName("items") @Expose private List<RepositoryDTO> repositories = null;

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
  public List<RepositoryDTO> getRepositories() {
    return repositories;
  }

  /**
   * @param repositories The repositories
   */
  public void setRepositories(List<RepositoryDTO> repositories) {
    this.repositories = repositories;
  }
}
