package ru.medyannikov.kotlintest.data.db

import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.medyannikov.kotlintest.MainApplication
import ru.medyannikov.kotlintest.data.UserManager
import ru.medyannikov.kotlintest.data.api.ApiRepository
import ru.medyannikov.kotlintest.data.model.Bill
import ru.medyannikov.kotlintest.utils.Prefs
import rx.Observable
import javax.inject.Inject

class HomeBankDB : SQLiteOpenHelper{

  @Inject lateinit var prefs: Prefs
  @Inject lateinit var userManager: UserManager
  @Inject lateinit var api: ApiRepository

  companion object {
    const val DB_NAME = "homebank_db.db"
    const val DB_VERSION = 4
  }
  var app :MainApplication? = null

   @Inject
   constructor(app : MainApplication) : super(app, DB_NAME, null, DB_VERSION) {
     this.app = app
     MainApplication.appComponent.inject(this)
  }

  fun saveBills(list: List<Bill>) {
    val db = writableDatabase
    db.beginTransaction()
    for (bill in list) {
      db.insert(Bill.Table.NAME, null, bill.toContentValues())
    }
    db.setTransactionSuccessful()
    db.endTransaction()
  }

  override fun onCreate(db: SQLiteDatabase?) {
    db?.execSQL(Bill.Table.CREATE)
  }

  override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    db?.execSQL("Drop table ${Bill.Table.NAME};")
    onCreate(db)
  }

  fun  getBills(): Cursor {
    val db = readableDatabase
    return db.query(Bill.Table.NAME, Bill.Table.PROJECTION,null,null,null,null,null)
  }

  fun saveBill(bill: Bill) : Observable<Boolean> {
    val db = writableDatabase
    return Observable.just(db.use {
      val id = db.insert(Bill.Table.NAME, null, bill.toContentValues())
      if (id != -1L) {
       bill.localId = id
      }
    })
  }

}

inline fun <T> SQLiteDatabase.use(f: SQLiteDatabase.() -> T) : Boolean  {
  try {
    beginTransaction()
    f()
    setTransactionSuccessful()
    return true
  } catch (ex : SQLException) {
    return false
  }
  finally {
    endTransaction()
  }
}