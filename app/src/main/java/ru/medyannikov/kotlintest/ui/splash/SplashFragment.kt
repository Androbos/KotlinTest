package ru.medyannikov.kotlintest.ui.splash

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import org.jetbrains.anko.support.v4.find
import ru.medyannikov.kotlintest.*
import ru.medyannikov.kotlintest.ui.base.BaseFragment
import ru.medyannikov.kotlintest.ui.login.LoginActivity
import ru.medyannikov.kotlintest.ui.main.MainActivity

class SplashFragment : BaseFragment(), SplashView {

  val progress by lazy { find<ProgressBar>(R.id.progress) }
  var presenter : SplashPresenter? = null

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initPresenter()
  }

  private fun initPresenter() {
    presenter = SplashPresenter(api, prefs, this)
    presenter!!.init()
  }

  override fun getLayout(): Int = R.layout.f_splash


  override fun onUserLoaded() {

  }

  override fun showLogin() {
    (activity as SplashActivity).startApp(context.getInstance<LoginActivity>())
  }

  override fun showMain() {
    (activity as SplashActivity).startApp(context.getInstance<MainActivity>())
  }

  override fun isReady(): Boolean = isAdded

  override fun onShowProgress() = progress.show()

  override fun onHideProgress() = progress.hide()

  override fun showError(th: Throwable) {

  }
}