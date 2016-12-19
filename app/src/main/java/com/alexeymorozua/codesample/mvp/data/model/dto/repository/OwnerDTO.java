package com.alexeymorozua.codesample.mvp.data.model.dto.repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwnerDTO {

  @SerializedName("login") @Expose private String login;
  @SerializedName("id") @Expose private int id;
  @SerializedName("avatar_url") @Expose private String avatarUrl;
  @SerializedName("gravatar_id") @Expose private String gravatarId;
  @SerializedName("url") @Expose private String url;
  @SerializedName("received_events_url") @Expose private String receivedEventsUrl;
  @SerializedName("type") @Expose private String type;

  /**
   * @return The login
   */
  public String getLogin() {
    return login;
  }

  /**
   * @param login The login
   */
  public void setLogin(String login) {
    this.login = login;
  }

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
   * @return The avatarUrl
   */
  public String getAvatarUrl() {
    return avatarUrl;
  }

  /**
   * @param avatarUrl The avatar_url
   */
  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  /**
   * @return The gravatarId
   */
  public String getGravatarId() {
    return gravatarId;
  }

  /**
   * @param gravatarId The gravatar_id
   */
  public void setGravatarId(String gravatarId) {
    this.gravatarId = gravatarId;
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
   * @return The receivedEventsUrl
   */
  public String getReceivedEventsUrl() {
    return receivedEventsUrl;
  }

  /**
   * @param receivedEventsUrl The received_events_url
   */
  public void setReceivedEventsUrl(String receivedEventsUrl) {
    this.receivedEventsUrl = receivedEventsUrl;
  }

  /**
   * @return The type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type The type
   */
  public void setType(String type) {
    this.type = type;
  }
}
