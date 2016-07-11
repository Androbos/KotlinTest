package ru.medyannikov.kotlintest.data.api

import retrofit.http.Field
import retrofit.http.FormUrlEncoded
import retrofit.http.GET
import retrofit.http.POST
import ru.medyannikov.kotlintest.data.model.Account
import ru.medyannikov.kotlintest.data.model.Bill
import ru.medyannikov.kotlintest.data.model.TokenModel
import rx.Observable

interface Api {
   companion object {
     const val BASE_URL = "http://mangyst-tog.ddns.net:3101/"
     const val BASE_URL_WORK = "http://mangyst-tog.ddns.net:3101/"
     const val GET_AUTH_TOKEN = "/oauth/token"
     const val TYPE_AUTH = "password"
     const val CLIENT_ID = "mobileV1"
     const val CLIENT_SECRET = "abc123456"
     const val GET_USER_MODEL = "/test"
     const val GET_MY_BILLS = "/mysql/bills"
   }

  @GET(GET_USER_MODEL)
   fun getUserModel(): Observable<Account>

  @FormUrlEncoded
  @POST(GET_AUTH_TOKEN)
  fun signIn(@Field("grant_type") grantType: String, @Field("client_id") clientId: String,
      @Field("client_secret") clientSecret: String, @Field("username") username: String,
      @Field("password") password: String) : Observable<TokenModel>

  @GET(GET_MY_BILLS)
  fun getBills() : Observable<List<Bill>>

}

