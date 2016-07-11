package ru.medyannikov.kotlintest.ui.main

import ru.medyannikov.kotlintest.data.model.User
import ru.medyannikov.kotlintest.ui.base.BaseView
import java.util.*

interface  MainView : BaseView {
  fun openTest()

  fun showUsers(listUser: ArrayList<User>)
}