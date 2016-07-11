package ru.medyannikov.kotlintest.ui.base

interface BaseView {
  fun onShowProgress();
  fun onHideProgress();

  fun isReady(): Boolean

  fun showError(th: Throwable)
}