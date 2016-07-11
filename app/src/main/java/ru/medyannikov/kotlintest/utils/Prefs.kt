package ru.medyannikov.kotlintest.utils

import com.fasterxml.jackson.databind.ObjectMapper
import org.jetbrains.anko.defaultSharedPreferences
import ru.medyannikov.kotlintest.MainApplication
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Prefs @Inject constructor(val app: MainApplication, val mapper: ObjectMapper) {
  fun  getString(key: String): String? {
    return app.defaultSharedPreferences.getString(key, null)
  }

  fun  putString(key: String, string: String?) {
    val editor = app.defaultSharedPreferences.edit()
    editor.putString(key, string)
    editor.apply()
  }

}