package ru.medyannikov.kotlintest.ui.bills

import ru.medyannikov.kotlintest.data.UserManager
import ru.medyannikov.kotlintest.data.api.ApiRepository
import ru.medyannikov.kotlintest.data.db.repository.BillRepository
import ru.medyannikov.kotlintest.data.model.Bill
import ru.medyannikov.kotlintest.ui.base.BasePresenter
import ru.medyannikov.kotlintest.utils.Prefs
import rx.subscriptions.CompositeSubscription

class BillsPresenter(
    var api: ApiRepository,
    val prefs: Prefs,
    val userManager: UserManager,
    override var view: BillsView) : BasePresenter<BillsView>() {

  val compositeSubscription = CompositeSubscription()
  val billRepository = BillRepository()
  override fun init() {

  }

  fun onSwipeRefresh() {
    compositeSubscription.add(billRepository.getAllObservable().subscribe(
        {onBillLoaded(it)},
        {onError(it)})
    )
  }

  private fun onBillLoaded(list: List<Bill>?) {
    if (list != null && !list.isEmpty()) {
      view.onHideProgress()
      view.onBillLoaded(list)
    } else {
      view.onHideProgress()
    }
  }

  fun insertBill(bill: Bill) {
    compositeSubscription.add(billRepository.saveBill(bill).subscribe(
        {onInsertedBill(it, bill)},
        {onError(it)})
    )
  }

  private fun onInsertedBill(result: Boolean?, bill: Bill) {
    if (view.isReady()) {
      view.onHideProgress()
      view.onInserted(bill)
    }
  }
}