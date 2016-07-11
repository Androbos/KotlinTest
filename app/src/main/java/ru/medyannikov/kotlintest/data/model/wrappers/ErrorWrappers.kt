package ru.medyannikov.kotlintest.data.model.wrappers

open class ErrorWrapper {
  var message: String? = null
  var type: String? = null
  var errors: String? = null

  val isEmpty: Boolean
    get() = message == null || message!!.isEmpty()
}