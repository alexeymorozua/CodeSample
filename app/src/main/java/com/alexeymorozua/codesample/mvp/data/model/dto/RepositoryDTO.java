package com.alexeymorozua.codesample.mvp.data.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class RepositoryDTO {

  @SerializedName("id") @Expose private int id;
  @SerializedName("name") @Expose private String name;
  @SerializedName("full_name") @Expose private String fullName;
  @SerializedName("owner") @Expose private OwnerDTO mOwnerDTO;
  @SerializedName("private") @Expose private boolean _private;
  @SerializedName("html_url") @Expose private String htmlUrl;
  @SerializedName("description") @Expose private String description;
  @SerializedName("fork") @Expose private boolean fork;
  @SerializedName("url") @Expose private String url;
  @SerializedName("created_at") @Expose private Date createdAt;
  @SerializedName("updated_at") @Expose private Date updatedAt;
  @SerializedName("pushed_at") @Expose private Date pushedAt;
  @SerializedName("homepage") @Expose private String homepage;
  @SerializedName("size") @Expose private int size;
  @SerializedName("stargazers_count") @Expose private int stargazersCount;
  @SerializedName("watchers_count") @Expose private int watchersCount;
  @SerializedName("language") @Expose private String language;
  @SerializedName("forks_count") @Expose private int forksCount;
  @SerializedName("open_issues_count") @Expose private int openIssuesCount;
  @SerializedName("master_branch") @Expose private String masterBranch;
  @SerializedName("default_branch") @Expose private String defaultBranch;
  @SerializedName("score") @Expose private float score;

  /**
   * @return The id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id The id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return The name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name The name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return The fullName
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * @param fullName The full_name
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   * @return The mOwnerDTO
   */
  public OwnerDTO getOwnerDTO() {
    return mOwnerDTO;
  }

  /**
   * @param ownerDTO The mOwnerDTO
   */
  public void setOwnerDTO(OwnerDTO ownerDTO) {
    this.mOwnerDTO = ownerDTO;
  }

  /**
   * @return The _private
   */
  public boolean isPrivate() {
    return _private;
  }

  /**
   * @param _private The private
   */
  public void setPrivate(boolean _private) {
    this._private = _private;
  }

  /**
   * @return The htmlUrl
   */
  public String getHtmlUrl() {
    return htmlUrl;
  }

  /**
   * @param htmlUrl The html_url
   */
  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  /**
   * @return The description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description The description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return The fork
   */
  public boolean isFork() {
    return fork;
  }

  /**
   * @param fork The fork
   */
  public void setFork(boolean fork) {
    this.fork = fork;
  }

  /**
   * @return The url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url The url
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return The createdAt
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * @param createdAt The created_at
   */
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * @return The updatedAt
   */
  public Date getUpdatedAt() {
    return updatedAt;
  }

  /**
   * @param updatedAt The updated_at
   */
  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  /**
   * @return The pushedAt
   */
  public Date getPushedAt() {
    return pushedAt;
  }

  /**
   * @param pushedAt The pushed_at
   */
  public void setPushedAt(Date pushedAt) {
    this.pushedAt = pushedAt;
  }

  /**
   * @return The homepage
   */
  public String getHomepage() {
    return homepage;
  }

  /**
   * @param homepage The homepage
   */
  public void setHomepage(String homepage) {
    this.homepage = homepage;
  }

  /**
   * @return The size
   */
  public int getSize() {
    return size;
  }

  /**
   * @param size The size
   */
  public void setSize(int size) {
    this.size = size;
  }

  /**
   * @return The stargazersCount
   */
  public int getStargazersCount() {
    return stargazersCount;
  }

  /**
   * @param stargazersCount The stargazers_count
   */
  public void setStargazersCount(int stargazersCount) {
    this.stargazersCount = stargazersCount;
  }

  /**
   * @return The watchersCount
   */
  public int getWatchersCount() {
    return watchersCount;
  }

  /**
   * @param watchersCount The watchers_count
   */
  public void setWatchersCount(int watchersCount) {
    this.watchersCount = watchersCount;
  }

  /**
   * @return The language
   */
  public String getLanguage() {
    return language;
  }

  /**
   * @param language The language
   */
  public void setLanguage(String language) {
    this.language = language;
  }

  /**
   * @return The forksCount
   */
  public int getForksCount() {
    return forksCount;
  }

  /**
   * @param forksCount The forks_count
   */
  public void setForksCount(int forksCount) {
    this.forksCount = forksCount;
  }

  /**
   * @return The openIssuesCount
   */
  public int getOpenIssuesCount() {
    return openIssuesCount;
  }

  /**
   * @param openIssuesCount The open_issues_count
   */
  public void setOpenIssuesCount(int openIssuesCount) {
    this.openIssuesCount = openIssuesCount;
  }

  /**
   * @return The masterBranch
   */
  public String getMasterBranch() {
    return masterBranch;
  }

  /**
   * @param masterBranch The master_branch
   */
  public void setMasterBranch(String masterBranch) {
    this.masterBranch = masterBranch;
  }

  /**
   * @return The defaultBranch
   */
  public String getDefaultBranch() {
    return defaultBranch;
  }

  /**
   * @param defaultBranch The default_branch
   */
  public void setDefaultBranch(String defaultBranch) {
    this.defaultBranch = defaultBranch;
  }

  /**
   * @return The score
   */
  public float getScore() {
    return score;
  }

  /**
   * @param score The score
   */
  public void setScore(float score) {
    this.score = score;
  }
}
