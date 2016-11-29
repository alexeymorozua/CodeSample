package com.alexeymorozua.codesample.mvp.data.model;

/**
 * Created by john on 29.11.2016.
 */

public class User {

  private int mId;
  private String mLogin;
  private String mAvatarUrl;
  private int mPublicRepos;
  private int mPublicGists;
  private int mFollower;
  private int mFollowing;

  public int getId() {
    return mId;
  }

  public void setId(int id) {
    mId = id;
  }

  public String getLogin() {
    return mLogin;
  }

  public void setLogin(String login) {
    mLogin = login;
  }

  public String getAvatarUrl() {
    return mAvatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    mAvatarUrl = avatarUrl;
  }

  public int getPublicRepos() {
    return mPublicRepos;
  }

  public void setPublicRepos(int publicRepos) {
    mPublicRepos = publicRepos;
  }

  public int getPublicGists() {
    return mPublicGists;
  }

  public void setPublicGists(int publicGists) {
    mPublicGists = publicGists;
  }

  public int getFollower() {
    return mFollower;
  }

  public void setFollower(int follower) {
    mFollower = follower;
  }

  public int getFollowing() {
    return mFollowing;
  }

  public void setFollowing(int following) {
    mFollowing = following;
  }
}
