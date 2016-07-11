package ru.medyannikov.kotlintest.ui.base

abstract class  BasePresenter<V:BaseView>{
  abstract var view: V

  abstract fun init()

  fun onDetachView() {

  }

  fun onError(th: Throwable){
    if (view.isReady()) {
      view.onHideProgress()
      view.showError(th)
    }
  }
}