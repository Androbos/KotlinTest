package ru.medyannikov.kotlintest.data.api

import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.jakewharton.retrofit.Ok3Client
import retrofit.RequestInterceptor
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response
import retrofit.converter.JacksonConverter
import ru.medyannikov.kotlintest.MainApplication
import ru.medyannikov.kotlintest.data.api.Api.Companion.BASE_URL_WORK
import ru.medyannikov.kotlintest.data.api.Api.Companion.CLIENT_ID
import ru.medyannikov.kotlintest.data.api.Api.Companion.CLIENT_SECRET
import ru.medyannikov.kotlintest.data.api.Api.Companion.TYPE_AUTH
import ru.medyannikov.kotlintest.data.model.Account
import ru.medyannikov.kotlintest.data.model.Bill
import ru.medyannikov.kotlintest.data.model.TokenModel
import ru.medyannikov.kotlintest.data.model.enums.Consts
import ru.medyannikov.kotlintest.data.model.wrappers.ErrorWrapper
import ru.medyannikov.kotlintest.data.model.wrappers.ResponseException
import ru.medyannikov.kotlintest.inBackground
import ru.medyannikov.kotlintest.utils.Prefs
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(val app: MainApplication, val prefs: Prefs, val mapper: ObjectMapper) {
  
  val api: Api = RestAdapter.Builder()
  .setEndpoint(BASE_URL_WORK)
  .setRequestInterceptor { it -> initInterceptor(app, prefs, it) }
  .setConverter(JacksonConverter(mapper))
      .setLogLevel(RestAdapter.LogLevel.FULL)
  .setClient(initClient())
  .build().create(Api::class.java)

  private fun  initClient(): Ok3Client {
    return Ok3Client()
  }

  private fun initInterceptor(app: MainApplication, prefs: Prefs, request: RequestInterceptor.RequestFacade?) {
    val token : String? = prefs.getString(Consts.AUTH_TOKEN)
    if (token != null) {
      request?.addHeader("Authorization", "Bearer " + token)
    }
  }

  fun getBills() : Observable<List<Bill>> {
     return inBackground(api.getBills().onErrorResumeNext { handleErrors(it)})
  }

  private fun saveBill(it: List<Bill>?): List<Bill>? {
    return it
  }

  fun signIn(login:String, password: String): Observable<TokenModel> {
    return inBackground(api.signIn(TYPE_AUTH, CLIENT_ID, CLIENT_SECRET, login, password))
    .onErrorResumeNext { handleErrors(it)}
        .map { saveToken(it) }
  }

  private fun saveToken(it: TokenModel?): TokenModel? {
    prefs.putString(Consts.AUTH_TOKEN, it?.accessToken)
    return it
  }

  fun getUserModel() : Observable<Account> {
    return inBackground(api.getUserModel()
    .onErrorResumeNext { handleErrors(it) })
    .map { saveUser(it)}
  }

  private fun saveUser(account: Account?): Account? {
    return account
  }

  private fun <T> handleErrors(throwable: Throwable): Observable<T> {
  if (throwable is RetrofitError) {
    return Observable.error(ResponseException(throwable.message.toString(), throwable.response.status))
  /*val error = throwable.getBodyAs(ErrorWrapper::class.java) as ErrorWrapper
      if (error != null && !error.isEmpty) {
        return Observable.error(ResponseException(error.message, 0, error.type, error.errors))
      } else {
        val response = throwable.getBodyAs(ResponseWrapper::class.java) as ResponseWrapper
        if (response != null) {
          return Observable.error(ResponseException(response.errorMessage, response!!.errorCode))
        }
      }
      if (throwable.cause != null) {
        return Observable.error(throwable.cause as Throwable)
      }*/
    }
    return Observable.error(throwable)
  }

  private fun <T : ErrorWrapper> parseResponse(response: Response, responseClass: Class<T>,
      mapper: ObjectMapper): Observable<T> {
    try {
      if (response.body.length() != 0L) {
        val value = mapper.readValue(response.body.`in`(), responseClass)
        if (value.message != null) {
          return Observable.error(ResponseException(value.message, 0, value.type, value.errors))
        } else {
          return Observable.just<T>(value)
        }
      } else {
        return Observable.just<T>(null)
      }
    } catch (e: Exception) {
      Log.d("parseResponse", e.toString())
      return Observable.error(ResponseException("json exception", 0))
    }

  }

}