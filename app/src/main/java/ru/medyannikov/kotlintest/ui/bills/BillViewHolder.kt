package ru.medyannikov.kotlintest.ui.bills

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.i_bill_list_item.view.*
import ru.medyannikov.kotlintest.data.model.Bill

class BillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val nameBill: TextView? = itemView.billName
  val aboutBill: TextView? = itemView.billAbout
  val valueBill: TextView? = itemView.billValue
  val billSync = itemView.billSync
  var listener: OnClickBillListener? = null

  private var bill: Bill? = null

  fun bindBill(bill : Bill) {
     this.bill = bill
     itemView.setOnClickListener { listener?.onClickBill(bill) }
  }

  interface OnClickBillListener{
    fun onClickBill(bill: Bill)
  }

}