package com.cvaccari.commons.databindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.cvaccari.commons.extensions.loadImageByUrl

@BindingAdapter("srcFromUrl")
fun ImageView.srcFromUrl(imageUrl: String?) {
    loadImageByUrl(imageUrl.orEmpty())
}

@BindingAdapter("onStateChanged")
fun ImageView.bindOnStateChanged(boolean: Boolean) {
    this.isSelected = boolean
}