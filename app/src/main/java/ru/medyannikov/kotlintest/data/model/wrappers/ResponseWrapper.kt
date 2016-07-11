package ru.medyannikov.kotlintest.data.model.wrappers

import com.fasterxml.jackson.annotation.JsonProperty

class ResponseWrapper : ErrorWrapper() {
  @JsonProperty("errorCode")
  var errorCode: Int = 0
  @JsonProperty("errorMessage")
  var errorMessage: String = ""
}