package com.cvaccari.commons.extensions

import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cvaccari.commons.GlideApp

fun ImageView.loadImageByUrl(imageUrl: String) {
    GlideApp.with(context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .override(300, 200)
        .into(this)
}