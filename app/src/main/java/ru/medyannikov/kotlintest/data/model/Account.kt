package ru.medyannikov.kotlintest.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Account : Serializable {

  @SerializedName("idAccount")
  @Expose
  var idAccount: Long = 0


  @SerializedName("firstName")
  @Expose
  var firstName: String? = null


  @SerializedName("lastName")
  @Expose
  var lastName: String? = null


  @SerializedName("thirdName")
  @Expose
  var thirdName: String? = null


  @SerializedName("email")
  @Expose
  var email: String? = null


  @SerializedName("token")
  @Expose
  var token: String? = null


  @SerializedName("urlImage")
  @Expose
  var urlImage: String? = null

  @SerializedName("urlImageThumb")
  @Expose
  var urlImageThumb: String? = null

  @SerializedName("urlVk")
  @Expose
  var urlVk: String? = null

  @SerializedName("status")
  @Expose
  val status: Int? = 0

  @SerializedName("dateUpdate")
  @Expose
  var dateUpdate = Date()

  @SerializedName("phone")
  @Expose
  var phone: String? = null

  @SerializedName("about")
  @Expose
  var about: String? = null

  @Expose
  var isAuth: Boolean = false

}