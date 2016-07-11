package ru.medyannikov.kotlintest.ui.bills

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.f_bills_list.*
import org.jetbrains.anko.support.v4.find
import ru.medyannikov.kotlintest.R
import ru.medyannikov.kotlintest.data.model.Bill
import ru.medyannikov.kotlintest.snackBar
import ru.medyannikov.kotlintest.ui.base.BaseFragment

class BillsFragment : BaseFragment(), BillsView, BillViewHolder.OnClickBillListener, BillDialog.OnCreateBillListener {

  var presenter : BillsPresenter? = null

  var adapter : BillsAdapter? = null
  val fab by lazy { find<FloatingActionButton>(R.id.floating_button) }
  override fun onClickBill(bill: Bill) {
    recycler.snackBar(bill.name.toString())
  }

  override fun getLayout(): Int = R.layout.f_bills_list

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initPresenter()
    initViews()
    initAdapter()
    presenter?.onSwipeRefresh()
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    initFab()
  }

  private fun initFab() {
    var params = activity.floating_button.layoutParams as CoordinatorLayout.LayoutParams
    params.anchorId = recycler.id
    params.anchorGravity = Gravity.BOTTOM or Gravity.RIGHT or Gravity.END
    activity.floating_button.layoutParams = params
    activity.floating_button.setOnClickListener { openDialog() }
  }

  private fun openDialog() {
    val dialog = BillDialog()
    dialog.listener = this
    dialog.show(childFragmentManager, "Adding Bill")
  }

  private fun initAdapter() {
    adapter = BillsAdapter(emptyList<Bill>().toMutableList(), this)
    recycler.layoutManager = LinearLayoutManager(context)
    recycler.adapter = adapter
  }

  private fun initViews() {
    swipe_layout.setOnRefreshListener { presenter?.onSwipeRefresh() }
  }

  private fun initPresenter() {
    presenter = BillsPresenter(api, prefs, userManager, this)
    presenter?.init()
  }

  override fun onShowProgress() { swipe_layout.isRefreshing = true }

  override fun onHideProgress() { swipe_layout.isRefreshing = false }

  override fun isReady(): Boolean = isAdded

  override fun showError(th: Throwable) { }

  override fun onBillLoaded(data: List<Bill>) {
    adapter?.addAll(data)
  }

  override fun onSave(bill: Bill) {
    presenter?.insertBill(bill)
  }

  override fun onInserted(bill: Bill) {
    adapter?.add(bill)
  }

  override fun onDeleted(bill: Bill) {
    adapter?.delete(bill)
  }
}
