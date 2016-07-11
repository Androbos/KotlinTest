package ru.medyannikov.kotlintest.data

import ru.medyannikov.kotlintest.data.api.ApiRepository
import ru.medyannikov.kotlintest.data.model.TokenModel
import ru.medyannikov.kotlintest.data.model.enums.Consts
import ru.medyannikov.kotlintest.utils.Prefs
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class UserManager @Inject constructor(val api: ApiRepository, val prefs: Prefs) {

  fun saveToken(token:TokenModel?){
    prefs.putString(Consts.AUTH_TOKEN, token?.accessToken)
  }
}