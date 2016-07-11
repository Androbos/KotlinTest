package ru.medyannikov.kotlintest.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.medyannikov.kotlintest.MainApplication
import ru.medyannikov.kotlintest.data.UserManager
import ru.medyannikov.kotlintest.data.api.ApiRepository
import ru.medyannikov.kotlintest.utils.Prefs
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

  @Inject lateinit var prefs: Prefs
  @Inject lateinit var api : ApiRepository
  @Inject lateinit var userManager : UserManager

  var compositeSubscription = CompositeSubscription()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initDependencies()
  }

  private fun initDependencies() {
    MainApplication.appComponent.inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater?.inflate(getLayout(), container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onDestroyView() {
    super.onDestroyView()
  }

  abstract fun getLayout(): Int
}

