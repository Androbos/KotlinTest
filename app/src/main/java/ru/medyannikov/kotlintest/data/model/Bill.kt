package ru.medyannikov.kotlintest.data.model

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Bill : Serializable {
  val BILL_EXTRA = "bill_extra"

  @SerializedName("id_bill")
  @Expose
  var idBill:Int = 0

  var localId:Long = 0

  @SerializedName("account")
  @Expose
  var account: Account? = null

  @SerializedName("name")
  @Expose
  var name: String? = "Empty"

  @SerializedName("about")
  @Expose
  var about: String? = "Empty about"

  @SerializedName("summValue")
  @Expose
  var value: Double? = 0.0

  @SerializedName("date_create")
  @Expose
  var date: String? = Calendar.getInstance().toString()

  constructor()

  constructor(name: String): this() {
    this.name = name
  }

  fun toContentValues() : ContentValues {
    val cv = ContentValues()

    //cv.put(Table.ID, localId)
    cv.put(Table.REMOTE_ID, idBill)
    cv.put(Table.NAME_BILL, name)
    cv.put(Table.ABOUT, about)
    cv.put(Table.SUMM, value)
    cv.put(Table.DATE_CREATE, date)

    return cv
  }

  interface Table {
    companion object {
      val NAME = "bills"

      val ID = BaseColumns._ID
      val REMOTE_ID = "remote_id"
      val NAME_BILL = "name"
      val ABOUT = "about"
      val SUMM = "summ"
      val DATE_CREATE = "date_create"

      val PROJECTION = arrayOf(ID, REMOTE_ID, NAME_BILL, ABOUT, SUMM, DATE_CREATE)

      val CREATE = "CREATE TABLE $NAME " +
          "(" +
          "$ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
          "$REMOTE_ID INTEGER, " +
          "$NAME_BILL TEXT, " +
          "$ABOUT TEXT, $SUMM REAL, $DATE_CREATE TEXT" +
          ");"

    }
  }

  constructor(cursor: Cursor) : this() {
    this.idBill = cursor.getInt(cursor.getColumnIndex(Table.REMOTE_ID))
    this.name = cursor.getString(cursor.getColumnIndex(Table.NAME_BILL))
    this.about = cursor.getString(cursor.getColumnIndex(Table.ABOUT))
    this.value = cursor.getDouble(cursor.getColumnIndex(Table.SUMM))
    this.date = cursor.getString(cursor.getColumnIndex(Table.DATE_CREATE))
  }
}