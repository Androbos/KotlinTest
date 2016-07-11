package ru.medyannikov.kotlintest.data.db.repository

import ru.medyannikov.kotlintest.data.db.repository.base.BaseRepository
import ru.medyannikov.kotlintest.data.model.Bill
import rx.Observable
import javax.inject.Singleton


@Singleton
class BillRepository : BaseRepository() {

  fun getAll() : List<Bill> {
    val billCursor = store.getBills()
    var listBill = mutableListOf<Bill>()
    if (billCursor.count > 0) {
      billCursor.moveToFirst()
      do {
         listBill.add(Bill(billCursor))
       } while (billCursor.moveToNext())
    }
    return listBill
  }

  fun getAllObservable() = Observable.just(getAll())

  fun fromServer() : Observable<List<Bill>> = api.getBills()

  fun saveList(listBill: List<Bill>) {
    store.saveBills(listBill)
  }

  fun downloadFromServer() {
    api.getBills().subscribe(
        {onDownload(it)},
        {onError(it)}
    )
  }

  private fun onError(throwable: Throwable?) {
    throwable
  }

  private fun onDownload(list: List<Bill>?) {
    if (list!= null && !list.isEmpty()) {
      saveList(list)
    }
  }

  fun saveBill(bill: Bill) : Observable<Boolean> {
   return store.saveBill(bill)
  }

}