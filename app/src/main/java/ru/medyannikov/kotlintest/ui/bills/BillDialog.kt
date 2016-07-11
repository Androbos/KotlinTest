package ru.medyannikov.kotlintest.ui.bills

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import kotlinx.android.synthetic.main.d_bill_add.view.*
import ru.medyannikov.kotlintest.R
import ru.medyannikov.kotlintest.data.model.Bill

class BillDialog : DialogFragment() {

  var listener : OnCreateBillListener? = null

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val builder = AlertDialog.Builder(activity)
    val view = activity.layoutInflater.inflate(R.layout.d_bill_add, null)

    builder.setView(view)
        .setTitle("Adding bill")
        .setPositiveButton("Add", DialogInterface.OnClickListener { dialogInterface, i ->
          if (!validateInput(view)) return@OnClickListener
          saveBill(view.billName.text.toString(),
          view.billAbout.text.toString())
        })
        .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i -> dismiss() })

    return builder.create()
  }

  private fun saveBill(name: String, about: String ) {
    val bill = Bill()
    bill.name = name
    bill.about = about
    listener?.onSave(bill)
  }

  private fun validateInput(view: View): Boolean {
    if (view.billName.text.isEmpty()) {
      return false
    } else {
      return true
    }
  }

  interface OnCreateBillListener {
    fun onSave(bill : Bill)
  }
}