package ru.medyannikov.kotlintest.ui.main

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.ArrayAdapter
import org.jetbrains.anko.find
import ru.medyannikov.kotlintest.R
import ru.medyannikov.kotlintest.data.model.User
import ru.medyannikov.kotlintest.ui.base.BaseActivity
import ru.medyannikov.kotlintest.ui.bills.BillsFragment
import java.util.*

class MainActivity : BaseActivity(), MainView {
  override fun isReady(): Boolean {
    return true
  }

  val presenter = MainPresenter(this)
  val fab by lazy { find<FloatingActionButton>(R.id.floating_button) }

  var adapter : ArrayAdapter<User>? = null
  override fun onShowProgress() {
    throw UnsupportedOperationException()
  }

  override fun onHideProgress() {
    throw UnsupportedOperationException()
  }

  override fun openTest() {
    throw UnsupportedOperationException()
  }

  override fun getLayout(): Int {
    return R.layout.activity_main
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initPresenter()
    supportFragmentManager.beginTransaction().replace(R.id.main_container, BillsFragment()).commit()
  }

  private fun initPresenter() {
    presenter.init()
  }

  override fun onPause() {
    super.onPause()
  }

  override fun showUsers(listUser: ArrayList<User>) {

  }

  override fun showError(th: Throwable) {

  }

  fun getFLoatinButton() : FloatingActionButton = fab
}
