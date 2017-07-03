package com.alexeymorozua.codesample.mvp.data.model.vo;

import com.alexeymorozua.codesample.mvp.data.local.RepositoriesDetailTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by john on 19.12.2016.
 */

@StorIOSQLiteType(table = RepositoriesDetailTable.TABLE) public class RepositoryDetail {

  @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_ID, key = true) Long id;

  @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_NAME) String name;

  @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_FULL_NAME) String fullName;

  @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_DESCRIPTION) String description;

  @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_LANGUAGE) String language;

  @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_STARGAZERS_COUNT) int stargazersCount;

  @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_UPDATE_AT) String updatedAt;

  @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_HTML_URL) String htmlUrl;

  @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_AVATAR_URL) String avatarUrl;

  @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_LOGIN) String login;

  @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_SAVE) boolean save;

  private RepositoryDetail(Long id, String name, String fullName, String description,
      String language, int stargazersCount, String updatedAt, String htmlUrl, String avatarUrl,
      String login) {
    this.id = id;
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

  public RepositoryDetail() {
  }

  public static RepositoryDetail newRepositoryDetail(Long id, String name, String fullName,
      String description, String language, int stargazersCount, String updatedAt, String htmlUrl,
      String avatarUrl, String login) {
    return new RepositoryDetail(id, name, fullName, description, language, stargazersCount,
        updatedAt, htmlUrl, avatarUrl, login);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public boolean isSave() {
    return save;
  }

  public void setSave(boolean save) {
    this.save = save;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RepositoryDetail that = (RepositoryDetail) o;

    if (stargazersCount != that.stargazersCount) return false;
    if (save != that.save) return false;
    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
    if (description != null ? !description.equals(that.description) : that.description != null) {
      return false;
    }
    if (language != null ? !language.equals(that.language) : that.language != null) return false;
    if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) {
      return false;
    }
    if (htmlUrl != null ? !htmlUrl.equals(that.htmlUrl) : that.htmlUrl != null) return false;
    if (avatarUrl != null ? !avatarUrl.equals(that.avatarUrl) : that.avatarUrl != null) {
      return false;
    }
    return login != null ? login.equals(that.login) : that.login == null;
  }

  @Override public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (language != null ? language.hashCode() : 0);
    result = 31 * result + stargazersCount;
    result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
    result = 31 * result + (htmlUrl != null ? htmlUrl.hashCode() : 0);
    result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
    result = 31 * result + (login != null ? login.hashCode() : 0);
    result = 31 * result + (save ? 1 : 0);
    return result;
  }
}
