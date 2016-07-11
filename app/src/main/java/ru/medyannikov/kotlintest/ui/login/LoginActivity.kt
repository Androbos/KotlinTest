package ru.medyannikov.kotlintest.ui.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick
import ru.medyannikov.kotlintest.*
import ru.medyannikov.kotlintest.ui.base.BaseActivity
import ru.medyannikov.kotlintest.ui.main.MainActivity

class LoginActivity : BaseActivity(), LoginView {

  val login by lazy { find<EditText>(R.id.login_email) }
  val password by lazy { find<EditText>(R.id.login_password)}
  val signInButton by lazy { find<Button>(R.id.sign_in) }
  val dialog by lazy { buildProgress(R.string.text_loading) }
  var presenter : LoginPresenter?= null

  private fun signIn() {
    val textLogin = login.text.toString()
    val textPassword = password.text.toString()
    presenter?.signIn(textLogin, textPassword)
  }

  override fun onShowProgress() {
    showDialog(dialog)
  }

  override fun onHideProgress() {
   hideDialog(dialog)
  }

  override fun isReady(): Boolean {
    return true
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initPresenter()
    initView()
  }

  private fun initView() {
    signInButton.onClick { signIn() }
  }

  private fun initPresenter() {
    presenter = LoginPresenter(api, prefs, userManager, this)
    presenter?.init()
  }

  override fun getLayout(): Int {
    return R.layout.a_login
  }

  override fun showError(th: Throwable) {
    responseError(th, toolbar)
  }

  override fun showMain() {
    startApp(getInstance<MainActivity>())
  }
}
