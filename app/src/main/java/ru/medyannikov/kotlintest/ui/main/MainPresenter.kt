package ru.medyannikov.kotlintest.ui.main

import ru.medyannikov.kotlintest.data.model.User
import ru.medyannikov.kotlintest.ui.base.BasePresenter
import java.util.*

class MainPresenter(
    override var view: MainView
) : BasePresenter<MainView>() {

  val listUser = ArrayList<User>()

  override fun init() {

  }

}