package me.darthwithap.android.miniqoutesapp.util

sealed class Resource<T>(val data: T? = null, val errorMsg: String? = null) {
  class Loading<T> : Resource<T>()
  class Success<T>(data: T) : Resource<T>(data)
  class Error<T>(errorMsg: String, data: T? = null) : Resource<T>(data, errorMsg)
}
