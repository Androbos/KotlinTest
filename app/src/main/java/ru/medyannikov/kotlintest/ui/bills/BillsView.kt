package ru.medyannikov.kotlintest.ui.bills

import ru.medyannikov.kotlintest.data.model.Bill
import ru.medyannikov.kotlintest.ui.base.BaseView

interface BillsView : BaseView{
  fun onBillLoaded(testData: List<Bill>)
  fun onInserted(bill: Bill)
  fun onDeleted(bill: Bill)
}