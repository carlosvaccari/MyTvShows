package com.cvaccari.commons.databindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.cvaccari.commons.extensions.loadImageById

@BindingAdapter("srcFromImageId")
fun ImageView.srcFromImageId(imageId: String?) {
    imageId?.let { loadImageById(it) }
}