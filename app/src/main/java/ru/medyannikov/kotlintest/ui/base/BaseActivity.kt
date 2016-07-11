package ru.medyannikov.kotlintest.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.find
import ru.medyannikov.kotlintest.MainApplication
import ru.medyannikov.kotlintest.R
import ru.medyannikov.kotlintest.data.UserManager
import ru.medyannikov.kotlintest.data.api.ApiRepository
import ru.medyannikov.kotlintest.ui.base.toolbar.CustomToolbar
import ru.medyannikov.kotlintest.utils.Prefs
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

  companion object {}

  val toolbar by lazy { find<CustomToolbar>(R.id.toolbar)}

  @Inject lateinit var api: ApiRepository
  @Inject lateinit var prefs: Prefs
  @Inject lateinit var userManager : UserManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(getLayout())
    MainApplication.appComponent.inject(this)
  }


  abstract  fun getLayout(): Int
}