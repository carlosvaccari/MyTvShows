package com.cvaccari.commons.extensions

import android.widget.ImageView
import com.cvaccari.commons.GlideApp

fun ImageView.loadImageByUrl(imageUrl: String) {
    GlideApp.with(context)
        .load(imageUrl)
        .override(300, 200)
        .into(this)
}