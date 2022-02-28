package com.cvaccari.core_network.factory

import com.cvaccari.core_network.networkresponse.NetworkResponse
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkResponseCall<R>(private val call: Call<R>) :
    CallDelegate<R, NetworkResponse<R>>(call) {

    override fun enqueueImpl(callback: Callback<NetworkResponse<R>>) =
        call.enqueue(object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()
                val url = response.raw().request.url

                val result = if (response.isSuccessful) {
                    if (body != null) {
                        NetworkResponse.Success(body)
                    } else {
                        responseEmpty()
                    }
                } else {
                    NetworkResponse.Error(Throwable())
                }

                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(result)
                )
            }

            override fun onFailure(call: Call<R>, t: Throwable) = callback.onResponse(
                this@NetworkResponseCall,
                Response.success(NetworkResponse.Error(exception = t))
            )

        })

    override fun cloneImpl(): Call<NetworkResponse<R>> = NetworkResponseCall(proxy.clone())

    fun responseEmpty() = try {
        NetworkResponse.Success(Unit as R)
    } catch (ex: Exception) {
        NetworkResponse.Error(exception = Throwable())
    }
}

abstract class CallDelegate<TIn, TOut>(
    protected val proxy: Call<TIn>
) : Call<TOut> {
    override fun execute(): Response<TOut> = throw NotImplementedError()
    override final fun enqueue(callback: Callback<TOut>) = enqueueImpl(callback)
    override final fun clone(): Call<TOut> = cloneImpl()

    override fun cancel() = proxy.cancel()
    override fun request(): Request = proxy.request()
    override fun isExecuted() = proxy.isExecuted
    override fun isCanceled() = proxy.isCanceled

    abstract fun enqueueImpl(callback: Callback<TOut>)
    abstract fun cloneImpl(): Call<TOut>
    override fun timeout() = proxy.timeout()
}