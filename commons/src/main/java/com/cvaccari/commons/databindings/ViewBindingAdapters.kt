package com.cvaccari.commons.databindings

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("showIf")
fun View.showIf(value: Boolean) {
    isVisible = value
}