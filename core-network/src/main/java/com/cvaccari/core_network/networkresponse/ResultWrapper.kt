package com.cvaccari.core_network.networkresponse

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val failure: Throwable) : ResultWrapper<Nothing>()

    fun <M> map(mapper: (originalData: T) -> M): ResultWrapper<M> = when (this) {
        is Success -> Success(mapper(value))
        is Error -> Error(failure = failure)
    }

    fun isSuccessful() = this is Success
    fun isError() = this is Error

    inline fun <X> withSuccessAndError(success: (T) -> X, error: (Throwable) -> X): X = when (this) {
        is Success -> success(this.value)
        is Error -> error(this.failure)
    }


    fun toSuccess() = this as Success

    fun toError() = this as Error

    fun toFailure(): Throwable? =
        if (isError()) {
            (this as Error).failure
        } else {
            null
        }
}

inline fun <T> ResultWrapper<T>.onFailure(action: (exception: Throwable) -> Unit): ResultWrapper<T> {
    if (this is ResultWrapper.Error){
        action(failure)
    }
    return this
}

inline fun <T> ResultWrapper<T>.onSuccess(action: (value: T) -> Unit): ResultWrapper<T> {
    if (this is ResultWrapper.Success<T>) {
        action(value)
    }
    return this
}