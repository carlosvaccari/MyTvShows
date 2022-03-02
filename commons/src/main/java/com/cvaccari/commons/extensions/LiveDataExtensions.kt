package com.cvaccari.commons.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cvaccari.commons.base.State


inline fun <reified TYPE : State> LiveData<out State>.atState(
    crossinline block: (state: TYPE) -> Unit = {}
): LiveData<Boolean> {
    observeForever {
        if (it is TYPE) {
            block(it)
        }
    }

    val x = MutableLiveData((this is TYPE))
    println(">>>>>> " + x.value)

    return MutableLiveData((this as? TYPE) != null)
}
