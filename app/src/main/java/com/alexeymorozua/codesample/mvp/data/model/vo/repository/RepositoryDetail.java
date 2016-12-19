package com.alexeymorozua.codesample.mvp.data.model.vo.repository;

/**
 * Created by john on 19.12.2016.
 */

public class RepositoryDetail {

  private String name;
  private String fullName;
  private String description;
  private String language;
  private int stargazersCount;
  private String updatedAt;
  private String htmlUrl;
  private String avatarUrl;
  private String login;

  public RepositoryDetail(String name, String fullName, String description, String language,
      int stargazersCount, String updatedAt, String htmlUrl, String avatarUrl, String login) {
    this.name = name;
    this.fullName = fullName;
    this.description = description;
    this.language = language;
    this.stargazersCount = stargazersCount;
    this.updatedAt = updatedAt;
    this.htmlUrl = htmlUrl;
    this.avatarUrl = avatarUrl;
    this.login = login;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public int getStargazersCount() {
    return stargazersCount;
  }

  public void setStargazersCount(int stargazersCount) {
    this.stargazersCount = stargazersCount;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }
}
