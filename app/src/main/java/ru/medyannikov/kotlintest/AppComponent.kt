package ru.medyannikov.kotlintest

import dagger.Component
import ru.medyannikov.kotlintest.data.db.HomeBankDB
import ru.medyannikov.kotlintest.data.db.repository.base.BaseRepository
import ru.medyannikov.kotlintest.ui.base.BaseActivity
import ru.medyannikov.kotlintest.ui.base.BaseFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
  fun inject(app: MainApplication)
  fun inject(activity: BaseActivity)
  fun inject(fragment: BaseFragment)
  fun inject(repository: BaseRepository)

  fun inject(homeBankDB: HomeBankDB)
}