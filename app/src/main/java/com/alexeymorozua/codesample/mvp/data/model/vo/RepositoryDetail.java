package com.alexeymorozua.codesample.mvp.data.model.vo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.alexeymorozua.codesample.mvp.data.local.RepositoriesDetailTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by john on 19.12.2016.
 */

@StorIOSQLiteType(table = RepositoriesDetailTable.TABLE)
public class RepositoryDetail {

  @Nullable @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_ID, key = true) Long id;

  @NonNull @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_NAME) String name;

  @NonNull @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_FULL_NAME) String fullName;

  @NonNull @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_DESCRIPTION) String
      description;

  @NonNull @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_LANGUAGE) String language;

  @NonNull @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_STARGAZERS_COUNT) int
      stargazersCount;

  @NonNull @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_UPDATE_AT) String updatedAt;

  @NonNull @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_HTML_URL) String htmlUrl;

  @NonNull @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_AVATAR_URL) String avatarUrl;

  @NonNull @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_LOGIN) String login;

  @NonNull @StorIOSQLiteColumn(name = RepositoriesDetailTable.COLUMN_SAVE) boolean save;

  private RepositoryDetail(@Nullable Long id, @NonNull String name, @NonNull String fullName,
      @NonNull String description, @NonNull String language, @NonNull int stargazersCount,
      @NonNull String updatedAt, @NonNull String htmlUrl, @NonNull String avatarUrl,
      @NonNull String login) {
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

  @NonNull
  public static RepositoryDetail newRepositoryDetail(@Nullable Long id, @NonNull String name,
      @NonNull String fullName, @NonNull String description, @NonNull String language,
      @NonNull int stargazersCount, @NonNull String updatedAt, @NonNull String htmlUrl,
      @NonNull String avatarUrl, @NonNull String login) {
    return new RepositoryDetail(id, name, fullName, description, language, stargazersCount,
        updatedAt, htmlUrl, avatarUrl, login);
  }

  @NonNull public boolean isSave() {
    return save;
  }

  public void setSave(@NonNull boolean save) {
    this.save = save;
  }

  @Nullable public Long getId() {
    return id;
  }

  public void setId(@Nullable Long id) {
    this.id = id;
  }

  @NonNull public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  @NonNull public String getFullName() {
    return fullName;
  }

  public void setFullName(@NonNull String fullName) {
    this.fullName = fullName;
  }

  @NonNull public String getDescription() {
    return description;
  }

  public void setDescription(@NonNull String description) {
    this.description = description;
  }

  @NonNull public String getLanguage() {
    return language;
  }

  public void setLanguage(@NonNull String language) {
    this.language = language;
  }

  @NonNull public int getStargazersCount() {
    return stargazersCount;
  }

  public void setStargazersCount(@NonNull int stargazersCount) {
    this.stargazersCount = stargazersCount;
  }

  @NonNull public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(@NonNull String updatedAt) {
    this.updatedAt = updatedAt;
  }

  @NonNull public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(@NonNull String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  @NonNull public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(@NonNull String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  @NonNull public String getLogin() {
    return login;
  }

  public void setLogin(@NonNull String login) {
    this.login = login;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RepositoryDetail that = (RepositoryDetail) o;

    if (stargazersCount != that.stargazersCount) return false;
    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (!name.equals(that.name)) return false;
    if (!fullName.equals(that.fullName)) return false;
    if (!description.equals(that.description)) return false;
    if (!language.equals(that.language)) return false;
    if (!updatedAt.equals(that.updatedAt)) return false;
    if (!htmlUrl.equals(that.htmlUrl)) return false;
    if (!avatarUrl.equals(that.avatarUrl)) return false;
    return login.equals(that.login);
  }

  @Override public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + name.hashCode();
    result = 31 * result + fullName.hashCode();
    result = 31 * result + description.hashCode();
    result = 31 * result + language.hashCode();
    result = 31 * result + stargazersCount;
    result = 31 * result + updatedAt.hashCode();
    result = 31 * result + htmlUrl.hashCode();
    result = 31 * result + avatarUrl.hashCode();
    result = 31 * result + login.hashCode();
    return result;
  }
}
