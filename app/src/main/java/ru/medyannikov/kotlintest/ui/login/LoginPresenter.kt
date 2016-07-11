package ru.medyannikov.kotlintest.ui.login


import ru.medyannikov.kotlintest.data.UserManager
import ru.medyannikov.kotlintest.data.api.ApiRepository
import ru.medyannikov.kotlintest.data.model.TokenModel
import ru.medyannikov.kotlintest.ui.base.BasePresenter
import ru.medyannikov.kotlintest.utils.Prefs
import rx.subscriptions.CompositeSubscription

class LoginPresenter(var api: ApiRepository, var prefs: Prefs, var userManager: UserManager, override var view: LoginView) : BasePresenter<LoginView>(){

  val compositeSubscription = CompositeSubscription()

  override fun init() {

  }

  fun signIn(login: String, password: String) {
    view.onShowProgress()
    compositeSubscription.add(api.signIn(login, password).subscribe(
        {onLogin(it)},
        {onError(it)}
    ))
  }

  private fun onLogin(tokenModel: TokenModel?) {
    userManager.saveToken(tokenModel)
    view.onHideProgress()
    view.showMain()
  }

}