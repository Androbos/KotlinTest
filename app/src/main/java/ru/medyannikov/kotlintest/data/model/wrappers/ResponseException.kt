package ru.medyannikov.kotlintest.data.model.wrappers

class ResponseException : Throwable {
  var msg: String?
  var code: Int = 0
  var type: String? = null
  var errors: String? = null

  constructor(msg: String, code: Int) {
    this.msg = msg
    this.code = code
  }

  constructor(msg: String?, code: Int, type: String?, errors: String?) {
    this.msg = msg
    this.code = code
    this.type = type
    this.errors = errors
  }
}