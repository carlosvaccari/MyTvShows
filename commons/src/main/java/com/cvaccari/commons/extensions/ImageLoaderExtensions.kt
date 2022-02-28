package com.cvaccari.commons.extensions

import android.widget.ImageView
import com.cvaccari.commons.BuildConfig
import com.cvaccari.commons.GlideApp

private const val IMAGE_ID_PARAM = "{imageId}"

fun ImageView.loadImageById(imageId: String) {
    GlideApp.with(context)
        .load(BuildConfig.IMG_URL.replace(IMAGE_ID_PARAM, imageId))
        .override(300, 200)
        .into(this)
}