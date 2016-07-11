package ru.medyannikov.kotlintest.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TokenModel {

  @SerializedName("access_token")
  @Expose
  var accessToken: String? = null

  @SerializedName("refresh_token")
  @Expose
  var refreshToken: String? = null

  @SerializedName("expires_in")
  @Expose
  var expiresIn: Int? = null

  @SerializedName("token_type")
  @Expose
  var tokenType: String? = null


  constructor() {
  }

  constructor(accessToken: String, refreshToken: String, expiresIn: Int?, tokenType: String) {
    this.accessToken = accessToken
    this.refreshToken = refreshToken
    this.expiresIn = expiresIn
    this.tokenType = tokenType
  }
}

