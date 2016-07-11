package ru.medyannikov.kotlintest.data.db.repository.base

import ru.medyannikov.kotlintest.MainApplication
import ru.medyannikov.kotlintest.data.UserManager
import ru.medyannikov.kotlintest.data.api.ApiRepository
import ru.medyannikov.kotlintest.data.db.HomeBankDB
import ru.medyannikov.kotlintest.utils.Prefs
import javax.inject.Inject

open class BaseRepository {
  @Inject lateinit var prefs:Prefs
  @Inject lateinit var api: ApiRepository
  @Inject lateinit var userManager: UserManager
  @Inject lateinit var store: HomeBankDB

  constructor() {
    MainApplication.appComponent.inject(this)
  }
}