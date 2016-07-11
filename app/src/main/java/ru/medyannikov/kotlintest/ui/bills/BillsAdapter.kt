package ru.medyannikov.kotlintest.ui.bills

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.medyannikov.kotlintest.R
import ru.medyannikov.kotlintest.data.model.Bill

class BillsAdapter(
    list: MutableList<Bill>,
    var listener : BillViewHolder.OnClickBillListener
) : RecyclerView.Adapter<BillViewHolder>() {
    var billList : MutableList<Bill> = list

  override fun onBindViewHolder(holder: BillViewHolder?, position: Int) {
    val bill: Bill = billList.get(position)
    if (holder != null) {
      holder.listener = listener
      holder.nameBill?.text = bill.name
      holder.aboutBill?.text = bill.about
      holder.valueBill?.text = bill.value.toString()
      holder.bindBill(bill)
    }
  }

  fun add(bill: Bill) {
    billList.add(bill)
    notifyItemInserted(billList.size - 1)
  }

  fun delete(bill : Bill) {
    val index = billList.indexOf(bill)
    billList.removeAt(index)
    notifyItemRemoved(index)
  }

  fun addAll(list: List<Bill>) {
    billList = list.toMutableList()
    notifyDataSetChanged()
  }

  override fun getItemCount(): Int = billList.size

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BillViewHolder {
    val view = LayoutInflater.from(parent?.context).inflate(R.layout.i_bill_list_item, parent, false)
    return BillViewHolder(view)
  }
}