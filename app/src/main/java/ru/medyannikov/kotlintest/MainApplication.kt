package ru.medyannikov.kotlintest

import android.app.Application

class MainApplication : Application() {

  companion object {
    @JvmStatic lateinit var appComponent: AppComponent
  }

  override fun onCreate() {
    super.onCreate()
    initInjections()
  }


  private fun initInjections() {
    appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    appComponent.inject(this)
  }
}