package ru.medyannikov.kotlintest

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View
import ru.medyannikov.kotlintest.data.model.wrappers.ResponseException
import ru.medyannikov.kotlintest.ui.base.BaseActivity
import ru.medyannikov.kotlintest.ui.base.BaseView
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

fun View.hide() {
  visibility = View.GONE
}

fun View.show() {
  visibility = View.VISIBLE
}

fun <T> inBackground(func: Observable<T>): Observable<T> {
  return Observable.defer {
    func.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
  }
}

fun <T: BaseActivity> T.startApp(intent: Intent) : Unit {
  startActivity(intent)
  finish()
}

inline fun <reified T: BaseActivity> Context.getInstance() : Intent{
  return Intent(this, T::class.java)
}

inline fun <reified T: BaseView> T?.showDialog(dialog: Dialog) : Unit {
  if (!dialog.isShowing) {
    dialog.show()
  }
}
inline fun <reified T: BaseView> T?.hideDialog(dialog: Dialog?) : Unit {
  if (dialog?.isShowing as Boolean) {
    dialog?.dismiss()
  }
}

inline fun <reified T: Context> T.buildProgress(@StringRes text: Int) : Dialog {
  val dialog = ProgressDialog(this)
  dialog.setMessage(this.resources.getText(text))
  dialog.setCancelable(false)
  return dialog
}

fun View.snackBar(msg: String, duration : Int = Snackbar.LENGTH_LONG) {
  Snackbar.make(this,msg,duration).show()
}
fun View.snackBar(@StringRes msg: Int, duration : Int = Snackbar.LENGTH_LONG) {
  Snackbar.make(this,msg,duration).show()
}

inline fun <reified T: BaseView> T?.responseError(th : Throwable, view: View) {
  if (th is ResponseException) {
    with(th) {
      when(code){
        400 -> view.snackBar(R.string.data_incorrect)
        403 -> view.snackBar(R.string.userdata_incorrect)
        500 -> view.snackBar(R.string.not_respond)
      }
    }
  }
}

