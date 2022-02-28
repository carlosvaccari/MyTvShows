package com.cvaccari.core_network.networkresponse

sealed class NetworkResponse<out T> {
    data class Success<out T>(val value: T) : NetworkResponse<T>()
    data class Error(val exception: Throwable) : NetworkResponse<Nothing>()

    fun isSuccessful() = this is Success
    fun isError() = this is Error
}

inline fun <T> NetworkResponse<T>.onFailure(action: (exception: Throwable) -> Unit): NetworkResponse<T> {
    if (this is NetworkResponse.Error){
        action(exception)
    }
    return this
}

inline fun <T> NetworkResponse<T>.onSuccess(action: (value: T) -> Unit): NetworkResponse<T> {
    if (this is NetworkResponse.Success<T>) {
        action(value)
    }
    return this
}

fun <T> NetworkResponse<T>.toResult(): ResultWrapper<T> =
    when (this) {
        is NetworkResponse.Success -> {
            ResultWrapper.Success(this.value)
        }
        is NetworkResponse.Error -> {
            ResultWrapper.Error(failure = this.exception ?: Throwable())
        }
    }