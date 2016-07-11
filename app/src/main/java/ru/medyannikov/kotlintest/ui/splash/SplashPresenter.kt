package ru.medyannikov.kotlintest.ui.splash

import ru.medyannikov.kotlintest.data.api.ApiRepository
import ru.medyannikov.kotlintest.data.model.Account
import ru.medyannikov.kotlintest.data.model.TokenModel
import ru.medyannikov.kotlintest.data.model.enums.Consts
import ru.medyannikov.kotlintest.ui.base.BasePresenter
import ru.medyannikov.kotlintest.utils.Prefs
import rx.subscriptions.CompositeSubscription

class SplashPresenter(val apiRepository: ApiRepository, val prefs: Prefs, override var view: SplashView) : BasePresenter<SplashView>() {

  val compositeSubcription = CompositeSubscription()

  override fun init() {
    if (prefs.getString(Consts.AUTH_TOKEN) == null) {
      showLogin()
    } else {
      openMain()
    }
  }

  private fun openMain() {
    if (view.isReady()) {
      view.onShowProgress()
      view.showMain()
    }
  }

  private fun showLogin() {
    if (view.isReady()){
      view.onShowProgress()
      view.showLogin()
    }
  }

  private fun onTokenComplete(token: TokenModel?) {
    token.toString()
    compositeSubcription.add(apiRepository.getUserModel().subscribe({onGetUser(it)}, {onError(it)}))
  }

  private fun onGetUser(user: Account?) {
   user?.email
  }
}