package ru.medyannikov.kotlintest.ui.splash

import ru.medyannikov.kotlintest.ui.base.BaseView

interface SplashView : BaseView {
  fun onUserLoaded()

  fun showLogin()

  fun showMain()
}