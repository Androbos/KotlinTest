package ru.medyannikov.kotlintest.data.model

data class User(var id:Int, var name:String, var sex:Sex) {
  override fun toString(): String {
    return "I am #$id $name $sex"
  }
}